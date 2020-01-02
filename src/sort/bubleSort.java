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

        bubleSort(arrs);
        System.out.println(Arrays.toString(arrs));

        Date now1 = new Date();
        String date2 = format.format(now1);
        System.out.println("排序后时间：" + date2);

    }

    public static void bubleSort(int[] arrs) {

        int length = arrs.length;
        int temp;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++)
            {
                if (arrs[j] > arrs[j + 1]) {
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j+1] = temp;
                }
            }
        }


    }

}
