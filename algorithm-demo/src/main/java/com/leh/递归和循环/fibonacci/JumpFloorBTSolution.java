package com.leh.递归和循环.fibonacci;

/**
 * @Auther: leh
 * @Date: 2019/11/6 11:09
 * @Description: 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
 */
public class JumpFloorBTSolution {


    /**
     * 分析:
     * n个台阶会有一次n阶的跳法。
     * f(n-1) = f(0) + f(1) + f(2) + f(3) + ... + f((n-1)-1)
     * f(n) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2) + f(n-1)
     * 可以得出：
     * f(n) = 2 * f(n-1)
     *
     * 7) 得出最终结论,在n阶台阶，一次有1、2、...n阶的跳的方式时，总得跳法为：
     *
     *          | 1       ,(n=0 )
     *
     * f(n) =   | 1       ,(n=1 )
     *
     *          | 2*f(n-1),(n>=2)
     */
    public int JumpFloorBT_1(int target) {
        if (target <= 0) {
            return -1;
        } else if (target == 1) {
            return 1;
        } else {
            return 2 * JumpFloorBT_1(target - 1);
        }
    }

    /**
     * f(1) = 1;
     * f(n) = 1 + f(1) + f(2) + f(3) + ... + f(n-2) + f(n-1);
     *
     * @param target
     * @return
     */
    public int JumpFloorBT_2(int target) {
        int sum = 0;
        int tmp = 1;
        for (int i = 1; i <= target; i++) {
            sum = sum + tmp;
            tmp = sum;
        }
        return sum;
    }


    /**
     * 每个台阶可以看作一块木板，让青蛙跳上去，n个台阶就有n块木板，最后一块木板是青蛙到达的位子， 必须存在，
     * 其他 (n-1) 块木板可以任意选择是否存在，则每个木板有存在和不存在两种选择，(n-1) 块木板
     * 就有 [2^(n-1)] 种跳法，可以直接得到结果
     * Math.pow(底数,几次方)
     *
     * @param target
     * @return
     */
    public int jumpFloor_3(int target) {
        if (target <= 0) {
            return 0;
        }
        return (int) Math.pow(2, target - 1);
    }

    /**
     * 2^(n-1)可以用位移操作进行，更快
     * @param target
     * @return
     */
    public int jumpFloor_4(int target) {
        if (target <= 0) {
            return 0;
        }
        int a = 1;
        return a << (target - 1);
    }


}
