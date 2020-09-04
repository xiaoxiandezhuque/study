package com.xh.study.algorithm;


import com.xh.study.algorithm.bean.BinaryTreeNode;
import com.xh.study.algorithm.bean.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class JavaTest {


//    一、题目
//    把一个数组最开始的若干个元素搬到数组的末尾， 我们称之数组的旋转。输入一个递增排序的数组的一个旋转，
//    输出旋转数组的最小元素。例如数组{3,4,5,1,2 ｝为｛ 1,2,3,4,5}的一个旋转，该数组的最小值为1。
//
//    二、解题思路
//    Step1.和二分查找法一样，我们用两个指针分别指向数组的第一个元素和最后一个元素。
//    Step2.接着我们可以找到数组中间的元素：
//    如果该中间元素位于前面的递增子数组，那么它应该大于或者等于第一个指针指向的元素。此时数组中最小
//    的元素应该位于该中间元素的后面。我们可以把第一个指针指向该中间元素，这样可以缩小寻找的范围。
//    如果中间元素位于后面的递增子数组，那么它应该小于或者等于第二个指针指向的元素。此时该数组中最小的元素应该位于该中间元素的前面。
//    Step3.接下来我们再用更新之后的两个指针，重复做新一轮的查找。

    //找旋转数组的最小元素
    public static int test1(int[] src) {
        if (src == null || src.length == 0) {
            throw new NullPointerException();
        }
        if (src.length == 1) {
            return src[0];
        }
        int start, end, middle;
        start = 0;
        end = src.length - 1;
        middle = (end - start) / 2;
        while (true) {
            if (middle == start) {
                return src[start + 1];
            }
            if (src[start] > src[middle]) {
                end = middle;
            } else {
                start = middle;
            }
            middle = (end - start) / 2 + start;
        }

    }

    //    写一个函数，输入n，求斐波那契数列的第n项值。
    public static int test2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int pre = 1, prepre = 1;
        int result = 2;
        for (int i = 3; i <= n; i++) {
            result = pre + prepre;
            prepre = pre;
            pre = result;
        }
        return result;

    }

//    一、题目
//    请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。例如把9表示成二进制1001，
//    有2位1。因此如果输入9，该函数输出2。
//
//    二、解题思路
//      ①位移＋计数 每次右移一位，不断和1进行与运算，直到位0。
//      ②循环让(n - 1) & n。如果n的二进制表示中有k个1，那么这个方法只需要循环k次即可。
//      其原理是不断清除n的二进制表示中最右边的1，同时累加计数器，直至n为0。因为从二进制的角度讲，
//      n相当于在n - 1的最低位加上1。举个例子，8（1000）= 7（0111）+ 1（0001），所以8 & 7 = （1000）&（0111）= 0（0000），
//      清除了8最右边的1（其实就是最高位的1，因为8的二进制中只有一个1）。
//      再比如7（0111）= 6（0110）+ 1（0001），所以7 & 6 = （0111）&（0110）= 6（0110），
//      清除了7的二进制表示中最右边的1（也就是最低位的1）。

    public static int test3(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += (n & 1);
            n >>>= 1;
        }
        return result;
    }

    public static int test4(int n) {
        int result = 0;
        while (n > 0) {
            n = n & (n - 1);
            result++;
        }
        return result;
    }

//    一、题目
//    给定单向链表的一个头指针和节点指针，定义一个函数在O(1)时间删除该节点。
//
//    二、解题思路
//    由于给定了节点指针，那么要删除该节点。只要把该节点的值替换为下一个节点的值，
//    同时让该节点直接指向下一个节点的下一个节点。相当于顶包代替了下一个节点，该节点自然就不存在。
//    需要注意的是如果指定节点是头结点，那么直接把头结点定义为下一个节点即可。如果是尾节点，需要循环遍历到该节点，
//    然后让尾节点的上一个节点的指针为空即可。

    //    public static int test5(int n) {
