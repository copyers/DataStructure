package linkedlist;

import java.util.Stack;

public class MyLinkedListDemo {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        System.out.println(getLength(singleLinkedList.getHeadNode()));
        singleLinkedList.list();

//        HeroNode newheroNode = new HeroNode(20, "小卢", "玉麒麟~~");

        System.out.println("============");

        printreverseNode(singleLinkedList.getHeadNode());
//        reverseNode(singleLinkedList.getHeadNode());
//        singleLinkedList.list();

//        singleLinkedList.del(1);
//        singleLinkedList.list();

//        HeroNode res = findLastIndexNode(singleLinkedList.getHeadNode(), 1);

//        System.out.println(getLength(singleLinkedList.getHeadNode()));

//        System.out.println("res = " + res);

    }

    //从尾到头打印单链表---栈实现

    public static void printreverseNode(HeroNode head) {

        if (head.next == null) {
            return;
        }

        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> heroNodes = new Stack<HeroNode>();
        HeroNode temp = head.next;
        while (temp != null) {
            heroNodes.push(temp);
            temp = temp.next;  //向后移
        }

        //将栈中节点出栈
        while (heroNodes.size() > 0){
            System.out.println(heroNodes.pop());
        }


    }


    //单链表反转
    public static void reverseNode(HeroNode head) {

        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助的指针，用于遍历原链表
        HeroNode cur = head.next;
        HeroNode next = null;  //指向当前节点【cur】的下一节点
        HeroNode reverseHead = new HeroNode(0, "", "");

        //遍历原来的链表，并按逆序挂在reverseHead上
        while (cur != null) {
            next = cur.next;//暂时保存，后面使用
            cur.next = reverseHead.next;   //链接当前节点与之前节点
            reverseHead.next = cur;         //链接当前节点与头节点
            cur = next;  //让cur后移
        }

        //将head.next 指向reverse.next
        head.next = reverseHead.next;

    }

    //查找单链表中的倒数第k个节点【新浪】
    //1.接收head节点，同时接受index
    //2.index表示倒数第index个节点
    //3.遍历获取总长度
    //4.得到size后，从链表的第一个开始遍历（size-index）个
    //5.如果找到了，则返回，否则为null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {

        //判断链表是否为空，如果为空则返回null
        if (head.next == null) {
            return null;
        }

        //获取链表的长度
        int size = getLength(head);
        //size-index位置就是倒数第k个节点
        if (index <= 0 || index > size) {
            return null;
        }

        //定义辅助变量,for循环定位到index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }


    //方法：获取单链表的节点个数
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

}

//定义SingleLinkedList管理单链表
class SingleLinkedList {
    //先初始化一个头节点，头节点不要随意修改
    private HeroNode head = new HeroNode(0, "", "");


    public HeroNode getHeadNode() {
        return head;
    }

    //添加节点到单向链表
    //不考虑顺序编号，先找到最后一个节点，最后节点的next指向新节点即可
    public void add(HeroNode heroNode) {
        //1.创建一个temp节点辅助遍历
        HeroNode temp = head;

        //2.遍历链表
        while (true) {
            //找到链表的最后节点
            if (temp.next == null) {
                break;
            }
            //如果没有找到，继续后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向最后的节点
        temp.next = heroNode;
    }

    //第二种添加的方式，安装顺序添加
    public void addByOrder(HeroNode heroNode) {

        //因为头节点不能动，仍然通过辅助节点进行遍历
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {  //已经是链表最后
                break;
            }
            if (temp.next.no > heroNode.no) {   //已经找到，就在temp后面
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;//说明变化存在
                break;
            }
            temp = temp.next;  //后移，遍历当前链表
        }

        //通过flag的值进行判断是否可以添加
        if (flag) {
            System.out.printf("准备插入的%d已经存在了，不能重复添加\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }


    //遍历显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //因为头节点不能移动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }

    }

    //修改节点信息，根据no编号来修改
    public void update(HeroNode newheroNode) {

        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;   //到链表最后
            }
            if (temp.no == newheroNode.no) {
                flag = true;  //找到要修改的节点
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newheroNode.name;
            temp.nickname = newheroNode.nickname;
        } else {
            System.out.printf("没有找到编号为%d的节点，不能操作\n", newheroNode.no);
        }
    }

    //删除节点
    //head不能动，需要一个temp节点辅助遍历
    //用temp与需要删除的节点比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有找到待删除的节点，不能操作");
                return;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) { //找到节点
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }


    }

}


//带头节点的单向链表
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，重写toString方法


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
