package com.demo.structures.tree;

/**
 * @author zmj
 * @date 2020/6/19 8:31
 * @Description
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        TreeHeroNode root = new TreeHeroNode(1, "宋江");
        TreeHeroNode node2 = new TreeHeroNode(2, "吴用");
        TreeHeroNode node3 = new TreeHeroNode(3, "卢俊义");
        TreeHeroNode node4 = new TreeHeroNode(4, "林冲");
        TreeHeroNode node5 = new TreeHeroNode(5, "关胜");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        binaryTree.preTraversal();
        System.out.println("***************************************************");
        binaryTree.inTraversal();
//        System.out.println("***************************************************");
//        binaryTree.postTraversal();
//        System.out.println("***************************************************");
//        TreeHeroNode treeHeroNode = binaryTree.preFind(4 );
//        if (treeHeroNode != null) {
//            System.out.println("第" + treeHeroNode.getId() + "号英雄为:" + treeHeroNode.getName() + ";");
//        } else {
//            System.out.println("未找到指定英雄");
//        }
        System.out.println("***************************************************");
        binaryTree.deleteById(1);
        binaryTree.preTraversal();
    }
}

class BinaryTree {
    private TreeHeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(TreeHeroNode root) {
        this.root = root;
    }

    public TreeHeroNode getRoot() {
        return root;
    }

    public void setRoot(TreeHeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preTraversal() {
        if (root != null) {
            root.preTraversal();
        } else {
            System.out.println("根节点不存在！");
        }

    }

    /**
     * 中序遍历
     */
    public void inTraversal() {
        if (root != null) {
            root.inTraversal();
        } else {
            System.out.println("根节点不存在！");
        }

    }

    /**
     * 后序遍历
     */
    public void postTraversal() {
        if (root != null) {
            root.postTraversal();
        } else {
            System.out.println("根节点不存在！");
        }

    }

    /**
     * 前序遍历查找id
     * 根 → 左 → 右
     */
    public TreeHeroNode preFind(int id) {
        if (id > 0) {
            return root.preFind(id);
        } else {
            return null;
        }
    }
    /**
     * 通过id删除结点
     * @param id
     */
    public void deleteById(int id){
        if (id < 0) {
            System.out.println("id输入有误!");
            return;
        }
        if (root.getId() == id) {
            root = null;
            return;
        }
        root.deleteById(id);
    }
}

class TreeHeroNode {
    private int id;
    private String name;
    private TreeHeroNode left;
    private TreeHeroNode right;

    public TreeHeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeHeroNode getLeft() {
        return left;
    }

    public void setLeft(TreeHeroNode left) {
        this.left = left;
    }

    public TreeHeroNode getRight() {
        return right;
    }

    public void setRight(TreeHeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Heroes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     * 根 → 左 → 右
     */
    public void preTraversal() {
        // 输出根结点
        System.out.println("第" + id + "号英雄为:" + name + ";");
        // 输出左子树
        if (this.left != null) {
            this.left.preTraversal();
        }
        // 输出右子树
        if (this.right != null) {
            this.right.preTraversal();
        }
    }

    /**
     * 中序遍历
     * 左 → 根 → 右
     */
    public void inTraversal() {
        // 输出左子树
        if (this.left != null) {
            this.left.inTraversal();
        }
        // 输出根结点
        System.out.println("第" + id + "号英雄为:" + name + ";");
        // 输出右子树
        if (this.right != null) {
            this.right.inTraversal();
        }
    }

    /**
     * 后序遍历
     * 左 → 右 → 根
     */
    public void postTraversal() {
        // 输出左子树
        if (this.left != null) {
            this.left.postTraversal();
        }
        // 输出右子树
        if (this.right != null) {
            this.right.postTraversal();
        }
        // 输出根结点
        System.out.println("第" + id + "号英雄为:" + name + ";");
    }

    /**
     * 前序遍历查找id
     * 根 → 左 → 右
     */
    public TreeHeroNode preFind(int id) {
        TreeHeroNode result = null;
        if (this.id == id) {
            // 输出根结点
            return this;
        }
        // 输出左子树
        if (this.left != null) {
            result = this.left.preFind(id);
        }
        // 如果结果不为null，则左节点找到,否则接着寻找右节点
        if (result != null) {
            return result;
        }
        // 输出右子树
        if (this.right != null) {
            result = this.right.preFind(id);
        }
        return result;
    }
    /**
     * 后序遍历查找id
     * 左 → 右 → 根
     * 略
     */
    /**
     * 中序遍历查找id
     * 左 → 根 → 右
     * 略
     */
    /**
     * 通过id删除结点
     * @param id
     */
    public void deleteById(int id){
        // 判断当前节点的左子结点是否是要删除节点
        if (this.left != null && this.left.id == id) {
            // 删除子节点的连接
            this.left = null;
            return;
        }
        // 判断当前节点的右子结点是否是要删除节点
        if (this.right != null && this.right.id == id) {
            // 删除子节点的连接
            this.right = null;
            return;
        }
        // 进行左递归遍历
        if (this.left != null) {
            this.left.deleteById(id);
        }
        // 进行右递归遍历
        if (this.right != null) {
            this.right.deleteById(id);
        }

    }
}