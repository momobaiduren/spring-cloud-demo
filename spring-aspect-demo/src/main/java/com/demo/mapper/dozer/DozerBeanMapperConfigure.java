package com.demo.mapper.dozer;

import com.demo.mapper.orika.Mapper;
import lombok.Data;
import org.dozer.DozerBeanMapper;

import java.math.BigDecimal;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/24  11:38 下午
 */
public class DozerBeanMapperConfigure {
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }

    public static void main(String[] args) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        A a = new A();
        a.setA("aaa");
        a.setSs(BigDecimal.TEN);
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000 ; i++) {
            mapper.map(a, B.class);
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
        for (int i = 0; i < 1000000 ; i++) {
            Mapper.map(a, B.class);
        }
        long l3 = System.currentTimeMillis();
        System.out.println(l3 - l2);
    }
    @Data
    public static class A {
        String a;
        BigDecimal ss;
    }
    @Data
    public static class B {
        String b;
        BigDecimal ss;
    }

}
