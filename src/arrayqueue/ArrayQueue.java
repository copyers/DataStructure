package arrayqueue;

import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args) {

        //创建一个数组队列,有效数据为maxSize - 1，rear后面的一个位置永远为空
        MyArrayQueue arrayQueue = new MyArrayQueue(3);
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符

            switch (key) {

                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序退出！");


    }

}


//使用数组模拟环形队列——编写一个MyArrayQueue
class MyArrayQueue {

    private int maxSize; //表示数组的最大容量
    private int front;   //队列头
    private int rear;    //队列尾(其下一位置为空，作为队列是否满的标记)
    private int[] arr;   //用于存放数据

    public MyArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满了，不能加入数据！");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //抛出运行时异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //front 目前指向当前头
        //1. 先把front的值保留在临时变量
        //2. 将front 后移
        //3. 将之前的临时变量返回
        int value = arr[front];   //front后移
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据！");
        } else {
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
            }
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，不是取数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front];
    }

}