//
//
//
//    }
    public static int test6(int n) {
        return n & 1;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有奇数位于数组的前半部分，所有偶数位予数组的后半部分。
     *
     * @param arr 输入的数组
     */
    public static int[] test7(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            while ((arr[start] & 1) == 1 && start < arr.length) {
                start++;
            }
            while ((arr[end] & 1) == 0 && end > 0) {
                end--;
            }
            if (start < end) {
                int i = arr[start];
                arr[start] = arr[end];
                arr[end] = i;
            }
        }
        return arr;
    }

    //    一、题目
//      定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
//
//    二、解题思路
//      ①遍历。将指向下一个节点的指针指向上一个节点。
//
//      ②递归。先让指向下一个节点的指针为空，然后递归调用，最后再将指向下一个节点的指针指向上一个节点。
    public static Node test8(Node head) {
        if (head == null) {
            return head;
        }
        Node pre = head;
        Node cur = head.next;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        head.next = null;
        head = pre;
        return head;
    }

    public static Node test9(Node current) {
        if (current == null || current.next == null) return current;
        Node nextNode = current.next;
        current.next = null;
        Node reverseRest = test9(nextNode);
        nextNode.next = current;
        return reverseRest;
    }
//    一、题目
//    输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的。
//
//    二、解题思路
//    Step1.定义一个指向新链表的指针，暂且让它指向NULL；
//
//    Step2.比较两个链表的头结点，让较小的头结点作为新链表的头结点；
//
//    Step3.有两种方法。
//
//     ①递归比较两个链表的其余节点，让较小的节点作为上一个新节点的后一个节点；
//
//      ②循环比较两个链表的其余节点，让较小的节点作为上一个新节点的后一个节点。直到有一个链表没有节点，
//      然后将新链表的最后一个节点直接指向剩余链表的节点。

    public static Node test10(Node head1, Node head2) {
// 如果第一个链表为空，返回第二个链表头结点
        if (head1 == null) {
            return head2;
        }

        // 如果第二个结点为空，返回第一个链表头结点
        if (head2 == null) {
            return head1;
        }
        Node root = new Node();
        Node cur = root;
        Node cur1 = head1;
        Node cur2 = head2;

        while (cur1 != null && cur2 != null) {
            if (cur1.value > cur2.value) {
                cur.next = cur2;
                cur2 = cur2.next;
            } else {
                cur.next = cur1;
                cur1 = cur1.next;
            }

            cur = cur.next;
        }

        if (cur1 == null) {
            cur.next = cur2;
        }
        if (cur2 == null) {
            cur.next = cur1;
        }

        return root.next;
    }

//    一、题目
//    输入两棵二叉树A 和B，判断B 是不是A 的子结构。
//
//    二、解题思路
//    要查找树A 中是否存在和树B 结构一样的子树，我们可以分成两步：
//    第一步在树A 中找到和B 的根结点的值一样的结点R，
//    第二步再判断树A 中以R 为根结点的子树是不是包含和树B 一样的结构。

    public static boolean test11(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        boolean result = false;

        if (root1.value == root2.value) {
            result = match(root1, root2);
        }
        if (result) {
            return true;
        }

        return test11(root1.left, root2) || test11(root1.right, root2);
    }

    public static boolean match(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.value == root2.value) {
            return match(root1.left, root2.left) && match(root1.right, root2.right);
        }

        return false;
    }

//    一、题目
//    请完成一个函数，输入一个二叉树，该函数输出它的镜像。
//
//    二、解题思路
//    先前序遍历这棵树的每个结点，如果遍历到的结点有子结点，就交换它的两个子结点。
//    当交换完所有非叶子结点的左右子结点之后，就得到了树的镜像。

    public static void test12(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        BinaryTreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        test12(root.left);
        test12(root.right);
    }

    //    一、题目
