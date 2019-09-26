package com.demo.Jdk8Api;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/22  10:24 下午
 */
public class HandlerEntity<T> {

    private  T t;

    public HandlerEntity(T t) {
        Objects.requireNonNull(t,"target object could not be null");
        this.t = t;
    }

    public <F> void setValue(Supplier<F> supplier, BiConsumer<T,F> biConsumer){
        Assert.notNull(supplier,"target value could not be null");
        Assert.notNull(biConsumer,"biConsumer could not be null");
        F f = supplier.get();
        if (f instanceof BigDecimal){
            Optional.ofNullable(f).orElse((F) BigDecimal.ZERO);
        }
        biConsumer.accept(t,supplier.get());
    }

    public void setBigDecimalValue( Function<T, BigDecimal> function,
                                              Supplier<BigDecimal> supplier,
                                              BinaryOperator<BigDecimal> bigDecimalOperator,
                                              BiConsumer<T, BigDecimal> biConsumer) {
        Assert.notNull(function, "var1 object cloud not be null");
        Assert.notNull(supplier, "supplier cloud not be null");
        Assert.notNull(bigDecimalOperator, "BinaryOperator cloud not be null");
        biConsumer.accept(t, bigDecimalOperator.apply(function.apply(t), supplier.get()));
    }
}
