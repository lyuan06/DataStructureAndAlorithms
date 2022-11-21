package com.atnihao.LinkedList;

/**
 * @author nihao
 * @create 2022-11-21 12:04
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建结点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //添加元素：按照编号加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);

        //显示
        singleLinkedList.listShow();

        //测试修改结点的代码
        HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表~");
        singleLinkedList.listShow();

        //删除结点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后的链表~");
        singleLinkedList.listShow();
    }
}
//定义一个SingleLinkedList类，管理英雄
class SingleLinkedList{
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //将结点添加到单向链表--只能加到最后一个位置
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后结点
    //2.将最后的结点的next指向新的结点
    public void add(HeroNode heroNode){
        //因为头结点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表
        while(true){
            if(temp.next == null){//找到了最后一个
                break;
            }
            //如果没有找到最后一个，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后一个
        //将这个结点的next指向新的结点
        temp.next = heroNode;
    }

    //第二种方式在添加英雄的时候，根据排名将英雄插入指定位置上
    //（如果没有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        //因为头结点不能动，因此我们仍然通过一个辅助变量来帮助找到添加的位置
        //因为是单链表，因此我们找到的temp是位于添加位置的前一个结点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; //标记添加的编号是否已经存在，默认是不存在
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到，就在temp后面插入
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;//说明编号已经存在
                break;
            }
            temp = temp.next;//后移遍历链表
        }
        //判断flag的值
        if(flag){
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n",heroNode.no);
        }else{//找到了位置
            heroNode.next = temp.next;//先把新的结点的next指向temp的next
            temp.next = heroNode;//再把temp.next指向新的结点
        }
    }

    //修改结点的信息，根据编号来修改，即no不改变
    //说明：
    //1.根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        //不为空
        //根据no，找到需要修改的结点
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//是否找到结点
        while(true){
            if(temp  == null){
                break;//已经遍历完了
            }
            if(temp.no == newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp =temp.next;
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("没有找到编号为%d的结点，不能修改",newHeroNode.no);
        }
    }

    //删除结点
    //思路
    //1.head不能动，需要一个辅助结点来找到待删除的结点的前一个结点
    //2.说明：在我们比较时，是temp.next.no和需要删除的结点的no比较
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                //找到待删除结点的前一个结点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            //删除
            temp.next = temp.next.next;
        }else{
            System.out.printf("要删除的%d结点不存在\n",no);
        }
    }

    //显示链表【遍历】
    public void listShow(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表是空的~");
            return;
        }
        //创建一个辅助变量来遍历
        HeroNode temp = head.next;//疑问：为什么不能指向头结点
        while(true){
            //判断链表是否到最后
            if(temp == null){
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }


}

//定义一个HeroNode类，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示内容，我们重写toString

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
