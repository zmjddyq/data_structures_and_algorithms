package com.demo.structures.structure;

/**
 * @author zmj
 * @date 2020/6/10 10:41
 * @Description 二维数组 ←→ 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        // 建立二维数组
        int[][] array = new int[11][11];
        // 白子为1，黑子为2
        array[5][5] = 1;
        array[2][4] = 2;
        array[7][3] = 1;
        System.out.println("原始二维数组");
        for (int[] row : array) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        System.out.println("\n二维数组 → 稀疏数组");
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0){
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array[0].length;
        sparseArray[0][2] = sum;
        int row = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0){
                    sparseArray[row][0] = i;
                    sparseArray[row][1] = j;
                    sparseArray[row][2] = array[i][j];
                    row++;
                }
            }
        }

        System.out.println("行\t列\t值");
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }

        System.out.println("\n稀疏数组 → 二维数组");

        int[][] array1 = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            array1[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        for (int[] row1 : array1) {
            for (int data : row1) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}