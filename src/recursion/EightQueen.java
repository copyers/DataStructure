package recursion;

public class EightQueen {

    //定义一个max表示共有多少个皇后
    //定义一个一维数组，保存放置之后的结果，比如arr={0,4,7,5,2,6,1,3}
    static int count;
    int max = 8;
    int[] array = new int[max];

    public static void main(String[] args) {

        //测试八皇后
        EightQueen eightQueen = new EightQueen();
        eightQueen.check(0);
        System.out.printf("总共有%d种解法",count);

    }

    private void check(int n) {
        if (n == max) {  //n=8，其实8个已经放好了
            print();
            return;
        }

        //依次放入皇后并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n放到改行的第一列
            array[n] = i;
            //判断是否冲突
            if (judge(n)) {
                //放n+1个皇后，即开始递归
                check(n + 1);
            }

            //如果冲突，继续执行arr[n] = i + 1,直到找到不冲突位置
        }

    }


    //检查是否冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //1.array[i] = array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //2.Math.abs(n-i) == Math.abs(array[n]-array[i])表示是否在同一斜线(斜率是否为1)
            //3.判断是否在同一行没有必要，n每次都在递增

            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }


    //输出结果
    private void print() {
        for (int data : array) {
            System.out.print(data + "\t");
        }
        System.out.println();
        count++;
    }

}
