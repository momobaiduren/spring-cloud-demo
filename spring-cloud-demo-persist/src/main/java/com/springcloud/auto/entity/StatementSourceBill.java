package com.springcloud.auto.entity;

    import java.time.LocalDate;
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
* @since 2019-07-11
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class StatementSourceBill implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer billType;

    private String billNo;

    private LocalDate happenDate;

    private String statementNo;

    private LocalDate statementDate;

    private Long paymentAmount;

    private Long deductAmount;

    private Long statementAmount;

    private String companyCode;

    private String companyName;

    private String costCode;

    private String costName;

    private Integer statementState;

    private String relationNo;

    private String purchaseNo;

    private String happenPlaceCode;

    private String happenPlaceName;

    private String settlePlaceCode;

    private String settlePlaceName;


}
