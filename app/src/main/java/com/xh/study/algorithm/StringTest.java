package com.xh.study.algorithm;

import com.blankj.utilcode.util.StringUtils;

public class StringTest {


    //朴素的模式匹配算法   暴力匹配算法
    public static void bruteForce(String s, String p) {
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(p)) {
            return;
        }

        int sLength = s.length();
        int pLength = p.length();
        if (sLength < pLength) {
            return;

        }

        int i = 0, j = 0;
        while (i < sLength && j < pLength) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
                j = 0;
            }
        }

        if (j == pLength) {
            System.out.println("匹配--" + (i - j));
        } else {
            System.out.println("不匹配");
        }
    }


    //kmp 算法

    //    构建next表
    public static int[] buildNext(String p) {
        int[] n = new int[p.length()];
        int m = p.length(), j = 0;
        int t = n[0] = -1;

        while (j < m - 1) {
            if (t < 0 || p.charAt(j) == p.charAt(t)) {
                j++;
                t++;
                n[j] = t;
            } else {
                t = n[t];
            }
        }

        return n;
    }

    public static void a(String a) {
        int length = a.length();

        int start = 0;
        int[] table = new int[length];

        table[0] = -1;
        int t = -1;

        while (start < length - 1) {
            if (t == -1 || a.charAt(start) == a.charAt(t)) {
                start++;
                t++;
                table[start] = t;
            } else {
                t = table[t];
            }
        }
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i]+"---");
        }

    }


    public static void kmp(String s, String p) {
        int[] next = buildNext(p);// 调用next（String p）方法
        int index = -1;// 成功匹配的位置
        int sLength = s.length();// 主串长度
        int pLength = p.length();// 子串长度
        if (sLength < pLength) {
            System.out.println("Error.The main string is greater than the sub string length.");
            return;
        }
        int i = 0;
        int j = 0;

        while (i < sLength && j < pLength) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= pLength) {// 匹配成功
            index = i - j;
            System.out.println("Successful match,index is:" + index);
        } else {// 匹配失败
            System.out.println("Match failed.");
        }
    }





}
