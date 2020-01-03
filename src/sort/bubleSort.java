package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class bubleSort {

    public static void main(String[] args) {

        int[] arrs = {2, 5, 1, 6, 9, 20, 0};

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
        shellImproveSort(arrs);
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

}
