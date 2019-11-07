package com.leh.recursionwithloop.fibonacci;

/**
 * @Auther: leh
 * @Date: 2019/11/6 10:34
 * @Description:
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0），n<=39
 */
public class FibonacciSolution {

    /**
     * Method1:递归法
     * 分析：
     * 斐波那契数列的标准公式为：F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)（n>=3，n∈N*）
     * 根据公式可以直接写出
     * 优点：简单明了
     * 缺点：递归会重复计算上一次计算过的数据，时间复杂度高
     * 复杂度：
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public int Fibonacci_one(int n) {
        if (n <= 1) {
            return n;
        }
        return Fibonacci_one(n - 1) + Fibonacci_one(n - 2);
    }

    /**
     * 优化递归
     * 1. 分析 由于递归会重复计算大量相同数据，我们用个数组把结果存起来！
     * 2. 复杂度：
     *    时间复杂度：O(n)
     *    空间复杂度：O(n)
     * @param n
     * @return
     */
    public int Fibonacci_two(int n) {
        int ans[] = new int[40];
        ans[0] = 0;
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            ans[i] = ans[i-1] + ans[i-2];
        }
        return ans[n];
    }

    /**
     * 优化存储
     * 1. 分析
     * 其实我们可以发现每次就用到了最近的两个数，所以我们可以只存储最近的两个数
        sum 存储第 n 项的值
        one 存储第 n-1 项的值
        two 存储第 n-2 项的值
       2. 复杂度：
        时间复杂度：O(n)
        空间复杂度：O(1)
     * @param n
     * @return
     */
    public int Fibonacci_three(int n) {
        if (n <= 1) {
            return n;
        }
        int sum = 0;
        int one = 1;
        int two = 0;
        for (int i = 2; i <n ; i++) {
            sum = one + two;
            two = one;
            one = sum;
        }
        return sum;
    }

}
