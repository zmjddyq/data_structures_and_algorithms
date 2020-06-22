package com.demo.structures.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zmj
 * @date 2020/6/22 20:20
 * @Description
 */
public class GraphDemo {
    public static void main(String[] args) {
        ArrayList<String> vertexList = new ArrayList<>(Arrays.asList("A","B","C","D","E"));
        Graph graph = new Graph(vertexList);
        // 添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        // 显示边
        graph.showEdges();
    }
}

class Graph {
    private ArrayList<String> vertexList;
    private int numOfEdges;
    private int[][] edges;

    /**
     * 获得权值
     * @param v1
     * @param v2
     * @return 0:无关联,1:关联
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    /**
     * 显示edges
     */
    public void showEdges() {
        int temp = 0;
        System.out.print(" ");
        System.out.println(vertexList);
        for (int[] edge : edges) {
            System.out.print(vertexList.get(temp++));
            System.out.println(Arrays.toString(edge));
        }
    }


    /**
     * 设置顶点的联系
     *
     * @param v1  联系顶点
     * @param v2  联系顶点
     * @param weight 0:取消关联,1:添加关联
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight == 1 ? 1 : 0;
        edges[v2][v1] = weight == 1 ? 1 : 0;
        numOfEdges = weight == 1 ? numOfEdges + 1 : numOfEdges - 1;
    }

    /**
     * 添加顶点
     *
     * @param value
     */
    public void insertVertex(String value) {
        if (value == null || value.length() == 0) {
            throw new RuntimeException("输入有误！添加失败");
        }
        vertexList.add(value);
    }

    /**
     * 获取顶点数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    public Graph(int maxSize) {
        this.vertexList = new ArrayList<>(maxSize);
        this.edges = new int[maxSize][maxSize];
    }

    public Graph(ArrayList<String> vertexList) {
        this.vertexList = vertexList;
        int maxSize = vertexList.size();
        this.edges = new int[maxSize][maxSize];
    }
}