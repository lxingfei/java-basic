package com.leh.callback.v2;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 10:44
 * @Version 1.0
 **/
public class TestV2 {
    /**
     * 明明超纲了好不好！这时候小明同学明显不能再像上面那样靠心算来完成了，
     * 正在懵逼的时候，班上的小红同学递过来一个只能计算加法的计算器（奸商啊）！！！！
     * 而小明同学恰好知道怎么用计算器，于是通过计算器计算得到结果并完成了填空。
     **/
    public static void main(String[] args)
    {
        int a = 168;
        int b = 291;
        StudentV2 s = new StudentV2("小明");
        s.fillBlank(a, b);
    }

    /**
     * output:
     * 小明使用计算器:168 + 291 = 459
     * 该过程中仍未涉及到回调机制，但是部分小明的部分工作已经实现了转移，由计算器来协助实现。
     **/
}
