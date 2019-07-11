package com.springcloud.aspect;

import com.springcloud.interceptor.CompensableTransactionInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

/**
 * @title: TccCompensableAspect
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1020:09
 */


public class TccCompensableAspect implements Ordered {

  static final Logger LOG = LoggerFactory.getLogger(TccCompensableAspect.class.getSimpleName());
  /**
   * 通知顺序(默认：最高优先级).
   * 在“进入”连接点的情况下，最高优先级的通知会先执行（所以给定的两个前置通知中，优先级高的那个会先执行）。 在“退出”连接点的情况下，最高优先级的通知会最后执行。
   * 当定义在不同的切面里的两个通知都需要在一个相同的连接点中运行， 那么除非你指定，否则执行的顺序是未知的，你可以通过指定优先级来控制执行顺序。
   */
  private int order = Ordered.HIGHEST_PRECEDENCE; // 最高优先级（值较低的那个有更高的优先级）

  @Override
  public int getOrder() {
    return order;
  }
  public void setOrder(int order) {
    this.order = order;
  }

  /**
   * 可补偿事务拦截器
   */
  private CompensableTransactionInterceptor compensableTransactionInterceptor;

  /**
   * 定义切入点（包含切入点表达式和切点签名，切点用于准确定位应该在什么地方应用切面的通知，切点可以被切面内的所有通知元素引用）.
   */
  @Pointcut("@annotation(com.springcloud.annotation.Compensable)")
  public void compensableService() {

  }

  /**
   * 定义环绕通知（在一个方法执行之前和执行之后运行，第一个参数必须是 ProceedingJoinPoint类型，pjp将包含切点拦截的方法的参数信息）
   * @param pjp
   * @throws Throwable
   */
  @Around("compensableService()")
  public Object interceptCompensableMethod(ProceedingJoinPoint pjp) throws Throwable {
    LOG.debug("==>interceptCompensableMethod");
    return compensableTransactionInterceptor.interceptCompensableMethod(pjp);
  }

  /**
   * 设置可补偿事务拦截器.
   * @param compensableTransactionInterceptor
   */
  public void setCompensableTransactionInterceptor(CompensableTransactionInterceptor compensableTransactionInterceptor) {
    this.compensableTransactionInterceptor = compensableTransactionInterceptor;
  }

}
