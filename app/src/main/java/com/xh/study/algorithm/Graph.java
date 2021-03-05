package com.xh.study.algorithm;

public class Graph {

    //节点数目
    protected int size;
    //定义数组，保存顶点信息
    protected String[] nodes;

    //定义矩阵保存顶点信息
    protected int[][] edges;

    /**
     * A B C D E F G
     * A  0 0 1 1 0 1 0
     * B  0 0 1 0 0 0 0
     * C  1 1 0 1 0 0 0
     * D  1 0 1 0 0 0 0
     * E  0 0 0 0 0 0 1
     * F  1 0 0 0 0 0 1
     * G  0 0 0 0 1 1 0
     */
    public Graph() {
        //初始化顶点
        nodes = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        size = nodes.length;
        visit = new int[size];
        queue = new int[size];
        //初始化边---- 为了直观，做一个常量定义
        final int A = 0, B = 1, C = 2, D = 3, E = 4, F = 5, G = 6;
        edges = new int[size][size];
        edges[A][C] = 1;
        edges[A][D] = 1;
        edges[A][F] = 1;
        edges[B][C] = 1;
        edges[C][A] = 1;
        edges[C][D] = 1;
        edges[C][B] = 1;
        edges[D][A] = 1;
        edges[D][C] = 1;
        edges[E][G] = 1;
        edges[F][A] = 1;
        edges[F][G] = 1;
        edges[G][F] = 1;
        edges[G][E] = 1;
    }


    //访问过的保存一下  下标代表顶点，值代表访问过
    private int[] visit;

    //A->C->B->D->F->G->E->
    //深度遍历
    public void deepFirst(int start) {
        visit[start] = 1;
        System.out.print(nodes[start] + "->");
        for (int i = 0; i < size; i++) {
            if (edges[start][i] == 1 && visit[i] == 0) {
                deepFirst(i);
            }
        }
    }

    //广度遍历
    private int[] queue;

    public void breadthFirst(int start) {
        visit[start] = 1;
        queue[0] = start;
        breadthFirst(0, 0);


    }

//    A->C->D->F->B->G->E->
    public void breadthFirst(int front, int tail) {
        int last = tail;
        for (int i = front; i <= tail; i++) {
            System.out.print(nodes[queue[i]] + "->");

            for (int j = 0; j < size; j++) {
                if (edges[queue[i]][j] == 1 && visit[j] == 0) {
                    visit[j] = 1;
                    queue[++last] = j;
                }
            }
        }
        if (last > tail) {
            breadthFirst(tail + 1, last);
        }

    }

}