//    输入一个矩阵，按照从外向里以顺时针的顺序依次扫印出每一个数字。
//
//    二、解题思路
//    把打印一圈分为四步：
//    第一步从左到右打印一行，
//    第二步从上到下打印一列，
//    第三步从右到左打印一行，
//    第四步从下到上打印一列。
//    每一步我们根据起始坐标和终止坐标用一个循环就能打印出一行或者一列。
//
//    不过值得注意的是，最后一圈有可能退化成只有一行、只有一列，甚至只有一个数字，因此打印这样的一圈就不再需要四步。
//
//    因此我们要仔细分析打印时每一步的前提条件。
//    第一步总是需要的， 因为打印一圈至少有一步。如果只有一行，那么就不用第二步了。
//    也就是需要第二步的前提条件是终止行号大于起始行号。
//    需要第三步打印的前提条件是圈内至少有两行两列，
//    也就是说除了要求终止行号大于起始行号之外，还要求终止列号大于起始列号。
//    同理，需要打印第四步的前提条件是至少有三行两列，因此要求终止行号比起始行号至少大2 ， 同时终止列号大于起始列号。
    public static void test13(int[][] numbers) {
        if (numbers == null) {
            return;
        }

        int x = 0;
        int y = 0;
        while (x * 2 < numbers.length && y * 2 < numbers[0].length) {
            printMatrixInCircle(numbers, x, y);
            x++;
            y++;
        }

    }

    public static void printMatrixInCircle(int[][] numbers, int x, int y) {

        int rows = numbers.length;
        int cols = numbers[0].length;

        for (int i = y; i <= cols - y - 1; i++) {
            System.out.println(numbers[x][i] + "");
        }
        if (rows - x - 1 > x) {
            for (int i = x + 1; i <= rows - x - 1; i++) {
                System.out.println(numbers[i][cols - y - 1] + "");
            }
        }

        if (rows - x - 1 > x && cols - 1 - y > y) {
            for (int i = cols - y - 2; i >= y; i--) {
                System.out.println(numbers[rows - 1 - x][i] + " ");
            }
        }
        if (cols - 1 - y > y && rows - 1 - x > x + 1) {
            for (int i = rows - x - 2; i > x; i--) {
                System.out.println(numbers[i][y] + " ");
            }
        }

    }

