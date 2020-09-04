package com.xh.study.algorithm;

import com.xh.study.algorithm.bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {

    /**
     * -----g
     * ---/   \
     * --d     m
     * -/  \   / \
     * a   f  h  z
     * ---/
     * --e
     */
    public TreeNode create() {
        TreeNode root = new TreeNode();
        root.value = "g";
        TreeNode root1 = new TreeNode();
        root1.value = "d";
        TreeNode root2 = new TreeNode();
        root2.value = "a";
        TreeNode root3 = new TreeNode();
        root3.value = "f";
        TreeNode root4 = new TreeNode();
        root4.value = "e";
        TreeNode root5 = new TreeNode();
        root5.value = "m";
        TreeNode root6 = new TreeNode();
        root6.value = "h";
        TreeNode root7 = new TreeNode();
        root7.value = "z";
        root.left = root1;
        root.right = root5;
        root1.left = root2;
        root1.right = root3;
        root3.left = root4;
        root5.left = root6;
        root5.right = root7;
        return root;
    }


    //    一、前序遍历
//
    //　　访问顺序：先根节点，再左子树，最后右子树；上图的访问结果为：GDAFEMHZ。
    public void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + "->");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }

    public void preOrderTraverse2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curNode;
        //先打印左边的 在打印右边的
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
            System.out.print(curNode.value + "->");
        }
    }


//    二、中序遍历
//
//　　访问顺序：先左子树，再根节点，最后右子树；上图的访问结果为：ADEFGHMZ。

    public void inOrderTraverse1(TreeNode root) {
        if (root != null) {
            inOrderTraverse1(root.left);
            System.out.print(root.value + "->");
            inOrderTraverse1(root.right);
        }
    }

    public void inOrderTraverse2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
        TreeNode curNode = root;
        //先打印左边的 在打印右边的
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = stack.pop();
                System.out.print(curNode.value + "->");
                curNode = curNode.right;
            }
        }
    }

    //    三、后序遍历
//
//　　访问顺序：先左子树，再右子树，最后根节点，上图的访问结果为：AEFDHZMG。
    public void postOrderTraverse1(TreeNode root) {
        if (root != null) {
            postOrderTraverse1(root.left);
            postOrderTraverse1(root.right);
            System.out.print(root.value + "->");
        }
    }

    public void postOrderTraverse2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curNode, preNode = null;
        //先打印左边的 在打印右边的
        while (!stack.isEmpty()) {
            curNode = stack.peek();
            if ((curNode.left == null && curNode.right == null) || (preNode != null && (preNode == curNode.left || preNode == curNode.right))) {
                System.out.print(curNode.value + "->");
                stack.pop();
                preNode = curNode;
            } else {
                if (curNode.right != null) {
                    stack.push(curNode.right);
                }
                if (curNode.left != null) {
                    stack.push(curNode.left);
                }
            }
        }

    }

//    四、层次遍历

    //　　访问结果：GDMAFHZE。
    public void levelOrderTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curNode;
        queue.add(root);
        while (!queue.isEmpty()) {
            curNode = queue.remove();
            if (curNode.left != null) {
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
            }
            System.out.print(curNode.value + "->");
        }

    }
}
