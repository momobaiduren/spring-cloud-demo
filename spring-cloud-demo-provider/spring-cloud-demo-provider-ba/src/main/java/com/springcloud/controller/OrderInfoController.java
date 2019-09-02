package com.springcloud.controller;


import com.springcloud.auto.entity.OrderInfo;
import com.springcloud.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */
@RestController
@RequestMapping("/api/order")
public class OrderInfoController {
  @Autowired
  private IOrderInfoService iOrderInfoService;
  @PostMapping("/createAndPayOrder")
  public OrderInfo createAndPayOrder(OrderInfo orderInfo){
    return iOrderInfoService.createAndPayOrder(orderInfo);
  }

}
