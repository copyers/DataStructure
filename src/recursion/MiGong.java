package recursion;

public class MiGong {

    private static int count = 0;

    public static void main(String[] args) {

        //先创建一个二维数组，模拟迷宫
        //地图
        //上下置为1
        int[][] map = new int[8][7];


        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出当前地图
        for (int[] row : map) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        System.out.println("==============迷宫找路之后的线路==============");
        //使用递归回溯给小球找到路
        setWay(map,1,1);

        //输出新的地图，小球走过并标识的地图
        for (int[] row : map) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        System.out.printf("总共走了%d步",count);

    }

    //使用递归回溯找路
    //map 表示地图 ij表示从哪个位置开始出发
    //如果小球能到map[6][5]则表示通路找到
    //约定：当map[i][j]为0表示该店没有走过，当为1表示为墙，2表示通路可以走，3表示改点已经走过，但是走不通
    //通路策略下右上左，如果该点走不通再回溯
    public static boolean setWay(int[][] map, int i, int j) {

        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) { //表示当前这个点没有走过
                //按照策略下右上左
                map[i][j] = 2;  //假定可以走通
                count++;
                if (setWay(map, i + 1, j)) {     //向下走
                    return true;
                }
                if (setWay(map, i, j + 1)) {  //向右走
                    return true;
                }
                if (setWay(map, i - 1, j)) {    //向上走
                    return true;
                }
                if (setWay(map, i, j - 1)) {    //向左走
                    return true;
                } else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }

        }

    }

}
