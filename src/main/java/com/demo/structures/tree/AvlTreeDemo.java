package com.demo.structures.tree;

/**
 * @author zmj
 * @date 2020/6/21 15:07
 * @Description
 */
public class AvlTreeDemo {
    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        int[] arr = {15, 10, 25, 4, 60, 1, 8, 19, 23, 18, 22, 24, 16, 17};
//        int[] arr = {10, 11, 7, 6, 8, 9};
        avlTree.add(arr);
        avlTree.inTraversal();
        System.out.println("***************************");
        System.out.println(avlTree.contain(10));
        System.out.println("根节点为: " + avlTree.getRoot());
        System.out.println("树总高: " + avlTree.treeHeight()); // 6
        System.out.println("左子树总高: " + avlTree.leftHeight()); // 3
        System.out.println("右子树总高: " + avlTree.rightHeight());  // 5
        System.out.println("***************************");
        avlTree.delete(15);
        System.out.println("删除后");
        avlTree.inTraversal();
        System.out.println("root:" + avlTree.getRoot());
    }
}

class AvlTree {
    private Node root;

    /**
     * 旋转判断
     */
    private void RotateDecide() {
        // 左旋转判断
        if (rightHeight() - leftHeight() > 1) {
            // 如果右子树的左子树高度大于右子树高度，则先对右子树进行右旋转
            if (rightHeight(root.right) < leftHeight(root.right)) {
                RotateRight(root.right);
            }
            RotateLeft(root);
            // 右旋转判断
        } else if (leftHeight() - rightHeight() > 1) {
            if (rightHeight(root.left) > leftHeight(root.left)) {
                RotateLeft(root.left);
            }
            RotateRight(root);
        }
    }

    /**
     * 左旋转
     */
    private void RotateLeft(Node node) {
        // 创建新的节点，为原来的根节点的值
        Node temp = new Node(node.value);
        // 给新的节点链接左右节点
        temp.left = node.left;
        temp.right = node.right.left;
        // 根节点的换成源右子节点
        node.value = node.right.value;
        // 重新指向原来的右节点
        node.right = node.right.right;
        // 指向新的节点
        node.left = temp;
    }

    /**
     * 右旋转
     */
    private void RotateRight(Node node) {
        // 创建新的节点，为原来的根节点的值
        Node temp = new Node(node.value);
        // 给新的节点链接左右节点
        temp.left = node.left.right;
        temp.right = node.right;
        // 根节点的换成源左子节点
        node.value = node.left.value;
        // 重新指向原来的右节点
        node.left = node.left.left;
        // 指向新的节点
        node.right = temp;
    }

    /**
     * 统计整树左子树高度
     *
     * @return
     */
    public int leftHeight() {
        return leftHeight(root);
    }

    /**
     * 统计左子树高度
     *
     * @return
     */
    private int leftHeight(Node node) {
        if (node == null || node.left == null) {
            return 0;
        }
        return treeHeight(node.left);
    }

    /**
     * 统计整树右子树高度
     *
     * @return
     */
    public int rightHeight() {
        return rightHeight(root);
    }

    /**
     * 统计右子树高度
     *
     * @return
     */
    private int rightHeight(Node node) {
        if (node == null || node.right == null) {
            return 0;
        }
        return treeHeight(node.right);
    }

    /**
     * 返回整棵树的高度
     *
     * @return
     */
    public int treeHeight() {
        if (root == null) {
            return 0;
        }
        return treeHeight(root);
    }

    /**
     * 输入节点到树末端的高度
     *
     * @param node
     * @return
     */
    private int treeHeight(Node node) {
        return Math.max(node.left == null ? 0 : treeHeight(node.left), node.right == null ? 0 : treeHeight(node.right)) + 1;
    }

