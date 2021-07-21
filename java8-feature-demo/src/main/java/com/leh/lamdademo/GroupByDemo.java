package com.leh.lamdademo;

import com.alibaba.fastjson.JSONObject;
import com.leh.model.Student;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 先构造一个list<Student> 集合，分组的原理是把这个集合根据我们输入的条件划分为一个个小的集合
 * @Author lveh
 * @Date 2021/7/21 16:31
 * @Version 1.0
 **/
public class GroupByDemo {
    /**
     * 1.单一条件进行集合划分，比如我根据我的名字，或者性别，将集合划分
     * 2.复合条件，两个或者多个条件，需要自己构造一个合并条件的方法，返回值为String
     */
    public static void main(String[] args) {
        Student student1 = new Student(200, "lveh", "1001");
        Student student2 = new Student(220, "lveh", "1001");
        Student student3 = new Student(500, "lveh", "2001");
        Student student4 = new Student(504, "lveh", "2001");
        List<Student> studentList =new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        //单一分组条件,根据code
        Map<String, List<Student>> singleMap = studentList.stream().collect(Collectors.groupingBy(Student::getCode));
        System.out.println("singleMap===" + JSONObject.toJSONString(singleMap));

        //单一分组条件,根据username
        Map<String, List<Student>> singleNameMap = studentList.stream().collect(Collectors.groupingBy(Student::getUsername));
        System.out.println("singleNameMap===" + JSONObject.toJSONString(singleNameMap));

        //组合分组条件
        Map<String, List<Student>> complexMap = studentList.stream().collect(Collectors.groupingBy(e -> fetchGroupKey(e)));
        System.out.println("complexMap===" + JSONObject.toJSONString(complexMap));
        List<Student> studentList1 = complexMap.get("lveh+1001");
        List<Student> studentList2 = complexMap.get("lveh+2001");

        System.out.println("studentList1===" + JSONObject.toJSONString(studentList1));
        System.out.println("studentList2===" + JSONObject.toJSONString(studentList2));

    }
    private static String fetchGroupKey(Student student){
        return student.getUsername() +"+"+ student.getCode();
    }

    /**
     * output:
     * singleMap==={"1001":[{"age":200,"code":"1001","username":"lveh"},{"age":220,"code":"1001","username":"lveh"}],"2001":[{"age":500,"code":"2001","username":"lveh"},{"age":504,"code":"2001","username":"lveh"}]}
     * singleNameMap==={"lveh":[{"age":200,"code":"1001","username":"lveh"},{"age":220,"code":"1001","username":"lveh"},{"age":500,"code":"2001","username":"lveh"},{"age":504,"code":"2001","username":"lveh"}]}
     * complexMap==={"lveh+2001":[{"age":500,"code":"2001","username":"lveh"},{"age":504,"code":"2001","username":"lveh"}],"lveh+1001":[{"age":200,"code":"1001","username":"lveh"},{"age":220,"code":"1001","username":"lveh"}]}
     * studentList1===[{"age":200,"code":"1001","username":"lveh"},{"age":220,"code":"1001","username":"lveh"}]
     * studentList2===[{"age":500,"code":"2001","username":"lveh"},{"age":504,"code":"2001","username":"lveh"}]
     */

}
