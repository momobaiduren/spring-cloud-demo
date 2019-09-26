package com.demo.Jdk8Api;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/21  12:24 上午
 */
public class SettleAmount {

    /**
     * @Author zhanglong
     * @Date 2019/9/21
     */
    public static <T> BigDecimal settleAmout(Stream<T> stream, boolean isAdd, Predicate<T> predicate, Function<T, BigDecimal> function) {
        Objects.requireNonNull(stream, "collection is not null");
        Objects.requireNonNull(function, "function expression is not null");
        Stream<BigDecimal> streamMap;
        if (Objects.nonNull(predicate)) {
            streamMap = stream.filter(t -> predicate.test(t)).map(t -> function.apply(t));
        } else {
            streamMap = stream.map(t -> function.apply(t));
        }
        BigDecimal result;
        if (isAdd) {
            result = streamMap.reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            result = streamMap.reduce(BigDecimal.ZERO, BigDecimal::subtract);
        }
        return result;
    }

    public static <T> void setBigDecimalValue(T t, BigDecimal value, BiConsumer<T, BigDecimal> biConsumer) {
        biConsumer.accept(t, value);
    }

    public static <T> void setBigDecimalValue(T t, BigDecimal var1, BigDecimal var2, BigDecimalOperatorEnum bigDecimalOperatorEnum, BiConsumer<T, BigDecimal> biConsumer) {
        Assert.notNull(t,"target object cloud not be null");
        biConsumer.accept(t, bigdecimalAmount(var1, var2, bigDecimalOperatorEnum));
    }

    public static <T> void setBigDecimalValue(T t, Function<T, BigDecimal> var1, BigDecimal var2, BigDecimalOperatorEnum bigDecimalOperatorEnum, BiConsumer<T, BigDecimal> biConsumer) {
        Assert.notNull(t,"target object cloud not be null");
        biConsumer.accept(t, bigdecimalAmount(var1.apply(t), var2, bigDecimalOperatorEnum));
    }

    public static BigDecimal bigdecimalAmount(BigDecimal val1, BigDecimal val2, BigDecimalOperatorEnum bigDecimalOperatorEnum) {
        return bigdecimalAmount(val1, val2, bigDecimalOperatorEnum, -1);
    }

    public static BigDecimal bigdecimalAmount(BigDecimal val1, BigDecimal val2, BigDecimalOperatorEnum bigDecimalOperatorEnum, Integer precision) {
        Assert.notNull(bigDecimalOperatorEnum, "operator is not null");
        BigDecimal result;
        switch (bigDecimalOperatorEnum) {
            case ADD:
                result = Optional.ofNullable(val1).orElse(BigDecimal.ZERO).add(Optional.ofNullable(val2).orElse(BigDecimal.ZERO));
                break;
            case SUBTRACT:
                result = Optional.ofNullable(val1).orElse(BigDecimal.ZERO).subtract(Optional.ofNullable(val2).orElse(BigDecimal.ZERO));
                break;
            case MULTIPLY:
                result = Optional.ofNullable(val1).orElse(BigDecimal.ZERO).multiply(Optional.ofNullable(val2).orElse(BigDecimal.ZERO));
                break;
            case DIVIDE:
                if (Objects.isNull(val2)) {
                    throw new IllegalStateException("dividend could not be zero");
                }
                if (Objects.isNull(precision) || precision < 0) {
                    result = Optional.ofNullable(val1).orElse(BigDecimal.ZERO).divide(Optional.ofNullable(val2).orElse(BigDecimal.ZERO));
                } else {
                    result = Optional.ofNullable(val1).orElse(BigDecimal.ZERO).divide(Optional.ofNullable(val2).orElse(BigDecimal.ZERO), precision);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + bigDecimalOperatorEnum);
        }
        return result;
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = bigdecimalAmount(BigDecimal.ONE, BigDecimal.TEN, BigDecimalOperatorEnum.ADD, -1);
        System.out.println(bigDecimal);
        BigDecimal bigDecimal1 = bigdecimalAmount(BigDecimal.ONE, BigDecimal.TEN, BigDecimalOperatorEnum.ADD);
        System.out.println(bigDecimal1);
        Apple a = new Apple(BigDecimal.valueOf(10));
//        setBigDecimalValue(a, BigDecimal.ONE, (apple, value) -> {
//            apple.setPrice(value);
//        });
        setBigDecimalValue(a,(apple) ->apple.getPrice(), BigDecimal.valueOf(12.2), BigDecimalOperatorEnum.MULTIPLY,(Apple::setPrice));
        System.out.println(a);
    }

}
