package com.leh.guava;

import com.google.common.collect.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @Auther: leh
 * @Date: 2019/9/5 10:06
 * @Description:
 */
public class GuavaListAndMap {

    @Before
    public void before() {
        System.out.println("before。。。。。。。");
    }

    @After
    public void after() {
        System.out.println("after。。。。。。。");
    }

    @Test
    public void test() {
        System.out.println("testing。。。。。。。");
    }

    @Test
    public void guava() {
        /*
            常规方式建立 map 和 list
         */
        List<String> list = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        /*
            guava方式
            用到的是lists,maps的工具类
         */
        List<String> gList = Lists.newArrayList();
        Map<String, Integer> gMap = Maps.newHashMap();

        List<Integer> gList2 = Lists.newArrayList(1, 2, 3, 4);

        /*
             不可变list和map
             不可变集合，顾名思义就是说集合是不可被修改的。集合的数据项是在创建的时候提供，并且在整个生命周期中都不可改变。

        　　  为什么要用immutable对象？immutable对象有以下的优点：
        　　　　1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
        　　　　2.线程安全的：immutable对象在多线程下安全，没有竞态条件
        　　　　3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
        　　　　4.可以被使用为一个常量，并且期望在未来也是保持不变的

　　          immutable对象可以很自然地用作常量，因为它们天生就是不可变的对于immutable对象的运用来说，
             它是一个很好的防御编程（defensive programming）的技术实践。
         */

        //创建
        ImmutableList<Integer> immList = ImmutableList.of(1, 2, 3, 10);


    }

    @Test
    public void testGuavaImmutable() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list：" + list);

        ImmutableList<String> imlist = ImmutableList.copyOf(list);
        System.out.println("imlist：" + imlist);

        ImmutableList<String> imOfList = ImmutableList.of("jack", "tom", "mick");

        System.out.println("imOfList：" + imOfList);

        ImmutableSortedSet imSortedSet = ImmutableSortedSet.of("a", "c", "f", "r", "b", "s");

        System.out.println("imSortedSet:" + imSortedSet);

        list.add("baby");
        System.out.println("list add a item after list:" + list);
        System.out.println("list add a item after imlist:" + imlist);

        ImmutableSet<Color> imColorSet = ImmutableSet.<Color>builder()
                .add(new Color(0, 255, 255))
                .add(new Color(0, 0, 0))
                .build();

        System.out.println("imColorSet:" + imColorSet);
    }


    /**
     * SortedMap
     */
    @Test
    public void whenUsingSortedMap_thenKeysAreSorted() {
        ImmutableSortedMap<String, Integer> salary = new ImmutableSortedMap
                .Builder<String, Integer>(Ordering.natural())
                .put("John", 1000)
                .put("Jane", 1500)
                .put("Adam", 2000)
                .put("Tom", 2000)
                .build();

        assertEquals("Adam", salary.firstKey());
        assertEquals(2000, salary.lastEntry().getValue().intValue());
    }


    /**
     * BiMap
     * Map 翻转了
     */
    @Test
    public void whenCreateBiMap_thenCreated() {
        BiMap<String, Integer> words = HashBiMap.create();
        words.put("First", 1);
        words.put("Second", 2);
        words.put("Third", 3);

        assertEquals(2, words.get("Second").intValue());
        assertEquals("Third", words.inverse().get(3));
    }

    /**
     * Multimap
     就是一个键多个值
     */
    @Test
    public void whenCreateMultimap_thenCreated() {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("fruit", "apple");
        multimap.put("fruit", "banana");
        multimap.put("pet", "cat");
        multimap.put("pet", "dog");

        System.out.println(multimap);
        //{fruit=[apple, banana], pet=[cat, dog]}
    }

    /**
     * TABLE
     * 当我们需要多个索引的数据结构的时候，
     * 通常情况下，我们只能用这种丑陋的Map<FirstName, Map<LastName, Person>>来实现。
     * 为此Guava提供了一个新的集合类型－Table集合类型，
     * 来支持这种数据结构的使用场景。Table支持“row”和“column”，而且提供多种视图。
     */

    @Test
    public void whenCreatingTable_thenCorrect() {
        Table<String,String,Integer> distance = HashBasedTable.create();
        distance.put("London", "Paris", 340);
        distance.put("New York", "Los Angeles", 3940);
        distance.put("London", "New York", 5576);
        System.out.println(distance);
        assertEquals(3940, distance.get("New York", "Los Angeles").intValue());

        /*
            before。。。。。。。
            {London={Paris=340, New York=5576}, New York={Los Angeles=3940}}
            after。。。。。。。
         */
    }




}


