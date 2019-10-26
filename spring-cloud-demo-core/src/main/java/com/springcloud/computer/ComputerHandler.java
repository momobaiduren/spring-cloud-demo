package com.springcloud.computer;

import java.util.List;

/**
 * @author  zhanglong on 2019-09-13  12:08 上午
 * @version V1.0
 */
public interface ComputerHandler {
   /**
    * create by ZhangLong on 2019-09-13
    * description 分片处理数据
    */
   default void execut(List<Integer> shardingData){
      shardingData.forEach(shardingNum -> {
         throw new UnsupportedOperationException("unimplemented interface ComputerHandler and override execut method");
      });
      compensate();
   };
   /**
    * create by ZhangLong on 2019-09-13
    * description 未处理数据总量（除去新生成的，新数据在统一在补偿中处理）
    */
   int count();
   /**
    * create by ZhangLong on 2019-09-13
    * description 数据补偿，防止在执行过程中新添加的数据未做处理(必须实现，但可以为空实现)
    */
   default void compensate(){
      throw new UnsupportedOperationException("unimplemented interface ComputerHandler and override compensate method");
   };


}

