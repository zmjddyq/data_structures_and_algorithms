package com.demo.structures.Recursive;

/**
 * @author zmj
 * @date 2020/6/16 11:01
 * @Description 递归解决八皇后问题
 */
public class EightQueens {
    public static void main(String[] args) {
        int start = EightQueensDemo.start();
        System.out.println("共有：" + start + "种解法");
    }
}

 class EightQueensDemo{
    private static final int MAX_SIZE = 8;
    private static int count = 0;
    private static int[] Checkerboard = new int[MAX_SIZE];

    /**
     * 成功 → 打印成功方案，统计成功方案个数
     * @param
     */
    private static void succeed(){
        count++;
        System.out.print("第" + count + "种解法为: ");
        for (int i : Checkerboard) {
            System.out.print("\t" + (i + 1));
        }
        System.out.println();
    }

    /**
     * 判断是否有冲突
     * @param n 此时放的是第n个皇后
     * @return 如果和已有的皇后在同一条直线上或者在一条斜线上返回false
     */
    private static boolean conflict(int n){
        for (int i = 0; i < n; i++) {
            if (Checkerboard[i] == Checkerboard[n] || Math.abs(n - i) == Math.abs(Checkerboard[n] - Checkerboard[i])) {
                return false;
            }
        }
        return true;
    }

    private static void check(int n){
        if (n == MAX_SIZE) {
            succeed();
            return;
        }

        for (int i = 0; i < MAX_SIZE; i++) {
            Checkerboard[n] = i;
            // 如果不冲突，则放置下一个皇后
            if (conflict(n)) {
                check(n + 1);
            }
            // 冲突则皇后位置往后移动一格
        }
    }

    public static int start(){
        check(0);
        return count;
    }
}
