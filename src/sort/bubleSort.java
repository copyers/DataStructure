package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class bubleSort {

    public static void main(String[] args) {


//        int[] arrs = {2, 5, 1, 6, 9, 20, 0,109281};

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
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date now = new Date();
//        String date1 = format.format(now);
//        System.out.println("排序前时间：" + date1);

        long startTime = System.currentTimeMillis();
//        bubleSort(arrs);
//        improvedBubleSort(arrs);
//        selectSort(arrs);
//        shellSort(arrs);

//        insertSort(arrs);
//        shellImproveSort(arrs);
//        quickSort(arrs, 0, arrs.length - 1);
//        int[] temp = new int[arrs.length];
//        mergeSort(arrs, 0, arrs.length - 1, temp);
        radixSort(arrs);
        System.out.println(Arrays.toString(arrs));


        Date now1 = new Date();
        String date2 = format.format(now1);
        System.out.println("排序后时间：" + date2);

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

    //通过两个指针移动实现快速排序
    public static void quickSort(int[] arrs, int left, int right) {

        //定义两个指针遍历
        int l = left;
        int r = right;
        //选取中间位置的数作为基准值
        int pivot = arrs[(left + right) / 2];
        //定义一个中间变量，以备交换使用
        int temp = 0;

        while (l < r) {

            //判断左边比基准值大的数，并记录索引
            while (arrs[l] < pivot) {
                l += 1;
            }

            //判断右边比基准值大的数，并记录索引
            while (arrs[r] > pivot) {
                r -= 1;
            }

            //如果没有找到上述符合条件的数则退出while循环
            if (l >= r) {
                break;
            }

            //否则进行交换
            temp = arrs[l];
            arrs[l] = arrs[r];
            arrs[r] = temp;
        }

        //为了避免上面的while循环陷入死循环，递归之前先做如下判断
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //左边递归
        if (left < r) {
            quickSort(arrs, left, r);
        }
        //右边递归
        if (l < right) {
            quickSort(arrs, l, right);
        }
    }


    //归并排序合并
    public static void merge(int[] arrs, int left, int mid, int right, int[] temp) {

        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right) {
            if (arrs[i] < arrs[j]) {
                temp[t] = arrs[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arrs[j];
                t += 1;
                j += 1;
            }
        }

        while (i <= mid) {
            temp[t] = arrs[i];
            t += 1;
            i += 1;
        }

        while (j <= right) {
            temp[t] = arrs[j];
            t += 1;
            j += 1;
        }

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arrs[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }


    //归并排序分
    public static void mergeSort(int[] arrs, int left, int right, int[] temp) {

        int mid = (left + right) / 2;
        if (left < right) {
            mergeSort(arrs, left, mid, temp);
            mergeSort(arrs, mid + 1, right, temp);
            merge(arrs, left, mid, right, temp);
        }

    }

    //基数排序
    public static void radixSort(int[] arrs) {

        //1、准备桶
        int[][] bucket = new int[10][arrs.length];

        //2、每个桶准备索引
        int[] bucketElementCounts = new int[10];

        //3、获取数组最大数字的最长位数
        int max = arrs[0];
        for (int i = 0; i < arrs.length; i++) {
            if (arrs[i] > max) {
                max = arrs[i];
            }
        }

        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            for (int j = 0; j < arrs.length; j++) {
                int digitOfElement = (arrs[j] / n) % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arrs[j];
                bucketElementCounts[digitOfElement]++;
            }


            //取数据返回到数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arrs[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }

            System.out.println("第"+(i+1)+"轮的排序结果为："+Arrays.toString(arrs));

        }


    }

}
