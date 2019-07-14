package com.springcloud.auto.entity;

    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author zhanglong
* @since 2019-07-14
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderNo;

    private Long productId;

    private String productCode;

    private Integer productNum;

    private Integer productTotal;

    private Integer productPrice;

    private String userName;


}
