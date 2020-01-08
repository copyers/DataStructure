package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class bubleSort {

    public static void main(String[] args) {

//        int[] arrs = {2, 5, 1, 6, 9, 20, 0};

        //随机生成int数组
        int arrsLength = 800;
        int[] arrs = new int[arrsLength];
        for (int i = 0; i < arrsLength; i++) {
            int random = (int) (Math.random() * 1000);

            arrs[i] = random;
        }


//
        System.out.println(Arrays.toString(arrs));
//        System.out.println("=======经过冒泡排序之后======");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date now = new Date();
//        String date1 = format.format(now);
//        System.out.println("排序前时间：" + date1);

        long startTime = System.currentTimeMillis();
//        bubleSort(arrs);
//        improvedBubleSort(arrs);
//        selectSort(arrs);
//        shellSort(arrs);
        insertSort(arrs);
//        shellImproveSort(arrs);
//        8.623s
//        improved1BubbleSort(arrs);
//        improved2BubbleSort(arrs);
//        7.536s
//        improved3BubbleSort(arrs);
        System.out.println(Arrays.toString(arrs));
        long endTime = System.currentTimeMillis();
        long duraTime = endTime - startTime;
//        Date now1 = new Date();
//        String date2 = format.format(now1);
        System.out.println("排序耗费时间：" + (double) duraTime / 1000 + 's');

    }

    //普通的冒泡排序：比较相邻元素之间的大小，如果发现当前顺序与所期望的顺序不一致则进行交换
    public static void bubbleSort(int[] arrs) {

        //接收数组长度
        int length = arrs.length;
        //定义一个临时变量用于交换
        int temp;

        //for循环遍历数组元素，外层为交换的轮数，内循环为每轮比较的次数
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                //判断当前数如果大于其后面的数时进行交换（因为我们的顺序是从小到大，只有当前数小于等于后一个数时不用交换）
                if (arrs[j] > arrs[j + 1]) {
                    //通过临时变量来进行交换，如果不使用临时变量直接赋值会造成覆盖
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                }
            }
        }
    }

    //在每轮比较中增加一个判断标识，如果当前一轮中一次数据交换都没有发生，则说明数据已经有序，后面的轮数也不用再进行，直接break跳出循环
    public static void improved1BubbleSort(int[] arrs) {

        //接收数组长度
        int length = arrs.length;
        //定义一个临时变量用于交换
        int temp;
        //判断是否进行了数据交换的标识，0为发生了数据交换，1表示没有发生交换（即数据当前顺序符合期望顺序）
        int flag = 0;

        //for循环遍历数组元素，外层为交换的轮数，内循环为每轮比较的次数
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                //判断当前数如果大于其后面的数时进行交换（因为我们的顺序是从小到大，只有当前数小于等于后一个数时不用交换）
                if (arrs[j] > arrs[j + 1]) {
                    //通过临时变量来进行交换，如果不使用临时变量直接赋值会造成覆盖
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                    //上面表示发生了数据交换，则把flag置为1
                    flag = 1;
                }
            }
            //当前轮完成之后，立即判断该轮是否进行过交换，如果flag为0则说明没有交换，数组已经有序直接跳出for循环，反之则继续进行冒泡排序
            if (flag == 0) {
                break;
            }

        }

    }

    //在每轮比较中增加一个判断标识，如果当前一轮中一次数据交换都没有发生，则说明数据已经有序，后面的轮数也不用再进行，直接break跳出循环
    //当产生了数据交换则记录当前交换的位置作为下一轮起始位置
    public static void improved2BubbleSort(int[] arrs) {

        //接收数组长度
        int length = arrs.length;
        //定义一个临时变量用于交换
        int temp;
        //判断是否进行了数据交换的标识，0为发生了数据交换，1表示没有发生交换（即数据当前顺序符合期望顺序）
        int flag = 0;
        //pos用于记录最后一次交换的位置
        int pos = 0;
        //innerlength用于记录内层比较的位置
        int innerlength = length - 1;

        //for循环遍历数组元素，外层为交换的轮数，内循环为每轮比较的次数
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < innerlength; j++) {
                //判断当前数如果大于其后面的数时进行交换（因为我们的顺序是从小到大，只有当前数小于等于后一个数时不用交换）
                if (arrs[j] > arrs[j + 1]) {
                    //通过临时变量来进行交换，如果不使用临时变量直接赋值会造成覆盖
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                    //上面表示发生了数据交换，则把flag置为1
                    flag = 1;
                    //记录当前轮最后交换的位置
                    // 因为这里我选择的顺序是从小到大，所以是后面的顺序是最先确定，下一轮判断到该位置即可
                    // 如果选择的顺序是从大到小，则前面的顺序是最先确定，下一轮要从该位置开始
                    pos = j;
                }
            }

            //下一轮循环至该位置即可
            innerlength = pos;

            //当前轮完成之后，立即判断该轮是否进行过交换，如果flag为0则说明没有交换，数组已经有序直接跳出for循环，反之则继续进行冒泡排序
            if (flag == 0) {
                break;
            }

        }

    }


    //在每轮比较中增加一个判断标识，如果当前一轮中一次数据交换都没有发生，则说明数据已经有序，后面的轮数也不用再进行，直接break跳出循环
    //当产生了数据交换则记录当前交换的位置作为下一轮起始位置，同时在每轮中增加寻找最小数的过程
    public static void improved3BubbleSort(int[] arrs) {

        //接收数组长度
        int length = arrs.length;
        //定义一个临时变量用于交换
        int temp;
        //判断是否进行了数据交换的标识，0为发生了数据交换，1表示没有发生交换（即数据当前顺序符合期望顺序）
        int flag = 0;
        //pos用于记录最后一次交换的位置
        int pos = 0;
        //寻找最小值的下标
        int minindex = 0;
        //innerlength用于记录内层比较的位置
        int innerlength = length - 1;
        //定义一个临时变量用于交换最小数
        int tempmin = 0;
        //用于记录最小数交换的位置
        int minnext = 0;

        //for循环遍历数组元素，外层为交换的轮数，内循环为每轮比较的次数
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < innerlength; j++) {
                //判断当前数如果大于其后面的数时进行交换（因为我们的顺序是从小到大，只有当前数小于等于后一个数时不用交换）
                if (arrs[j] > arrs[j + 1]) {
                    //通过临时变量来进行交换，如果不使用临时变量直接赋值会造成覆盖
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                    //上面表示发生了数据交换，则把flag置为1
                    flag = 1;
                    //记录当前轮最后交换的位置
                    // 因为这里我选择的顺序是从小到大，所以是后面的顺序是最先确定，所以下一轮判断到该位置即可
                    // 如果选择的顺序是从大到小，则前面的顺序是最先确定，下一轮要从该位置开始
                    pos = j;
                }
            }

            //下一轮循环至该位置即可
            innerlength = pos;

            //当前轮完成之后，立即判断该轮是否进行过交换，如果flag为0则说明没有交换，数组已经有序直接跳出for循环，反之则继续进行冒泡排序
            if (flag == 0) {
                break;
            }

            //反向寻找最小值
            for (int j = innerlength; j > minindex; j--) {
                tempmin = arrs[j];
                arrs[j] = arrs[j - 1];
                arrs[j - 1] = tempmin;
                flag = 1;
                //记录作为下一次的起始位置
                minnext = j;
            }
            minindex = minnext;

            if (flag == 0) {
                break;
            }

        }

    }


    public static void selectSort(int[] arrs) {

        //记录最小值索引，存储最小值的临时变量，数组长度
        int minIndex, min,length;
        length = arrs.length;


        for (int i = 0; i < length - 1; i++) {
            //初始最小值索引与最小值
            minIndex = i;
            min = arrs[minIndex];

            for (int j = i + 1; j < length; j++) {
                if (arrs[j] < arrs[minIndex]) {
                    minIndex = j;
                    min = arrs[j];
                }
            }
            if (minIndex != i) {
                arrs[minIndex] = arrs[i];
                arrs[i] = min;
            }
        }
    }

    public static void insertSort(int[] arrs) {

        int insertVal, insertIndex;

        for (int i = 1; i < arrs.length; i++) {

            insertVal = arrs[i];
            insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arrs[insertIndex]) {
                arrs[insertIndex + 1] = arrs[insertIndex];
                insertIndex--;
            }

            arrs[insertIndex + 1] = insertVal;
        }

    }

    public static void shellSort(int[] arrs) {

        int temp = 0;
        for (int gap = arrs.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arrs.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arrs[j] > arrs[j + gap]) {
                        temp = arrs[j];
                        arrs[j] = arrs[j + gap];
                        arrs[j + gap] = temp;
                    }
                }

            }

        }

    }

    public static void shellImproveSort(int[] arrs) {


        for (int gap = arrs.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arrs.length; i++) {
                int j = i;
                int temp = arrs[j];
                while (j - gap >= 0 && temp < arrs[j - gap]) {
                    arrs[j] = arrs[j - gap];
                    j -= gap;
                }
                arrs[j] = temp;
            }

        }

    }

}
