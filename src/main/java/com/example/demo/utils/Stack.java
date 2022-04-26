package com.example.demo.utils;

import java.util.Iterator;

public class Stack<T> implements Iterable<T>{
    //记录首结点
    private Node head;
    //栈中元素的个数
    private int N;
    //创建静态私有成员变量
    private static Stack instance = new Stack();
    
    private class Node{
        public T item;
        public  Node next;
        public Node(T item,Node next){
            this.item = item;
            this.next = next;
        }
    }

    private Stack(){
        this.head = new Node(null,null);
        this.N=0;
    }

    public static Stack getInstance() {
        return instance;
    }
    //判断当前栈元素个数是否为0
    public boolean isEmpty(){
        return N==0;
    }
    //获取栈中元素的个数
    public int size(){
        return N;
    }
    //把t元素压入栈
    public void push(T t){
        //找到首结点指向的第一个结点
        Node oldFirst = head.next;
        //创建新结点
        Node newNode = new Node(t,null);
        //让首结点指向新结点
        head.next =newNode;
        //让新结点指向原来的第一个结点
        newNode.next = oldFirst;
        //元素个数+1
        N++;
    }

    //弹出栈顶元素
    public T pop(){
        //找到首结点指向的第一个结点
        Node oldFirst = head.next;
        if(oldFirst==null){
            return null;
        }
        //让首结点指向原来的第一个结点
        head.next = oldFirst.next;
        //元素个数-1；
        N--;
        return  oldFirst.item;
    }

    //清空元素
    public void  clear() {
        this.head.next = null;
        this.N = 0;
    }
    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator{
        private Node n;

        public SIterator(){
            this.n = head;
        }
        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }

}