//    一、题目
//    输入两个整数序列，第一个序列表示栈的压入顺序，请判断二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
//
//    二、解题思路
//    解决这个问题很直观的想法就是建立一个辅助栈，把输入的第一个序列中的数字依次压入该辅助栈，并按照第二个序列的顺序依次从该栈中弹出数字。
//
//    判断一个序列是不是栈的弹出序列的规律：如果下一个弹出的数字刚好是栈顶数字，那么直接弹出。如果下一个弹出的数字不在栈顶，
//    我们把压栈序列中还没有入栈的数字压入辅助栈，直到把下一个需要弹出的数字压入栈顶为止。
//    如果所有的数字都压入栈了仍然没有找到下一个弹出的数字，那么该序列不可能是一个弹出序列。

    //方法：data1数组的顺序表示入栈的顺序。现在判断data2的这种出栈顺序是否正确
    public static boolean test14(int[] data1, int[] data2) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0, j = 0; i < data1.length; i++) {

            stack.push(data1[i]);
            while (stack.size() > 0 && stack.peek() == data2[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.size() == 0;
    }

//    一、题目
//    从上往下打印出二叉树的每个结点，同一层的结点按照从左向右的顺序打印。
//
//    二、解题思路
//    这道题实质是考查树的遍历算法。从上到下打印二叉树的规律：每一次打印一个结点的时候，
//    如果该结点有子结点， 则把该结点的子结点放到一个队列的末尾。接下来到队列的头部取出最早进入队列的结点，
//    重复前面的打印操作，直至队列中所有的结点都被打印出来为止。

    public static void test20(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Queue<BinaryTreeNode> list = new LinkedList<>();

        list.add(treeNode);

        BinaryTreeNode curNode;
        while (!list.isEmpty()) {
            curNode = list.remove();
            System.out.println(curNode.value + "");
            if (curNode.left != null) {
                list.add(curNode.left);
            }
            if (curNode.right != null) {
                list.add(curNode.right);
            }
        }
    }

//    一、题目
//    输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。
//    从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
//
//    二、解题思路
//    由于路径是从根结点出发到叶结点， 也就是说路径总是以根结点为起始点，因此我们首先需要遍历根结点
//    。在树的前序、中序、后序三种遍历方式中，只有前序遍历是首先访问根结点的。
//
//    当用前序遍历的方式访问到某一结点时， 我们把该结点添加到路径上，并累加该结点的值。
//    如果该结点为叶结点并且路径中结点值的和刚好等于输入的整数， 则当前的路径符合要求，
//    我们把它打印出来。如果当前结点不是叶结点，则继续访问它的子结点。当前结点访问结束后，
//    递归函数将自动回到它的父结点。因此我们在函数退出之前要在路径上删除当前结点并减去当前结点的值，
//    以确保返回父结点时路径刚好是从根结点到父结点的路径。
//
//    不难看出保存路径的数据结构实际上是一个枝， 因为路径要与递归调用状态一致，
//    而递归调用的本质就是一个压栈和出栈的过程。

    public static boolean test22(BinaryTreeNode root, int expectedSum) {

        List<Integer> list = new ArrayList<>();
        if (root != null) {
            findPath(root, 0, expectedSum, list);
        }
        return false;
    }

    public static void findPath(BinaryTreeNode root, int curSum, int expectedSum, List<Integer> result) {

        if (root != null) {
            curSum += root.value;
            result.add(root.value);

            if (curSum < expectedSum) {
                findPath(root.left, curSum, expectedSum, result);
                findPath(root.right, curSum, expectedSum, result);
            } else if (curSum == expectedSum) {
                if (root.left == null && root.right == null) {
                    System.out.println(result);
                }
            }
            result.remove(result.size() - 1);
        }
    }
//    一、题目
//    请实现函数ComplexListNode clone(ComplexListNode head),复制一个复杂链表。在复杂链表中，
//    每个结点除了有一个next 域指向下一个结点外，还有一个sibling 指向链表中的任意结点或者null。
//
//    二、解题思路
//    在不用辅助空间的情况下实现O(n)的时间效率。
//
//    第一步：仍然是根据原始链表的每个结点N 创建对应的N’。把N’链接在N的后面。
//
//    第二步：设置复制出来的结点的sibling。假设原始链表上的N的sibling指向结点S，
//    那么其对应复制出来的N’是N的next指向的结点，同样S’也是S的next指向的结点。
//
//    第三步：把这个长链表拆分成两个链表。把奇数位置的结点用next . 链接起来就是原始链表，
//    把偶数位置的结点用next 链接起来就是复制 出来的链表。

    public static class ComplexListNode {
        int value;
        ComplexListNode next;
        ComplexListNode sibling;
    }

    public static ComplexListNode test23(ComplexListNode head) {
        if (head == null) {
            return null;
        }

        cloneNodes(head);
        connectNodes(head);
        return reconnectNodes(head);
    }

    public static void cloneNodes(ComplexListNode head) {
        while (head != null) {
            ComplexListNode tmp = new ComplexListNode();
            tmp.value = head.value;
            tmp.next = head.next;
            head.next = tmp;
            head = tmp.next;
        }
    }

    public static void connectNodes(ComplexListNode head) {
        while (head != null) {
            if (head.sibling != null) {
                head.next.sibling = head.sibling.next;
            }
            head = head.next.next;
        }
    }

    public static ComplexListNode reconnectNodes(ComplexListNode head) {
        if (head == null) {
            return null;
        }
        ComplexListNode newHead = head.next;
        ComplexListNode pointer = newHead;
        head.next = newHead.next;
        head = head.next;

        while (head != null) {
            pointer.next = head.next;
            pointer = pointer.next;
            head.next = pointer.next;
            head = pointer.next;
        }
        return newHead;
    }
//    一、题目
//    输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
//
//    二、解题思路
//    在二叉树中，每个结点都有两个指向子结点的指针。在双向链表中，每个结点也有两个指针，
//    它们分别指向前一个结点和后一个结点。由于这两种结点的结构相似，同时二叉搜索树也是一种排序的数据结构，
//    因此在理论上有可能实现二叉搜索树和排序的双向链表的转换。
//
//    在搜索二叉树中，左子结点的值总是小于父结点的值，右子结点的值总是大于父结点的值。因此我们在转换成排序双向链表时，
//    原先指向左子结点的指针调整为链表中指向前一个结点的指针，原先指向右子结点的指针调整为链表中指向后一个结点指针。
//    接下来我们考虑该如何转换。
//
//    由于要求转换之后的链表是排好序的，我们可以中序遍历树中的每一个结点，
//    这是因为中序遍历算法的特点是按照从小到大的顺序遍历二叉树的每一个结点。当遍历到根结点的时候，
//    我们把树看成三部分：根结点，左子树，右子树。根据排序链表的定义，根结点将和它的左子树的最大一个结点链接起来，
//    同时它还将和右子树最小的结点链接起来。

    public static BinaryTreeNode test24(BinaryTreeNode root) {

        BinaryTreeNode[] lastNode = new BinaryTreeNode[1];
        converNode(root, lastNode);

        BinaryTreeNode head = lastNode[0];
        while (head != null && head.left != null) {
            head = head.left;
        }
        return head;
    }

    public static void converNode(BinaryTreeNode node, BinaryTreeNode[] lastNode) {
        if (node != null) {
            if (node.left != null) {
                converNode(node.left, lastNode);
            }
            node.left = lastNode[0];
            if (lastNode[0] != null) {
                lastNode[0].right = node;
            }
            lastNode[0] = node;
            if (node.right != null) {
                converNode(node.right, lastNode);
            }
        }
    }

    //    一、题目
//    输入一个字符串，打印出该字符串中字符的所有排列。例如输入字符串abc。
//    则打印出由字符a、b、c 所能排列出来的所有字符串abc、acb、bac 、bca、cab 和cba 。
//
//    二、解题思路
//    把一个字符串看成由两部分组成：第一部分为它的第一个字符，第二部分是后面的所有字符。
//
//    我们求整个字符串的排列，可以看成两步：首先求所有可能出现在第一个位置的字符，
//    即把第一个字符和后面所有的字符交换。这个时候我们仍把后面的所有字符分成两部分：后面字符的第一个字符，以及这个字符之后的所有字符。
//
//    这其实是很典型的递归思路。
    public static void test25(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] chars = str.toCharArray();

        permutation(chars, 0);
    }

    public static void permutation(char[] chars, int begin) {

        if (begin > chars.length - 2) {
            System.out.println(new String(chars) + "->");
            return;
        }

        char tmp;
        char[] copyChars = null;
        for (int i = begin; i < chars.length; i++) {
            if (begin < chars.length - 2) {
                copyChars = chars.clone();
            } else {
                copyChars = chars;
            }

            tmp = copyChars[begin];
            copyChars[begin] = copyChars[i];
            copyChars[i] = tmp;

            permutation(copyChars, begin + 1);
        }
    }


//    一、题目
//    数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
//
//    二、解题思路
//    解法一：基于Partition 函数的O(n)算法
//
//    数组中有一个数字出现的次数超过了数组长度的一半。如果把这个数组排序，
//    那么排序之后位于数组中间的数字一定就是那个出现次数超过数组长度一半的数字。
//    也就是说，这个数字就是统计学上的中位数，即长度为n 的数组中第n/2 大的数字。
//
//    这种算法是受快速排序算法的启发。在随机快速排序算法中，我们先在数组中随机选择一个数字，
//    然后调整数组中数字的顺序， 使得比选中的数字小数字都排在它的左边，比选中的数字大的数字都排在它的右边。
//    如果这个选中的数字的下标刚好是n/2，那么这个数字就是数组的中位数。如果它的下标大于n/2 ，
//    那么中位数应该位于它的左边，我们可以接着在它的左边部分的数组中查找。如果它的下标小于n/2，
//    那么中位数应该位于它的右边，我们可以接着在它的右边部分的数组中查找。这是一个典型的递归过程。
//
//    解法二：根据数组组特点找出O(n)的算法
//
//    数组中有一个数字出现的次数超过数组长度的一半，也就是说它出现的次数比其他所有数字出现次数的和还要多。
//    因此我们可以考虑在遍历数组的时候保存两个值： 一个是数组中的一个数字， 一个是次数。
//    当我们遍历到下一个数字的时候，如果下一个数字和我们之前保存的数字相同，则次数加1，
//    如果下一个数字和我们之前保存的数字不同，则次数减1 。如果次数为零，我们需要保存下一个数字，并把次数设为1 。
//    由于我们要找的数字出现的次数比其他所有数字出现的次数之和还要多，那么要找的数字肯定是最后一次把次数设为1 时对应的数字。
//
//    本题采用第二种实现方式

    public static int test26(int[] numbers) {
        // 输入校验
        if (numbers == null || numbers.length < 1) {
            throw new IllegalArgumentException("array length must large than 0");
        }
        int result = numbers[0];
        int count = 1;

        for (int i = 1; i < numbers.length; i++) {
            if (count == 0) {
                result = numbers[i];
                count = 1;
            }
            if (result == numbers[i]) {
                count++;
            } else {
                count--;
            }

        }
        count = 0;
        for (int number : numbers) {

            if (result == number) {
                count++;
            }
        }
        if (count > numbers.length / 2) {
            return result;
        } else {
            throw new IllegalArgumentException("invalid input");
        }
    }

//    一、题目
//    输入n个整数，找出其中最小的k个数。
//
//    例子说明：
//
//    例如输入4 、5 、1、6、2、7、3 、8 这8 个数字，则最小的4 个数字是1 、2、3 、4

//    二、解题思路
//    解法一：O(n)时间算法，只有可以修改输入数组时可用。
//
//    可以基于Partition函数来解决这个问题。如果基于数组的第k个数字来调整，
//    使得比第k个数字小的所有数字都位于数组的左边，比第k个数字大的所有数字都位于数组的右边。
//    这样调整之后，位于数组中左边的k个数字就是最小的k 个数字（这k 个数字不一定是排序的〉。
//
//    解法二： O（nlogk）的算法，精剧适合处理海量数据。
//
//    先创建一个大小为k的数据容器来存储最小的k个数字，接下来我们每次从输入的n个整数中读入一个数．
//    如果容器中已有的数字少于k个，则直接把这次读入的整数放入容器之中：如果容器中己有k 数字了，
//    也就是容器己满，此时我们不能再插入新的数字而只能替换已有的数字。找出这己有的k 个数中的最大值，
//    然后1在这次待插入的整数和最大值进行比较。如果待插入的值比当前己有的最大值小，
//    则用这个数替换当前已有的最大值：如果待插入的值比当前已有的最大值还要大，
//    那么这个数不可能是最小的k个整数之一，于是我们可以抛弃这个整数。
//
//    因此当容器满了之后，我们要做3 件事情： 一是在k 个整数中找到最大数：
//    二是有可能在这个容器中删除最大数： 三是有可能要插入一个新的数字。
//    我们可以使用一个大顶堆在O(logk）时间内实现这三步操作。

    public static void test27(int[] input, int[] output) {

        if (input == null || output == null || output.length <= 0 || input.length < output.length) {
            throw new IllegalArgumentException("Invalid args");
        }

        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);
        int target = output.length - 1;

        while (index != target) {
            if (index < target) {
                start = index + 1;
            } else {
                end = index - 1;
            }
            index = partition(input, start, end);
        }
        System.arraycopy(input, 0, output, 0, output.length);
    }

    private static int partition(int[] input, int start, int end) {
        int tmp = input[start];
        while (start < end) {
            while (start < end && input[end] >= tmp) {
                end--;
            }
            input[start] = input[end];

            while (start < end && input[start] <= tmp) {
                start++;
            }
            input[end] = input[start];
        }
        input[start] = tmp;
        return start;
    }


