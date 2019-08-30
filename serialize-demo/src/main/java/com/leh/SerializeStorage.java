package com.leh;

import java.io.*;

/**
 * @Auther: leh
 * @Date: 2019/8/30 11:35
 * @Description:序列化存储规则
 *
 * java 序列化机制：
 * Java 序列化机制为了节省磁盘空间，具有特定的存储规则，当写入文件的为同一对象时，
 * 并不会再将对象的内容进行存储，而只是再次存储一份引用，上面增加的 5 字节的存储空间就是新增引用和一些控制信息的空间。
 * 反序列化时，恢复引用关系，使得中的 t1 和 t2 指向唯一的对象，二者相等，输出 true。该存储规则极大的节省了存储空间。
 */
public class SerializeStorage implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result2.obj"));

            SerializeStorage storage = new SerializeStorage();

            //试图将对象两次写入文件
            out.writeObject(storage);

            out.flush();

            System.out.println(new File("result2.obj").length());

            out.writeObject(storage);

            out.close();

            System.out.println(new File("result2.obj").length());


            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result2.obj"));

            //从文件依次读出两个文件

            SerializeStorage t1 = (SerializeStorage) oin.readObject();

            SerializeStorage t2 = (SerializeStorage) oin.readObject();

            oin.close();

            //判断两个引用是否指向同一个对象
            System.out.println(t1 == t2);

            /**
             * output:
             * 45
               50
               true
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
