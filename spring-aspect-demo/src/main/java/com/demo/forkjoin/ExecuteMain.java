package com.demo.forkjoin;

/**
 * @author ZhangLong on 2019/11/19  9:01 下午
 * @version V1.0
 */
public class ExecuteMain {
//    public static void main(String[] args) {
//        long x = System.currentTimeMillis();
//        ForkJoinHandler forkJoinHandler = new ForkJoinHandlerImpl();
//        ForkJoinPool pool = new ForkJoinPool();
//        ForkJoinTask<Void> taskFuture =  pool.submit(new ForkJoinAction1(0, forkJoinHandler.count(null), forkJoinHandler));
//        boolean done = taskFuture.isDone();
//        System.out.println(done);
////        try {
////            Integer result = taskFuture.get();
////            System.out.println("result = " + result);
////        } catch (InterruptedException | ExecutionException e) {
////            e.printStackTrace(System.out);
////        }
////        System.out.println(System.currentTimeMillis() - x);
//
//    }

//    public static void main(String[] args) {
//        int num = 13;
//        for (int i = 1; i <= num / 2 + 1; i++) {
//            System.out.print(i+ "  ");
//            for (int j = 1; j <= num; j++) {
//                if (j <= num / 2 + i && j > num / 2 + 1 - i) {
//                    System.out.print("*");
//                } else {
//                    System.out.print(" ");
//                }
//            }
//
//            System.out.println("");
//        }
//    }
    public static void main(String[] args) {
        int num = 19;
        int row = num / 2 ;
        for (int i = 1; i <= row * 3; i++) {
            for (int j = 1; j <= num * 3; j++) {
                if (i < row && j <= num * 3 / 2 + i && j > num * 3 / 2 + 1 - i) {
                    System.out.print("*");
                } else if (i >= row && i< 2*row -3 && j > (i-row)*3 && j<= num * 3 - 3*(i - row)){
                    System.out.print("*");
                }else if (i >= 2*row -3 && i < 3* row-5 ){
                    if (j > 3*(row - 3) - 3*(i- 2*row +3) && j<= num * 3 - 3*(row - 3) + 3*(i- 2*row +3)){
                        System.out.print("*");
                    }else {
                        System.out.print("-");
                    }
                }else {
                    System.out.print("-");
                }

            }

            System.out.println("");
        }
    }

//    public static void main(String[] args) {
//        int touHigh = 7;
//        int jianHigh = 26 ;
//        int kuang =53;
//        for (int i=1;i<=touHigh+jianHigh;i++){
//            for(int j =1;j<=kuang;j++){
//                //上三角
//                if (i<=touHigh) {
//                    if(j>=(kuang/2+1)+1-i && j<=(kuang/2+1)-1+i){
//                        System.out.print("*");
//                    }else{
//                        System.out.print("-");
//                    }
//                }
//                //上三角一下部分
//                if (i>touHigh&&i<=jianHigh){
//                    if(j>=(kuang/2+1)+1-i&&j<=kuang-3*(i-touHigh)){System.out.print("*");
//                    }
//                    else if(j<=(kuang/2+1)-1+i&&j>=0+3*(i-touHigh)){System.out.print("*");
//                    }
//                    else {System.out.print("-");
//                    }
//                }
//            }
//            System.out.println("");
//        }
//    }
}
