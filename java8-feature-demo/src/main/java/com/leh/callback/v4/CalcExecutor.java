package com.leh.callback.v4;

/**
 * @Description 老太太找小红帮忙计算
 * @Param 
 * @return
 *
 *
 * 回看一下上一章的代码，我们发现小红牌超级计算器的add方法需要的参数是两个整型变量和一个Student对象，
 * 但是老婆婆她不是学生，是个小商贩啊，这里肯定要做修改。这种情况下，我们很自然的会想到继承和多态。
 * 如果让小明这个学生和老婆婆这个小商贩从一个父类进行继承，
 * 那么我们只需要给小红牌超级计算器传入一个父类的引用就可以啦。
 *
 * 不过，实际使用中，考虑到java的单继承，并且不希望把自身太多东西暴漏给别人，
 * =====》》》 这里使用从接口继承的方式配合内部类来做。
 *
 * 换句话说，小红希望以后继续向班里的小朋友们提供计算服务，
 * 同时还能向老婆婆提供算账服务，甚至以后能够拓展其他人的业务，
 * 于是她向所有的顾客约定了一个办法，
 * 用于统一的处理，也就是自己需要的操作数和做完计算之后应该怎么做。
 * 这个统一的方法，小红做成了一个接口，提供给了大家，代码如下：
 *
 **/
public interface CalcExecutor {
    void fillBlank(int a, int b, int result);
}