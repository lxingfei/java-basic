package com.leh.juc.currmap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author lveh
 * @Date 2021/5/20 2:33
 * @Version 1.0
 **/
public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        putIfAbsentTest();

        removeByKeyAndValTest();

        computeIfAbsentTest();
    }

    /**
     * 如果key对应的value已经存在则返回value,不存放新value
     * 如果key对应value不存在，则放入value,并返回null
     */
    public static void putIfAbsentTest(){
        ConcurrentHashMap<String,String> map=new ConcurrentHashMap();
        map.put("123","2");
        System.out.println("putIfAbsentTest-----" + map.putIfAbsent("1231","22"));
        System.out.println("putIfAbsentTest-----" + map.putIfAbsent("1231","1231"));
        System.out.println("putIfAbsentTest-----" + map.putIfAbsent("123","123"));
        System.out.println("putIfAbsentTest-----" + map.get("123"));
        /**
         * output:
         * null
         * 22
         * 2
         * 2
         */
    }

    /**
     * 按照键值对删除元素
     */
    public static void removeByKeyAndValTest(){
        ConcurrentHashMap<String,String> map=new ConcurrentHashMap();
        map.put("123","2");
        map.remove("123","2w");
        System.out.println("testRemove-----" + map.get("123")); // 2
    }

    /**
     * 如果key对应的value为空则将用Function计算得到的值存入
     * 如果key对应的value不为空
     */
    public static void computeIfAbsentTest(){
        ConcurrentHashMap<String,String> map=new ConcurrentHashMap();
        map.put("456","2*****");
        String ret1 = map.computeIfAbsent("1231", (key) -> key + "113123");
        String ret2 = map.computeIfAbsent("456",(key)-> key+"======");
        System.out.println("computeIfAbsentTest  " + map.get("1231"));
        System.out.println("computeIfAbsentTest  " + map.get("456"));
        Set<Map.Entry<String,String>> entrySet= (Set<Map.Entry<String, String>>) map.entrySet();
        Iterator iterator=entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry entry= (Map.Entry) iterator.next();
            System.out.println("computeIfAbsentTest entry " + entry.getKey()+"-"+entry.getValue());
        }
    }

}
