package linkedlist;

import java.util.concurrent.ForkJoinPool;

public class Josephu {

    public static void main(String[] args) {

        CircleLinedList circleLinedList = new CircleLinedList();
        circleLinedList.addBoy(5);
        circleLinedList.show();
        circleLinedList.countNode(1,2,5);

    }

}


class CircleLinedList {

    //创建一个first节点，first节点当前没有编号；
    private Boy first = new Boy(-1);

    public void addBoy(int nums) {
        //对nums做数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //根据编号创建节点
        Boy curBoy = null; //辅助指针，帮助构建与遍历环形链表
        for (int i = 1; i < nums + 1; i++) {
            Boy boy = new Boy(i);
            //如果是第一个
            if (i == 1) {
                first = boy;   //第一个节点构成环
                first.setNext(boy);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }

    }

    public void show() {
        //判断环形链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，使用辅助指针完成遍历
        Boy curboy = first;
        while (true) {
            System.out.printf("该节点编号%d\n", curboy.getNo());
            if (curboy.getNext() == first) {
                break;
            }
            curboy = curboy.getNext();  //后移
        }
    }

    /*
     * startNo:开始位置
     * countNum:计数次数
     * nums:总长度
     * */
    public void countNode(int startNo, int countNum, int nums) {

        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        //定义辅助指针，帮助完成出圈
        Boy helper = first;
        //辅助指针应指向first前一节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        //根据startNo决定first与helper指向位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true){

            if (helper == first){
                break;
            }

            //移动到出圈位置
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.printf("节点%d出圈\n",first.getNo());

            //出圈
            first=first.getNext();
            helper.setNext(first);

        }
        System.out.printf("还剩节点%d\n",first.getNo());
    }

}


class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
