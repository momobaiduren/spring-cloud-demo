package com.springcloud.computer.handler;

import com.springcloud.computer.ComputerHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author zhanglong
 * @Version V1.0.0
 * @Date 2019-09-13
 */
@Slf4j
@Component
public class UnpaymentReportComputerHandler implements ComputerHandler {
//    @Value("${report.nonPayment.pageSize:30}")
    private double pageSize = 30D;

//    @Autowired
//    private ReportUnpaymentDetailService reportUnpaymentDetailService;

    @Override
    public void execut(List<Integer> shardingData) {
        shardingData.forEach(pageNum -> {
//            Page<ReportUnpaymentDetailDO> page = new Page<>(pageNum, pageSize);
//            try {
//                List<ReportUnpaymentDetailDO> reportUnpaymentDetailDOS = reportUnpaymentDetailService.selectNonpaymentReportPage(page);
//                reportUnpaymentDetailDOS.forEach(reportUnpaymentDetailDO -> {
//                    try {
//                        reportUnpaymentDetailService.sumReportUnPaymentDetail(reportUnpaymentDetailDO.getCompanyCode(), reportUnpaymentDetailDO.getSupplierCode());
//                    } catch (Exception e) {
//                        log.error(String.format("公司编码[ %s ]; 供应商编码[ %s ] 数据处理异常失败", reportUnpaymentDetailDO.getCompanyCode(), reportUnpaymentDetailDO.getSupplierCode()));
//                    }
//                });
//            } catch (Exception e) {
//                log.error(String.format("第 %d 页数据处理异常失败", pageNum));
//            }
        });
        compensate();
    }

    @Override
    public int count() {
        int nonpaymentTotalCount =  1212; //reportUnpaymentDetailService.getNonpaymentTotalCount();
        return Double.valueOf(Math.ceil(nonpaymentTotalCount / pageSize)).intValue();
    }

    @Override
    public void compensate() {

    }
}