//    一、题目
//    输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
//
//    例子说明：
//
//    例如输入的数组为{1, -2, 3, 10, -4, 7, 2, -5}，和最大的子数组为｛3, 10, -4, 7, 2}。因此输出为该子数组的和18 。
//
//        二、解题思路
//        解法一：举例分析数组的规律。
//
//        我们试着从头到尾逐个累加示例数组中的每个数字。初始化和为0。第一步加上第一个数字1， 此时和为1。
//        接下来第二步加上数字-2，和就变成了-1。第三步刷上数字3。我们注意到由于此前累计的和是－1 ，小于0，那如果用-1 加上3 ，
//        得到的和是2 ， 比3 本身还小。也就是说从第一个数字开始的子数组的和会小于从第三个数字开始的子数组的和。
//        因此我们不用考虑从第一个数字开始的子数组，之前累计的和也被抛弃。
//
//        我们从第三个数字重新开始累加，此时得到的和是3 。接下来第四步加10，得到和为13 。
//        第五步加上-4， 和为9。我们发现由于-4 是一个负数，因此累加-4 之后得到的和比原来的和还要小。
//        因此我们要把之前得到的和13 保存下来，它有可能是最大的子数组的和。
//        第六步加上数字7，9 加7 的结果是16，此时和比之前最大的和13 还要大， 把最大的子数组的和由13更新为16。
//        第七步加上2，累加得到的和为18，同时我们也要更新最大子数组的和。
//        第八步加上最后一个数字-5，由于得到的和为13 ，小于此前最大的和18，
//        因此最终最大的子数组的和为18 ，对应的子数组是｛3, 10, -4, 7, 2｝。
//
//        解法二： 应用动态归划法。
//
//        可以用动态规划的思想来分析这个问题。如果用函数f(i)表示以第i个数字结尾的子数组的最大和，
//        那么我们需要求出max[f(i)]，其中0 <= i < n。我们可用如下边归公式求f(i):
//
//
//
//        这个公式的意义：当以第i-1 个数字结尾的子数组中所有数字的和小于0时，
//        如果把这个负数与第i个数累加，得到的结果比第i个数字本身还要小，
//        所以这种情况下以第i个数字结尾的子数组就是第i个数字本身。
//        如果以第i-1 个数字结尾的子数组中所有数字的和大于0 ,
//        与第i 个数字累加就得到以第i个数字结尾的子数组中所有数字的和。

    public static int test28(int[] arr) {

        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException("Array must contain an element");
        }
        int max = Integer.MIN_VALUE;
        int curMax = 0;

        for (int i : arr) {
            if (curMax < 0) {
                curMax = i;
            } else {
                curMax += i;
            }
            if (max > curMax) {
                max = curMax;
            }
        }
        return max;
    }

