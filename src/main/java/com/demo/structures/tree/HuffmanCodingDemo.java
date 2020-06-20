package com.demo.structures.tree;

import org.junit.Test;
import sun.applet.Main;
import sun.security.util.Length;

import java.util.*;

/**
 * @author zmj
 * @date 2020/6/20 16:20
 * @Description
 */
public class HuffmanCodingDemo {
    public static void main(String[] args) {
//        String message = "i like like like java do you like a java";
//        String message = "Talk is cheap,Show me the code";
        String message = "You build it, You run it.";
        byte[] bytes = message.getBytes();
        HuffmanCodingTree huffmanCodingTree = new HuffmanCodingTree();
/*        PriorityQueue<HuffmanNode> adjust = huffmanCodingTree.adjust(bytes);
        System.out.println(adjust.toString());*/
        System.out.println("树化后的根节点为： " + huffmanCodingTree.creatHuffmanTree(bytes));
        huffmanCodingTree.preTraversal();
        System.out.println("*********************************************");
        HashMap huffmanCoding = huffmanCodingTree.getHuffmanCoding();
        System.out.println(huffmanCoding);
        System.out.println("*********************************************");
        byte[] zip = huffmanCodingTree.zip(bytes, huffmanCoding);
        System.out.println(Arrays.toString(zip));
        System.out.println("*********************************************");
        String decode = huffmanCodingTree.decode(zip, huffmanCoding);
        System.out.println(decode);

    }
}

class HuffmanCodingTree {

    private HuffmanNode root;
    private HashMap<Byte, String> huffmanCodingMap = new HashMap<>();

