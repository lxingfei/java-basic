package com.leh.callback.v5;

/**
 * @Description 用老师问问题，学生回答问题为例子，解释一下回调机制的使用
 * 首先需要一个接口callback，以及一个继承了接口的类Teacher。
 * Teahcer类中有另一个类Student的对象，在Teacher中执行函数，
 * 会调用student中的方法，student执行对应方法后再回调Teacher中重写的接口方法，
 * 这样就完成了一次回调方法
 * @Param
 * @return
 **/
public interface Callback {
    void tellAnswer(int answer);
}