//    一、题目
//    输入一个整数n，求从1 到n这n个整数的十进制表示中1 出现的次数。
//
//    举例说明：
//
//    例如输入12 ，从1 到12 这些整数中包含1 的数字有1、10、11 和12，1 一共出现了5 次。
//
//    二、解题思路
//    第一种：不考虑时间效率的解法
//
//    累加1 到n 中每个整数中1出现的次数。我们可以每次通过对10 求余数判断整数的个位数字是不是1 。
//    如果这个数字大于10，除以10 之后再判断个位数字是不是1 。
//
//    第二种：从数字规律着手明显提高时间效率的解法
//
//    21345 作为例子来分析。我们把从1 到21345 的所有数字分为两段， 一段是从1 到1345，另一段是从1346 到21345。
//
//    我们先看从01346 到21345 中1 出现的次数。1 的出现分为两种情况。
//    首先分析1出现在最高位（本例中是万位）的情况。从01346 到21345 的数字中，
//    1出现在10000～19999 这10000 个数字的万位中， 一共出现了10000(10^4)个。
//
//    值得注意的是， 并不是对所有5 位数而言在万位出现的次数都是10000 个。
//    对于万位是1 的数字比如输入12345, 1 只出现在10000～ 12345 的万位，
//    出现的次数不是10^4 次，而是2346 次，也就是除去最高数字之后剩下的数字再加上1 （即2345+1=2346 次）。
//
//    接下来分析1出现在除最高位之外的其他四位数中的情况。
//    例子中01346～21345 这20000 个数字中后4 位中1 出现的次数是2000 次。
//    由于最高位是2，我们可以再把1346～21345 分成两段， 01346～11345 和11346～21345 。
//    每一段剩下的4 位数字中， 选择其中一位是1 ，其余三位可以在0～9 这10 个数字中任意选择，
//    因此根据排列组合原则，总共出现的次数是2*10^3=2000，一共有4位可以选择，所以一共是8000。
//
//    至于从1 到1345 中1 出现的次数，我们就可以用递归求得了。
//    这也是我们为什么要把1～21345 分成1～ 1345 和1346～21345 两段的原因。
//    因为把21345 的最高位去掉就变成1345 ，便于我们采用递归的思路。

}
