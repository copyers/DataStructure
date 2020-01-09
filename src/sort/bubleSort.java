package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class bubleSort {

    public static void main(String[] args) {

        int[] arrs = {2, 5, 1, 6, 9, 20, 0,109281};

        System.out.println(Arrays.toString(arrs));
        System.out.println("=======经过冒泡排序之后======");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date now = new Date();
        String date1 = format.format(now);
        System.out.println("排序前时间：" + date1);

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

    }

    public static void bubleSort(int[] arrs) {

        int length = arrs.length;
        int temp;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                }
            }
        }
    }

    public static void improvedBubleSort(int[] arrs) {

        int length = arrs.length;
        int temp;
        int flag = 0;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {

                if (arrs[j] > arrs[j + 1]) {
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                    flag = 1;
                }
            }
            if (flag == 0) {
                break;
            }

        }

    }

    public static void selectSort(int[] arrs) {

        for (int i = 0; i < arrs.length - 1; i++) {

            int minIndex = i;
            int min = arrs[i];

            for (int j = i + 1; j < arrs.length; j++) {
                if (arrs[j] < min) {
                    min = arrs[j];
                    minIndex = j;
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
