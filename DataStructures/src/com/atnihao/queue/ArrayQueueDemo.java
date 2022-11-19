package com.atnihao.queue;

import java.util.Scanner;

/**
 * @author nihao
 * @create 2022-11-19 18:50
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
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
                        int head = queue.getHead();
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
//使用数组模拟队列，编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;//数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，分析出front是指向队列列头的前一个位置
        rear = -1;//指向队列尾部，指向队尾的数据(即就是队列的最后一个数据)
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }
    //判断队列是否空
    public boolean isEmpty(){
        return front == rear;
    }
    //添加数据到队列--入队列
    public void add(int n){
        //先判断队满
        if(isFull()){
            throw new RuntimeException("队列已满~");
        }
        rear++;//让rear后移
        arr[rear] = n;//
    }
    //获取队列的数据--出队列
    public int getQueue(){
        //先判断队是否空
        if(isEmpty()) {
            throw new RuntimeException("队列没有数据~");
        }
        front++;//front后移
        return arr[front];
    }
    //显示队列的所有数据
    public void show(){
        //遍历
        if(isEmpty()){
            System.out.println("队列是空的~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\t\n",i,arr[i]);
        }
    }
    //显示队列的头数据
    public int getHead(){
        //判断
        if(isEmpty()){
            throw new RuntimeException("队列没有数据");
        }
        return arr[front+1];
    }

}