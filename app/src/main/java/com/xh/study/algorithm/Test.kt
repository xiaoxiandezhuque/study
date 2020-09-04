package com.xh.test

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.blankj.utilcode.util.ImageUtils
import com.xh.study.algorithm.bean.BinaryTreeNode
import java.io.FileInputStream


fun main() {
//    println(JavaTest.text1(intArrayOf(5,2)))
//    println(JavaTest.test5(2))

//    for (i in 0 until 10) {
//        println(JavaTest.test6(i))
//    }

//    println(JavaTest.test7(intArrayOf(2,3,4,5,6,7,8,9)))

    //生成 链表
//    val head1 = Node()
//    head1.value = 0
//    var node1 = head1
//
//    val head2 = Node()
//    head2.value = 1
//    var node2 = head2
//
//    for (i in 2 until 10) {
//        val newNode = Node()
//        newNode.value = i
//        if (i % 2 == 0) {
//            node1.next = newNode
//            node1 = newNode
//        }else{
//            node2.next = newNode
//            node2 = newNode
//        }
//
//    }
//    //打印链表
//    var cur: Node? =JavaTest.test10(head1,head2)
//    while (cur != null) {
//        println(cur.value)
//        cur = cur.next
//    }
    //生成树
    val root = BinaryTreeNode()
    root.value = 0

    var cur = root
    for (i in 1 until 10) {
        if (i % 2 == 1) {
            val left = BinaryTreeNode()
            left.value = i
            val right = BinaryTreeNode()
            right.value = i + 1
            cur.left = left
            cur.right = right

            cur = left
        }
    }

//    JavaTest.test23(root,17)
//    printTree(root)

    //二维数组
//    val a = arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9))
////    for (i in a){
////        for (j in i){
////            println(j)
////        }
////    }
//    JavaTest.test13(a)

//    val minStack = MinStack()
//    minStack.push(4)
//    minStack.push(5)
//    minStack.push(1)
//    minStack.push(2)
//    minStack.push(5)
//    println(minStack.pop())
//    println(minStack.pop())
//    println(minStack.min())

    //转换为两位小数
//    var str = "1."
//    if (str.indexOf(".") > 0) {
//        println(str.substring(0, Math.min(str.indexOf(".") + 3, str.length)).toDouble())
//    }else{
//        println(str)
//    }
//    val tree = Tree()
//    tree.levelOrderTraverse(tree.create())
//    println()
//    tree.postOrderTraverse2(tree.create())

//    println(JavaTest.test27(intArrayOf(4,5,1,8,2), IntArray(3)))


//    val btimapAndDat = BtimapAndDat()
//    btimapAndDat.inToDat()


//    println(Dat.hexToByte("d9"))
//    val bitmap = ImageUtils.getBitmap("D:\\work\\Test\\app\\src\\main\\java\\com\\xh\\test\\22")
//    ImageUtils.save(
//        bitmap, "D:\\work\\Test\\app\\src\\main\\java\\com\\xh\\test\\33.jpeg",
//        Bitmap.CompressFormat.JPEG
//    )
}

private fun printTree(tree: BinaryTreeNode?) {

    if (tree != null) {
        println(tree.value)
        printTree(tree.left)
        printTree(tree.right)
    }

}