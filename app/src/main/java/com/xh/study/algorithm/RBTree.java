package com.xh.study.algorithm;

public class RBTree {


    private static final boolean RED = false;//红色是false
    private static final boolean BLACK = true;//黑色是true


    public static class Node {
        public int value;
        public boolean color;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value, boolean color, Node left, Node right, Node parent) {
            this.value = value;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }


    public Node mRoot;// 根结点

    //没做相同数的判断
    public void inset(int num) {
        Node node = new Node(num, false, null, null, null);

        Node cur = mRoot;//当前结点
        Node p = null;//拿到父结点
        while (cur != null) {
            p = cur;
            //循环去取一个为空的结点
            if (node.value < cur.value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        //取到一个合适的空结点cur   和父节点p  然后就把node结点放入
        if (p != null) {
            if (node.value < p.value) {
                p.left = node;
            } else {
                p.right = node;
            }
            node.parent = p;
            node.color = RED;
        } else {
            mRoot = node;
            node.color = BLACK;
        }
        //开始选择树
        insetFix(node);

    }


    private void insetFix(Node cur) {
        //取到父结点
        Node p = null;
        //如果父结点为空，说明这个结点是root结点
        //如果父结点不为空，如果父结点是黑，那么直接插入，不动就可以了
        //并且父结点是红。那么肯定有祖父结点
        while ((p = cur.parent) != null && p.color == RED) {
            Node pp = p.parent;
            //判断父结点在祖父结点的左边
            if (pp.left == p) {
                //判断叔叔结点为空或者叔叔结点为黑
                if (pp.right == null || pp.right.color == BLACK) {
                    //判断当前插入的结点在父结点的左边，那么就做黑红红 ，都在左边，就要变为红黑红，并且右旋
                    if (p.left == cur) {
                        pp.color = RED;
                        p.color = BLACK;
                        rightHanded(pp);
                    } else {
                        //如果当前插入结点在父结点的右边，那么需要对父结点左旋，然后把父结点作为插入结点，继续循环
                        leftHanded(p);
                        cur = p;
                    }
                } else {
                    //如果叔叔结点是红的，那么变父结点和叔叔结点为黑，祖父结点为红，然后把祖父结点作为插入结点，继续循环
                    pp.color = RED;
                    p.color = BLACK;
                    pp.right.color = BLACK;
                    cur = pp;
                }
            } else {
                //父结点在祖父结点的右边， 叔叔结点不存在或者为黑
                if (pp.left == null || pp.left.color == BLACK) {
                    //插入结点在父结点的右边，那么变黑红红为红黑红，然后左旋
                    if (p.right == cur) {
                        pp.color = RED;
                        p.color = BLACK;
                        leftHanded(pp);
                    } else {
                        //插入结点在父结点的左边，那么先右旋，当父节点作为插入结点
                        rightHanded(p);
                        cur = p;
                    }
                } else {
                    //如果叔叔结点是红的，那么变父结点和叔叔结点为黑，祖父结点为红，然后把祖父结点作为插入结点，继续循环
                    pp.color = RED;
                    p.color = BLACK;
                    pp.left.color = BLACK;
                    cur = pp;
                }
            }
        }
        mRoot.color = BLACK;
    }

    //左旋    把他的右子结点变为父结点，吧他的右结点的左子结点变为他的右结点
//    这个地方动了3个键，需要修改6条引用关系
    private void leftHanded(Node cur) {
        Node right = cur.right;
        Node rightLeft = right.left;


        //首先判断他的父节点，父结点为空，那么当前结点的右结点就变为了root结点
        if (cur.parent == null) {
            mRoot = right;
        } else {
            //如果不为空，那么判断当前结点是父结点的左边还是右边，是哪一边就设置哪一边的值为 右结点
            if (cur.parent.right == cur) {
                cur.parent.right = right;
            } else {
                cur.parent.left = right;
            }
        }
        right.parent = cur.parent;//设置右结点的父结点为当前结点的父结点


        //设置当前的结点的父结点为右结点
        cur.parent = right;
        //右结点的左边是当前结点
        right.left = cur;

//        当前结点的右结点为右结点的左子结点
//        右结点的左子结点的父结点为当前结点
        cur.right = rightLeft;
        if (rightLeft != null) {
            rightLeft.parent = cur;
        }

    }

//右旋   跟左旋差不多，就是方向是反的
    private void rightHanded(Node cur) {
        Node left = cur.left;
        Node leftRigth = left.right;

        if (cur.parent == null) {
            mRoot = left;
        } else {
            if (cur.parent.left == cur) {
                cur.parent.left = left;
            } else {
                cur.parent.right = left;
            }
        }
        left.parent = cur.parent;

        cur.parent = left;
        left.right = cur;

        cur.left = leftRigth;
        if (leftRigth != null) {
            leftRigth.parent = cur;
        }

    }


    public void print(Node node) {
        if (node != null) {
            System.out.println("---" + node.value + (node.color == BLACK ? "黑" : "红"));
            print(node.left);
            print(node.right);
        }

    }


}
