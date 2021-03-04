package com.leh.cacheclean.lru;/**
 * @author futao
 * Created on 2021/3/3.
 */

import java.util.HashMap;

/**
 * @Description
 * 我们的思路就是哈希表+双向链表
 *
 * 哈希表用于满足题目时间复杂度O(1)的要求，双向链表用于存储顺序
 * 哈希表键值类型：
 * 双向链表的节点中除了value外还需要包含key，因为在删除最久未使用的数据时，需要通过链表来定位hashmap中应当删除的键值对
 * 一些操作：双向链表中，在后面的节点表示被最近访问新加入的节点放在链表末尾，addNodeToLast(node)若容量达到上限，去除最久未使用的数据，removeNode(head.next)若数据新被访问过，比如被get了或被put了新值，把该节点挪到链表末尾，moveNodeToLast(node)
 * 为了操作的方便，在双向链表头和尾分别定义一个head和tail节点。
 * @Author lveh
 * @Date 2021/3/3 19:35
 * @Version 1.0
 **/
public class LRUCache {
    private int capacity;
    private HashMap<Integer, ListNode> hashmap;
    private ListNode head;
    private ListNode tail;

    private class ListNode{
        int key;
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(){
        }
        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        hashmap = new HashMap<>();
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToLast(ListNode node){
        node.prev = tail.prev;
        node.prev.next = node;
        node.next = tail;
        tail.prev= node;
    }

    private void moveNodeToLast(ListNode node){
        removeNode(node);
        addNodeToLast(node);
    }

    public int get(int key) {
        if(hashmap.containsKey(key)){
            ListNode node = hashmap.get(key);
            moveNodeToLast(node);
            return node.val;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(hashmap.containsKey(key)){
            ListNode node = hashmap.get(key);
            node.val = value;
            moveNodeToLast(node);
            return;
        }
        if(hashmap.size() == capacity){
            hashmap.remove(head.next.key);
            removeNode(head.next);
        }

        ListNode node = new ListNode(key, value);
        hashmap.put(key, node);
        addNodeToLast(node);
    }
}
