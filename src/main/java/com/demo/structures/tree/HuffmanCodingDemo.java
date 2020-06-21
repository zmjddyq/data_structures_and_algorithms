package com.demo.structures.tree;


import java.io.*;
import java.util.*;

/**
 * @author zmj
 * @date 2020/6/20 16:20
 * @Description 霍夫曼编码 → 压缩 、解压
 */
public class HuffmanCodingDemo {
    public static void main(String[] args) {
////        String message = "i like like like java do you like a java";
////        String message = "Talk is cheap,Show me the code";
//        String message = "You build it, You run it.";
//        byte[] bytes = message.getBytes();
//        HuffmanCodingTree huffmanCodingTree = new HuffmanCodingTree();
///*        PriorityQueue<HuffmanNode> adjust = huffmanCodingTree.adjust(bytes);
//        System.out.println(adjust.toString());*//*
//        System.out.println("树化后的根节点为： " + huffmanCodingTree.creatHuffmanTree(bytes));
//        huffmanCodingTree.preTraversal();
//        System.out.println("*********************************************");
//        HashMap huffmanCoding = huffmanCodingTree.getHuffmanCoding();
//        System.out.println(huffmanCoding);
//        System.out.println("*********************************************");
//        byte[] zip = huffmanCodingTree.zip(bytes, huffmanCoding);
//        System.out.println(Arrays.toString(zip));*/
//        // 压缩
//        byte[] zip = huffmanCodingTree.huffmanZip(bytes);
//        // 获取编码表
//        // HashMap<Byte, String> huffmanCoding = huffmanCodingTree.getHuffmanCodingMap();
//        System.out.println("*********************************************");
//        byte[] decode = huffmanCodingTree.decode(zip);
//        System.out.println(new String(decode));

        // 文件压缩测试
        HuffmanCodingTree huffmanCodingTree = new HuffmanCodingTree();
        huffmanCodingTree.zipFile("D:\\123.txt", "D:\\123.zip");
        System.out.println("压缩完成");
        huffmanCodingTree.unZipFile("D:\\123.zip", "D:\\321.txt");
        System.out.println("解压完成");
    }
}

class HuffmanCodingTree {

    private HuffmanNode root;
    private HashMap<Byte, String> huffmanCodingMap = new HashMap<>();

    public HuffmanNode getRoot() {
        return root;
    }

    public HashMap<Byte, String> getHuffmanCodingMap() {
        return huffmanCodingMap;
    }

    //编写一个方法，完成对压缩文件的解压

    /**
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public void unZipFile(String zipFile, String dstFile) {
        try (InputStream is = new FileInputStream(zipFile);
             ObjectInputStream ois = new ObjectInputStream(is);
             OutputStream os = new FileOutputStream(dstFile)) {
            //创建文件输入流
            //创建一个和  is关联的对象输入流
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            HashMap<Byte, String> huffmanCodes = (HashMap<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanBytes, huffmanCodes);
            //将bytes 数组写入到目标文件
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //编写方法，将一个文件进行压缩

    /**
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public void zipFile(String srcFile, String dstFile) {
        try (OutputStream os = new FileOutputStream(dstFile);
             ObjectOutputStream oos = new ObjectOutputStream(os);
             FileInputStream is = new FileInputStream(srcFile)) {
            //创建文件的输入流
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            //创建一个和文件输出流关联的ObjectOutputStream
            //把 赫夫曼编码后的字节数组写入压缩文件
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodingMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 译码
     *
     * @param bytes
     * @return
     */
    public byte[] decode(byte[] bytes) {
        return decode(bytes, this.huffmanCodingMap);
    }

    /**
     * 译码
     *
     * @param bytes
     * @param huffmanCodingMap
     * @return
     */
    public byte[] decode(byte[] bytes, HashMap<Byte, String> huffmanCodingMap) {
        boolean flag = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length - 1; i++) {
            // 判断是否是数组最后一个元素
            if (i == bytes.length - 2) {
                flag = false;
            }
            stringBuilder.append(byteToString(flag, bytes[i], bytes[bytes.length - 1]));
        }
        HashMap<String, Byte> huffmanDecodeHashMap = adjustMap(huffmanCodingMap);
        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for(int  i = 0; i < stringBuilder.length(); ) {
            // 小的计数器
            int count = 1;
            boolean breakFlag = true;
            Byte b = null;

            while(breakFlag) {
                //1010100010111...
                //递增的取出 key 1
                //i 不动，让count移动，指定匹配到一个字符
                String key = stringBuilder.substring(i, i+count);
                b = huffmanDecodeHashMap.get(key);
                if(b == null) {
                    //说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    breakFlag = false;
                }
            }
            list.add(b);
            //i 直接移动到 count
            i += count;
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将编码表转换为译码表
     *
     * @return
     */
    private HashMap<String, Byte> adjustMap(HashMap<Byte, String> huffmanCodingMap) {
        HashMap<String, Byte> stringByteHashMap = new HashMap<>();
        for (Map.Entry<Byte, String> byteStringEntry : huffmanCodingMap.entrySet()) {
            stringByteHashMap.put(byteStringEntry.getValue(), byteStringEntry.getKey());
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
     * 封装压缩过程
     *
     * @param bytes
     * @return
     */
    public byte[] huffmanZip(byte[] bytes) {
        // 创造霍夫曼树
        HuffmanNode huffmanNode = creatHuffmanTree(bytes);
        // 建立编码表
        HashMap huffmanCoding = getHuffmanCoding();
        // 数据压缩
        byte[] zip = zip(bytes, huffmanCoding);
        return zip;

    }

    /**
     * 数据压缩
     *
     * @param bytes            要压缩的字节数组
     * @param huffmanCodingMap 编码表
     * @return
     */
    private byte[] zip(byte[] bytes, HashMap<Byte, String> huffmanCodingMap) {
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
    private HashMap getHuffmanCoding() {
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
    private PriorityQueue<HuffmanNode> adjust(byte[] arr) {
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
     * @return 树的根节点
     */
    private HuffmanNode creatHuffmanTree(byte[] arr) {
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