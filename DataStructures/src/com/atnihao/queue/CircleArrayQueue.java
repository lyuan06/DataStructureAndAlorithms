package com.atnihao.queue;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author nihao
 * @create 2022-11-20 16:06
 */
public class CircleArrayQueue {

    public static void main(String[] args) {
        //测试
        //创建一个队列
        CircleQueue queue = new CircleQueue(4);//最大是4，但实际只能放3个有效数据
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //打印一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队头元素");
            key = scanner.next().charAt(0);
            switch(key){
                case 's':
                    queue.show();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    try{
                        queue.add(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int queue1 = queue.getQueue();
                        System.out.println(queue1);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("队头数据是：%d",head);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序结束~");

    }
}

//使用数组模拟循环队列
class CircleQueue {
    private int maxSize;//数组的最大容量
    private int front;//队列头：指向队列的第一个元素，初始值为0
    private int rear;//队列尾：指向队列的最后一个元素的后一个位置，希望空出一个空间作为约定，初始值为0
    private int[] arr;//该数组用于存放数据，模拟队列

    public CircleQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否空
    public boolean isEmpty(){
        return front == rear;
    }


    public void add(int n){
        if(isFull()){
            throw new RuntimeException("队列已满~");
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，为了避免数组越界必须取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        int value = 0;
        //判断队列是否空
        if(isEmpty()){
            throw new RuntimeException("队列是空的");
        }
        //1.先把front对应的值保存到一个临时变量中
        //2.再把front后移，考虑取模
        //3.再返回临时变量
        value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列数据
    public void show(){
        if(isEmpty()){
            System.out.println("队列是空的");
            return;
        }
        //遍历
        //取模才可以循环起来
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n",i % maxSize,arr[i % maxSize]);
        }
    }
    //数组有效元素的个数
    public int size(){
        return (maxSize - front + rear) % maxSize;
    }

    //显示队首元素
    public int headQueue(){
        if(isFull()) {
            throw new RuntimeException("队列是空的~");
        }
        return arr[front];
    }
}