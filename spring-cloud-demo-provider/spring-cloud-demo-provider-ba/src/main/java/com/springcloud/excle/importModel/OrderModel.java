package com.springcloud.excle.importModel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.springcloud.excle.ExcelModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
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
public class OrderModel extends ExcelModel implements Serializable {
    @NotNull
    @ExcelProperty("单号")
    private String orderNo;
    @ExcelProperty("商品ID")
    private Long productId;
    @ExcelProperty("商品编码")
    private String productCode;
    @ExcelProperty("商品数量")
    private Integer productNum;
    @ExcelProperty("商品总额")
    private Integer productTotal;
    @ExcelProperty("商品单价")
    private Integer productPrice;
    @ExcelProperty("用户名称")
    private String userName;


}
