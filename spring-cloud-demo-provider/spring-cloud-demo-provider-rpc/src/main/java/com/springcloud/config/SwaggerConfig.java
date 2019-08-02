package com.springcloud.config;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import io.swagger.annotations.Api;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingBuilderPlugin;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.ApiListingContext;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Autowired
//    private ConfigurationProp properties;
    @Value("${swagger.springcloud.title}")
    private String title;
    @Value("${swagger.springcloud.description}")
    private String description;
    @Value("${swagger.springcloud.version}")
    private String version;
    @Value("${swagger.springcloud.enable}")
    private boolean enable;


    @Bean
    public Docket settleDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .enable(enable)
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts())
            .select()
            .apis(select(Api.class))
            .paths(PathSelectors.any())
            .build()
//                .globalOperationParameters(globalParam())
            ;
    }

    private Predicate<RequestHandler> select(Class<? extends Annotation> annotation) {
        return new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                return input.findControllerAnnotation(annotation).isPresent();
            }
        };
    }

    @Bean
    public ApiDocBuilder settleApiDocBuilder() {
        return new ApiDocBuilder();
    }

    private List<Parameter> globalParam() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(
            parameterBuilder
                .name("login-token")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build());
        return parameters;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(title)
            .version(version)
            .description(description)
            .build();
    }

    private List<ApiKey> securitySchemes() {
        return Collections.singletonList(new ApiKey("TokenAuth", "login-token", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder()
            .securityReferences(Arrays
                .asList(new SecurityReference("TokenAuth",
                    new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})))
            .forPaths(PathSelectors.any()).build());
    }

    public static class ApiDocBuilder implements ApiListingBuilderPlugin, OperationBuilderPlugin {

        @SuppressWarnings("unchecked")
        @Override
        public void apply(ApiListingContext context) {
            ApiListingBuilder builder = context.apiListingBuilder();
            builder.description("");
            Optional<? extends Class<?>> optional = context.getResourceGroup().getControllerClass();
            if (!optional.isPresent()) {
                return;
            }
            Api api = optional.get().getDeclaredAnnotation(Api.class);
            if (api == null || api.value().length() == 0 || (api.tags().length == 0
                && api.tags()[0].length() == 0)) {
                return;
            }
            String name = api.value();
            try {
                Field field = builder.getClass().getDeclaredField("tagNames");
                field.setAccessible(true);
                Set<String> tagNames = (Set<String>) field.get(builder);
                tagNames.clear();
                tagNames.add(name);
            } catch (Exception e) {
//                log.error("fail:", e);
            }
        }

        @Override
        public void apply(OperationContext context) {
            Optional<Api> optional = context.findControllerAnnotation(Api.class);
            if (!optional.isPresent()) {
                return;
            }
            Api api = optional.get();
            if (api == null || api.value().length() == 0 || (api.tags().length == 0
                && api.tags()[0].length() == 0)) {
                return;
            }
            context.operationBuilder().tags(new HashSet<>(Arrays.asList(api.value())));
        }

        @Override
        public boolean supports(DocumentationType delimiter) {
            return true;
        }
    }

}