    public String decode(byte[] bytes, HashMap<Byte, String> huffmanCodingMap) {
        boolean flag = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length - 1; i++) {
            // 判断是否是数组最后一个元素
            if (i == bytes.length - 2) {
                flag = false;
            }
            stringBuilder.append(byteToString(flag, bytes[i] ,bytes[bytes.length - 1]));
        }
        String s = stringBuilder.toString();
        StringBuilder result = new StringBuilder();
        HashMap<String, Byte> huffmanDecodeHashMap = adjustMap(huffmanCodingMap);
        while (s.length() > 0) {
            for (String key : huffmanDecodeHashMap.keySet()) {
                if (s.startsWith(key)) {
                    char c = (char) huffmanDecodeHashMap.get(key).byteValue();
                    result.append(c);
                    s = s.substring(key.length());
                }
            }
        }
        return result.toString();
    }

    /**
     * 将编码表转换为译码表
     * @return
     */
    private HashMap<String,Byte> adjustMap(HashMap<Byte, String> huffmanCodingMap){
        HashMap<String, Byte> stringByteHashMap = new HashMap<>();
        for (Map.Entry<Byte, String> byteStringEntry : huffmanCodingMap.entrySet()) {
            stringByteHashMap.put(byteStringEntry.getValue(),byteStringEntry.getKey());
        }
        return stringByteHashMap;
    }

    /**
     * 将十进制整数转为二进制
     *
     * @param flag
     * @param b
     * @return
     */
    private String byteToString(boolean flag, byte b, byte last) {
        int temp = b;
        temp |= 256;
        String s = Integer.toBinaryString(temp);
        if (flag) {
            return s.substring(s.length() - 8);
        }
        return s.substring(s.length() - last);
    }

    /**
     * 数据压缩
     *
     * @param bytes            要压缩的字节数组
     * @param huffmanCodingMap 编码表
     * @return
     */
    public byte[] zip(byte[] bytes, HashMap<Byte, String> huffmanCodingMap) {
        StringBuilder stringBuilder = new StringBuilder();
        // 将消息转为编码数据
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodingMap.get(b));
        }
        // 获取字节数组长度
        // 向上取整
        int size = (int) Math.ceil(stringBuilder.length() / 8.0);
        // 创建字节数组(加一位判断最后一位的长度)
        byte[] zipBytes = new byte[size + 1];
        zipBytes[size] = 8;
        // 创建字节数组临时指针
        int temp = 0;
        // 填充字节数组
        for (int i = 0; i < stringBuilder.length(); i = i + 8) {
            // 当最后一个字节不足八位
            if (i + 8 > stringBuilder.length()) {

                zipBytes[temp++] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
                zipBytes[temp] = (byte) (stringBuilder.length() - i);
                break;
            }
            // 取出八位字节转为十进制整数
            // 将转换完的十进制整数加入字节数组
            zipBytes[temp++] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);

        }
        return zipBytes;
    }

    /**
     * 获取哈夫曼编码表
     */
    public HashMap getHuffmanCoding() {
        if (root == null) {
            System.out.println("请先创建哈夫曼树!");
            return null;
        }
        getHuffmanCoding(root, "", new StringBuilder());
        return huffmanCodingMap;
    }

    /**
     * 获取哈夫曼编码表
     *
     * @param node
     * @param code
     * @param stringBuilder
     */
    private void getHuffmanCoding(HuffmanNode node, String code, StringBuilder stringBuilder) {
        // 重置StringBuilder
        StringBuilder newStringBuilder = new StringBuilder(stringBuilder);
        newStringBuilder.append(code);
        if (node != null) {
            // 判断是否是到达叶子节点
            if (node.character == null) {
                // 未到达，继续遍历
                // 左递归遍历
                getHuffmanCoding(node.left, "0", newStringBuilder);
                // 右递归遍历
                getHuffmanCoding(node.right, "1", newStringBuilder);
            } else {
                // 到达则将叶子节点放入map
                huffmanCodingMap.put(node.character, newStringBuilder.toString());
            }
        }
    }

    /**
     * 将字符数组变成对应的权值队列
     *
     * @param arr
     * @return
     */
    public PriorityQueue<HuffmanNode> adjust(byte[] arr) {
        // 存放字符权值
        HashMap<Byte, Integer> byteIntegerHashMap = new HashMap<>();
        for (byte c : arr) {
            // 获取当前字符出现的次数
            Integer integer = byteIntegerHashMap.get(c);
            // 出现次数统计
            if (integer == null) {
                byteIntegerHashMap.put(c, 1);
            } else {
                byteIntegerHashMap.put(c, integer + 1);
            }
        }

        // 创建node队列
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> byteIntegerEntry : byteIntegerHashMap.entrySet()) {
            nodes.add(new HuffmanNode(byteIntegerEntry.getKey(), byteIntegerEntry.getValue()));
        }
        return nodes;

    }

    /**
     * 前序遍历
     */
    public void preTraversal() {
        if (root == null) {
            System.out.println("空数！无法遍历");
            return;
        }
        root.preTraversal();
    }

    /**
     * 将数组变为哈夫曼树
     *
     * @param arr
     * @return
     */
    public HuffmanNode creatHuffmanTree(byte[] arr) {
        // 创建节点队列
        // 遍历数组创建节点
        // 将集合中的节点进行升序排序
        PriorityQueue<HuffmanNode> nodes = adjust(arr);
        // 当集合中只剩根节点时退出循环
        while (nodes.size() > 1) {
            // 取出最小的两个节点组成子树
            HuffmanNode leftNode = nodes.poll();
            HuffmanNode rightNode = nodes.poll();
            HuffmanNode node = new HuffmanNode(null, leftNode.value + rightNode.value);
            node.left = leftNode;
            node.right = rightNode;
            // 将子树的根节点重新放回集合中
            nodes.add(node);
        }
        // 退出循环，此时队列中仅剩的一个元素为根节点
        root = nodes.poll();
        return root;
    }

}

class HuffmanNode implements Comparable<HuffmanNode> {
    /**
     * 存放字符
     */
    public Byte character;
    /**
     * 字符权值（出现次数）
     */
    public int value;
    public HuffmanNode left;
    public HuffmanNode right;

    /**
     * 前序遍历
     */
    public void preTraversal() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preTraversal();
        }
        if (this.right != null) {
            this.right.preTraversal();
        }
    }


    public HuffmanNode(Byte character, int value) {
        this.character = character;
        this.value = value;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "character=" + character +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(HuffmanNode o) {
        // 升序排序
        return this.value - o.value;
    }
}