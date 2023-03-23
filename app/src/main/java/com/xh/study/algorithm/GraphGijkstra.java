package com.xh.study.algorithm;

public class GraphGijkstra {
    //节点数目
    protected int size;
    //定义数组，保存顶点信息
    protected String[] nodes;
    //定义矩阵保存顶点信息
    protected int[][] edges;


    private int[] distances;//路径的权重值。。下标是点的数值
    private String[] way;//路径
    private int[] isMarked; //是否走过了

    public GraphGijkstra() {
        init();
        distances = new int[size];
        way = new String[size];
        isMarked = new int[size];
    }


    public void find(int start, int end) {
        for (int i = 0; i < size; i++) {
            distances[i] = Integer.MAX_VALUE;
            way[i] = "";
        }
        distances[start] = 0;
        way[start] = nodes[start];
        int node;
        while ((node = getShort()) >= 0) {
            search(node);
        }

        for (int i = 0; i < size; i++) {
            System.out.println(way[i]+"---权重值"+distances[i]);
        }
    }

    private void search(int spot) {
        isMarked[spot] = 1;
        for (int i = 0; i < size; i++) {
            int length = edges[spot][i];
            if (length != 0 && (length + distances[spot]) < distances[i]) {
                distances[i] = length + distances[spot];
                way[i] = way[spot] + "-->" + nodes[i];
            }
        }
    }

    private int getShort() {
        int last = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            if (isMarked[i] == 1) {
                continue;
            }
            if (distances[i] < min) {
                min = distances[i];
                last = i;
            }
        }
        return last;
    }


    private void init() {
        //初始化顶点
        nodes = new String[]{"AA", "A", "B", "C", "D", "E", "F", "G", "H", "M", "K", "N"};
        //节点编号-常量
        final int AA = 0, A = 1, B = 2, C = 3, D = 4, E = 5, F = 6, G = 7, H = 8, M = 9, K = 10, N = 11;
        size = nodes.length;

        edges = new int[size][size];
        edges[AA][A] = 3;
        edges[AA][B] = 2;
        edges[AA][C] = 5;
        edges[A][AA] = 3;
        edges[A][D] = 4;
        edges[B][AA] = 2;
        edges[B][C] = 2;
        edges[B][G] = 2;
        edges[B][E] = 3;
        edges[C][AA] = 5;
        edges[C][E] = 2;
        edges[C][B] = 2;
        edges[C][F] = 3;
        edges[D][A] = 4;
        edges[D][G] = 1;
        edges[E][B] = 3;
        edges[E][C] = 2;
        edges[E][F] = 2;
        edges[E][K] = 1;
        edges[E][H] = 3;
        edges[E][M] = 1;
        edges[F][C] = 3;
        edges[F][E] = 2;
        edges[F][K] = 4;
        edges[G][B] = 2;
        edges[G][D] = 1;
        edges[G][H] = 2;
        edges[H][G] = 2;
        edges[H][E] = 3;
        edges[K][E] = 1;
        edges[K][F] = 4;
        edges[K][N] = 2;
        edges[M][E] = 1;
        edges[M][N] = 3;
        edges[N][K] = 2;
        edges[N][M] = 3;
    }


}
