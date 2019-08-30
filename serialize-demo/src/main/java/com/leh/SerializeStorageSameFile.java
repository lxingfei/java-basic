package com.leh;

import java.io.*;

/**
 * @Auther: leh
 * @Date: 2019/8/30 11:35
 * @Description:序列化存储规则
 *
 * java 序列化机制：
 * 案例代码的目的原本是希望一次性传输对象修改前后的状态
 * 结果输出都是100
 * 原因就是第一次写入对象以后，第二次再试图写的时候，虚拟机根据引用关系知道已经有一个相同对象已经写入文件，
 * 因此只保存第二次写的引用，所以读取时，都是第一次保存的对象。
 * 读者在使用一个文件多次 writeObject 需要特别注意这个问题
 *
 */
public class SerializeStorageSameFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private int i = 0;

    public static void main(String[] args) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result3.obj"));

            SerializeStorageSameFile sameFile = new SerializeStorageSameFile();

            //试图将对象两次写入文件

            sameFile.i = 100;

            out.writeObject(sameFile);

            out.flush();

            sameFile.i = 500;

            out.writeObject(sameFile);

            out.close();


            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result3.obj"));

            //从文件依次读出两个文件

            SerializeStorageSameFile t1 = (SerializeStorageSameFile) oin.readObject();

            SerializeStorageSameFile t2 = (SerializeStorageSameFile) oin.readObject();

            oin.close();

            //读取反序列化后对象的属性值
            System.out.println(t1.i);
            System.out.println(t2.i);

            /**
             * output:
             * 100
               100
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
