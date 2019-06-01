package com.jakiro;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:类/接口 LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

   获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
   写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

  进阶:

   你是否可以在 O(1) 时间复杂度内完成这两种操作？

  示例:

        LRUCache cache = new LRUCache( 2 [缓存容量] )

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

 */
public class LRUCache {

    //双链表节点
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
    }

    //容量
    private int capacity;
    private Node first;
    private Node last;

    private Map<Integer,Node> map;

    public LRUCache(int capacity){
        this.capacity=capacity;
        map=new HashMap<>(capacity);
    }

    private void moveToHead(Node node){
        if(node==first){
            return;
        }else if(node==last){
            last.prev.next=null;
            last=last.prev;
        }else {
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }

        node.prev=first.prev;
        node.next=first;
        first.prev=node;
        first=node;
    }

    private void addToHead(Node node){
        if(map.isEmpty()){
            first=node;
            last=node;
        }else{
            node.next=first;
            first.prev=node;
            first=node;
        }
    }

    private void removeLast(){
        map.remove(last.key);
        Node prevNode=last.prev;
        if(prevNode!=null){
            prevNode.next=null;
            last=prevNode;
        }
    }

    public void put(int key,int value){
        Node node=map.get(key);

        if(node==null){
            node=new Node();
            node.key=key;
            node.val=value;

            if(map.size()==capacity){
                removeLast();
            }

            addToHead(node);
            map.put(key,node);
        }else {
            node.next=first;
            first.prev=node;
            first=node;
        }
    }

    public int get(int key){

        Node node=map.get(key);
        if(node==null){
            return -1;
        }
        moveToHead(node);

        return node.val;
    }
}
