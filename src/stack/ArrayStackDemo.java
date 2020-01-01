package stack;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class ArrayStackDemo {


    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 添加数据");
            System.out.println("pop: 取出数据");
            System.out.println("请输入选择 ");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    System.out.println();
                    break;
                case "push":
                    System.out.println("请输入一个数据");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");

    }

}

class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean Empty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int data) {

        if (isFull()) {
            System.out.println("栈已满，不能存入数据");
            return;
        }
        top ++;
        stack[top] = data;
    }

    public int pop() {
        int value = stack[top];
        top -= 1;
        return value;
    }


    public void list() {
        if (Empty()) {
            System.out.println("栈为空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d", i, stack[i]);
        }
    }

}
