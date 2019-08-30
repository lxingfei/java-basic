package com.leh;

import java.io.*;

/**
 * 静态变量序列化
 * @Auther: leh
 * @Date: 2019/8/30 11:05
 * @Description:序列化并不保存静态变量
 * 最后的输出是 10，对于无法理解的读者认为，打印的 staticVar 是从读取的对象里获得的，应该是保存时的状态才对。
 * 之所以打印 10 的原因在于序列化时，并不保存静态变量，
 * 这其实比较容易理解，序列化保存的是对象的状态，静态变量属于类的状态，
 * 因此 序列化并不保存静态变量。
 */
public class SerializeWithoutStatic implements Serializable {

    private static final long serialVersionUID = 1L;

    public static int staticVar = 5;

    public static void main(String[] args) {
        try {
            //初始时staticVar为 5
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));

            out.writeObject(new SerializeWithoutStatic());

            out.close();

            //序列化后修改为10
            SerializeWithoutStatic.staticVar = 10;

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result.obj"));

            SerializeWithoutStatic t = (SerializeWithoutStatic) oin.readObject();

            oin.close();

            //再读取，通过t.staticVar打印新的值
            System.out.println(t.staticVar);

            /**
             * output:
             * 10
             */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
