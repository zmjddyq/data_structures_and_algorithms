package com.demo.structures.Recursive;

/**
 * @author zmj
 * @date 2020/6/16 9:46
 * @Description 通过递归实现迷宫
 */
public class Maze {
    public static void main(String[] args) {
        MazeDemo mazeDemo = new MazeDemo(5,1);
        mazeDemo.walk(1,2);
    }
}
class MazeDemo{
    private static int[][] maze;
    /**
     * 终点（endX，endY）
     */
    private int endY = 6;
    private int endX = 5;
    private static final int HAVEN_NOT_WALKED = 0;
    private static final int WALL = 1;
    private static final int HAVEN_WALKED = 2;
    private static final int NOWHERE = 3;
    private static final int END = 6;
    /**
     * 创建迷宫
     *   0   1   2   3   4   5   6
     *0  1	 1	 1	 1	 1	 1	 1
     *1  1	 0	 0	 0	 1   0	 1
     *2  1	 0	 1	 0	 1	 0	 1
     *3  1	 1	 1	 0	 1	 0	 1
     *4  1	 0 	 0	 0	 1	 0	 1
     *5  1	 0	 0	 1	 0	 0	 1
     *6  1	 0	 0	 0	 0	 0	 1
     *7  1	 1	 1	 1	 1	 1	 1
     */
    static {
        maze = new int[8][7];
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = WALL;
            maze[i][6] = WALL;
        }
        for (int i = 1;i < maze[0].length - 1; i++){
            maze[0][i] = WALL;
            maze[7][i] = WALL;
        }
        for (int i = 1; i < 5; i++) {
            maze[i][4] = WALL;
        }
        maze[3][1] = WALL;
        maze[3][2] = WALL;
        maze[2][2] = WALL;
        maze[6][5] = WALL;
        maze[5][3] = WALL;
        System.out.println("迷宫初始化");
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }
    public MazeDemo() {
    }
    public MazeDemo(int endX, int endY) {
        this.endY = endY;
        this.endX = endX;
    }

    /**
     * 开始走迷宫
     * 0：还未走过  1：墙  2: 可以通行 3： 此路不通
     * 策略：下 → 右 → 上 → 左
     * @param x
     * @param y
     */
    public boolean walk(int x, int y){
        //到达终点
        if (maze[endY][endX] == HAVEN_WALKED) {
            maze[endY][endX] = END;
            System.out.println("到达终点");
            for (int[] ints : maze) {
                for (int anInt : ints) {
                    System.out.print(anInt + "\t");
                }
                System.out.println();
            }
            return true;
        } else {
            if (maze[y][x] == HAVEN_NOT_WALKED) {
                maze[y][x] = HAVEN_WALKED;
                // 下
                if (walk(x,y + 1)){
                    return true;
                } else if (walk(x + 1 ,y)){
                    return true;
                } else if (walk(x,y - 1)){
                    return true;
                } else if (walk(x - 1,y)){
                    return true;
                } else {
                    maze[y][x] = NOWHERE;
                    return false;
                }
            } else {
                return false;
            }
        }

    }
}

