package com.leh.bitopertion;

/**
 * @Auther: leh
 * @Date: 2019/11/7 11:27
 * @Description:输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示
 */
public class NumberOf1 {


    /*
       从n的2进制形式的最右边开始判断是不是1
     * 该解法如果输入时负数会陷入死循环，
     * 因为负数右移时，在最高位补得是1
     * 二本题最终目的是求1的个数，那么会有无数个
     * 1了。
     */
    //-------------负数右移最高位补1，可能陷入死循环的解法---------------------
    public static int NumberOf1_CanNotUse(int n) {
        int count = 0;
        while (n != 0) {
            /*
             * 用1和n进行位与运算，
             * 结果要是为1则n的2进制形式
             * 最右边那位肯定是1，否则为0
             */
            if ((n & 1) == 1) {
                count++;
            }
            //把n的2进制形式往右推一位
            n = n >> 1;
        }
        return count;
    }

    public static int NumberOf1_CanUse(int n) {
        int count = 0;
        for (int i = 0; i < Integer.toBinaryString(n).length(); i++) {
            if((n >> i & 1) == 1){
                count++;
            }
        }
        return count;
    }

    //---------------正解--------------------------------
    //思想：用1（1自身左移运算，其实后来就不是1了）和 n 的每位进行位与，来判断1的个数
    private static int NumberOf1_low(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    //--------------------最优解----------------------------
    public static int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }
        return count;
    }


    public static void main(String[] args) {
        //使用 n=10,二进制形式为1010，则1的个数为 2;
        // -10 11111111111111111111111111110110
        int n = -10;
        System.out.println(n + "的二进制中1的个数：" + NumberOf1(n));
        System.out.println(n + "的二进制中1的个数：" + NumberOf1_CanUse(n));

        String result = Integer.toBinaryString(n);
        System.out.println(result);
        System.out.println(result.length());
    }


    public int NumberOf1_low2(int n) {
        String str = null;
        int count = 0;
        if(n < 0){
            //负数的补码 = 负数的绝对值的原码 + 1
            str = Integer.toBinaryString(~(-n) + 1);
        } else{
            str = Integer.toBinaryString(n);
        }
        char[] arr = str.toCharArray();
        for(int i = 0 ; i<= str.length() - 1; i++){
            if(str.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }

}
