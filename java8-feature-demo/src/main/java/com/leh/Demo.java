package com.leh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author lveh
 * @Date 2021/5/20 11:43
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        String str = "abc";
        setStr(str);
        System.out.println(str);
        List<?> list = new ArrayList<>();
        List<String> listString = new ArrayList<>();
        listString = (List<String>) list;
        list = listString;

/*        List<Object> list2 = new ArrayList<>();
        List<String> listString2 = new ArrayList<>();
        list2 = listString2;
        listString2 = list2;  编译不通过*/
       /* replaceBlank();
        String str = "123456  7989\n" +
                "90   90，abc,cde\n" +
                "uuio";*/
        //getStringNoBlank(str);
        //getStringNoBlank222(str);
        // spiltStrParam(str);
    }

    private static String setStr(String str) {
        str = "efg";
        return str;
    }

    public static List<String> spiltStrParam(String param) {
        List<String> list = new ArrayList<>();
        if (param == "" || param == null) {
            return list;
        }
        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(param);
        String strWithBlank = m.replaceAll(",").replaceAll("，",",");
        String regEx = "[' ']+"; // 一个或多个空格
        Pattern p1 = Pattern.compile(regEx);
        Matcher m1 = p1.matcher(strWithBlank);
        String strNoBlank = m1.replaceAll(",").trim();
        System.out.println("strNoBlank = " + strNoBlank);

        for (String item : strNoBlank.split(",")) {
            list.addAll(Stream.of(item.split(";"))
                    .collect(Collectors.toList()));
        }
        return list;
    }

    public static void replaceBlank()
    {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        String str="I am a, I am Hello ok, \n new line ffdsa!";
        System.out.println("before:"+str);
        Matcher m = p.matcher(str);
        String after = m.replaceAll("");
        System.out.println("after:"+after);
    }


    public static List<String> getStringNoBlank222(String param) {
        List<String> list = new ArrayList<>();
        if (param == "" || param == null) {
            return list;
        }
        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(param);
        String strWithBlank = m.replaceAll(",").replaceAll("，",",");
        System.out.println("strWithBlank = " + strWithBlank);
        String regEx = "[' ']+"; // 一个或多个空格
        Pattern p1 = Pattern.compile(regEx);
        Matcher m1 = p1.matcher(strWithBlank);
        String strNoBlank = m1.replaceAll(",").trim();
        System.out.println("strNoBlank = " + strNoBlank);
        for (String item : strNoBlank.split(",")) {
            list.addAll(Stream.of(item.split(";"))
                    .collect(Collectors.toList()));
        }
        return list;
    }


    public static String getStringNoBlank(String str) {
        if (str != null && !"".equals(str)) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll(",").replaceAll("，",",");
            System.out.println("strNoBlank = " + strNoBlank);
            List<String> list = new ArrayList<>();
            for (String item : strNoBlank.split(",")) {
                list.addAll(Stream.of(item.split(";"))
                        .collect(Collectors.toList()));
            }

            return strNoBlank;
        } else {
            return str;
        }
    }
}
