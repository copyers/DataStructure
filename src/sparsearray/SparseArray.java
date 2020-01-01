package sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0：表示没有棋子，1表示黑子2表示蓝子
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[1][5] = 1;
        chessArr[2][6] = 2;
        //输出原始的二维数组
        System.out.println("==========原始数组==========");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转稀疏数组
        //1. 遍历二维数组得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2. 创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int count = 0; //count用于记录是第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        System.out.println("==========稀疏数组==========");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组转二维数组
        /*
         *
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
         * 2.在读取稀疏数组后几行的数据，并赋值给原始的二维数组
         * */
        int[][] recoveryArray = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            recoveryArray[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("==========稀疏数组恢复后的原始数组==========");
        for (int[] row : recoveryArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


    }
}
