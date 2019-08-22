package com.springcloud.service.permission;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-2118:34
 */
public interface AuthorityHandler {

    List<String> fetchAuthorityCodeList(
        @NotNull() ModelEnum authorityModelName );
}
