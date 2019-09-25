package com.leh.streamdemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @Auther: leh
 * @Date: 2019/9/25 11:08
 * @Description: 创建stream
 */
public class CreateStream {

    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);
        createStreamFromValues().forEach(System.out::println);
        createStreamFromArrays().forEach(System.out::println);
        createStreamFromFile().forEach(System.out::println);
        System.out.println("-----------------------------------");
        createStreamFromIterator().forEach(System.out::println);
        System.out.println("-----------------------------------");
        createStreamFromGenerate().forEach(System.out::println);
        System.out.println("************************************");
        createObjStreamFromGenerate().forEach(System.out::println);
    }

    /**
     * generate the stream object from collection
     *
     * @return
     */
    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("你好", "2020", "再见", "2019");
        return list.stream();
    }

    /**
     * generate the stream object from values
     *
     * @return
     */
    private static Stream<String> createStreamFromValues() {
        return Stream.of("你好", "2020", "再见", "2019");
    }

    /**
     * generate the stream object from arrays
     *
     * @return
     */
    private static Stream<String> createStreamFromArrays() {
        return Arrays.stream(new String[]{"你好", "2020", "再见", "2019"});
    }

    /**
     * generate the stream object from file
     *
     * @return
     */
    private static Stream<String> createStreamFromFile() {
        Path path = Paths.get("D:\\ideawork\\java-basic\\java8-feature-demo\\src\\main\\java\\com\\leh\\streamdemo" +
                "\\CreateStream.java");

        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
            try - resource 语法

        try (Stream<String> stream = Files.lines(path)) {
            return stream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */

    }

    /**
     * generate the stream object from Iterator
     *
     * @return
     */
    private static Stream<Integer> createStreamFromIterator() {
        //取100个 否则 无限生成
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2).limit(100);
        return stream;

    }

    /**
     * generate the stream object from Generate
     *
     * @return
     */
    private static Stream<Double> createStreamFromGenerate() {

        return Stream.generate(Math::random).limit(100);

    }


    /**
     * 自定义Stream
     * generate the obj stream object from Generate
     * @return
     */
    private static Stream<Obj> createObjStreamFromGenerate() {

        return Stream.generate(new ObjSupplier()).limit(100);

    }



    static class ObjSupplier implements Supplier<Obj> {
        int index = 0;
        private Random random = new Random(System.currentTimeMillis());
        @Override
        public Obj get() {
            index = random.nextInt(100);

            return new Obj(index, "Name->" + index);
        }
    }


    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }


}