    /**
     * 刪除节点
     *
     * @param value
     * @return
     */
    public Integer delete(int value) {
        // 0:根节点 1:左 2:右
        int flag = 0;
        // 获取待删节点的父节点
        Node node = searchParent(value);
        // 未找到待删节点
        if (node == null) {
            return null;
        }
        Node temp = node;
        // 判断待删节点是父节点的左/右节点
        if (temp.left != null && temp.left.value == value) {
            flag = 1;
            temp = temp.left;
        } else if (temp.right != null && temp.right.value == value) {
            flag = 2;
            temp = temp.right;
        }
        // 当要删除的是叶子节点
        if (temp.left == null && temp.right == null) {
            int deleteResult = temp.value;
            adjustNode(flag, node, null);
            return value;
        }
        // 当要删除的只有左子节点
        if (temp.left != null && temp.right == null) {
            adjustNode(flag, node, temp.left);
            return value;
        }
        // 当要删除的只有右子节点
        if (temp.left == null) {
            adjustNode(flag, node, temp.right);
            return value;
        }
        // 当要删除的有两个子节点
        Node smallTemp = temp.right;
        if (smallTemp.left == null) {
            adjustNode(flag, node, smallTemp);
            smallTemp.left = temp.left;
            return value;
        }
        while (true) {
            if (smallTemp.left == null) {
                break;
            }
            smallTemp = smallTemp.left;
        }
        searchParent(smallTemp.value).left = null;
        temp.value = smallTemp.value;
        return value;
    }

    /**
     * delete 根据父节点类型删除节点
     *
     * @param flag   0:根节点 1:左 2:右
     * @param parent
     * @param son
     */
    private void adjustNode(int flag, Node parent, Node son) {
        switch (flag) {
            case 1:
                parent.left = son;
                break;
            case 2:
                parent.right = son;
                break;
            case 0:
                root = son;
                break;
            default:
                break;
        }
    }

    /**
     * 判断是否包含指定的值
     *
     * @param value
     * @return true:包含
     * false:不包含
     */
    public boolean contain(int value) {
        Node node = searchParent(value);
        if (node == null) {
            return false;
        }
        return true;
    }

    /**
     * 获取查找值的父节点
     *
     * @param value 要查找的值
     * @return root：值为根节点
     * null: 未找到
     * node: 查找值的父节点
     */
    private Node searchParent(int value) {
        if (root == null) {
            throw new RuntimeException("空树无法查找!");
        }
        if (root.value == value) {
            return root;
        }
        Node temp = root;
        while (true) {
            if (value < temp.value) {
                if (temp.left == null) {
                    return null;
                }
                if (temp.left.value == value) {
                    return temp;
                }
                temp = temp.left;
                continue;
            }
            if (value > temp.value) {
                if (temp.right == null) {
                    return null;
                }
                if (temp.right.value == value) {
                    return temp;
                }
                temp = temp.right;
                continue;
            }
        }
    }

    /**
     * 批量添加元素
     *
     * @param values
     */
    public void add(int[] values) {
        if (values == null && values.length == 0) {
            throw new RuntimeException("输入数组为空,无法添加!");
        }
        for (int value : values) {
            add(value);
        }
    }

    /**
     * 添加单个元素
     *
     * @param value
     * @return
     */
    public int add(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
            return value;
        }
        Node temp = root;
        while (true) {
            // 添加的值已经存在
            if (temp.value == value) {
                return -1;
            }
            // 当要添加的值小于当前的的指针的值
            if (value < temp.value) {
                if (temp.left == null) {
                    temp.left = node;
                    RotateDecide();
                    return value;
                }
                temp = temp.left;
                continue;
            }
            if (temp.right == null) {
                temp.right = node;
                RotateDecide();
                return value;
            }
            temp = temp.right;
        }


    }

    /**
     * 中序遍历
     */
    public void inTraversal() {
        if (root == null) {
            System.out.println("空树无法遍历!");
            return;
        }
        inTraversal(root);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private void inTraversal(Node node) {
        if (node.left != null) {
            inTraversal(node.left);
        }
        System.out.println(node);
        if (node.right != null) {
            inTraversal(node.right);
        }
    }

    public AvlTree() {
    }

    public AvlTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    static class Node {

        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}