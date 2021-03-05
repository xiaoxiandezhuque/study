package com.xh.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class SortTest {

    //冒泡
    public void sort1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    //选择
    public void sort2(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }

    //插入
    public void sort3(int[] array) {
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = i - 1; j >= 0; j--) {
//                if (array[j] > array[j + 1]) {
//                    int temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
//                } else {
//                    break;
//                }
//            }
//        }

        for (int i = 0; i < array.length - 1; i++) {
            int current = i + 1;
            int curNum = array[current];
            while (current > 0 && curNum < array[current - 1]) {
                array[current] = array[current - 1];
                current--;
            }
            array[current] = curNum;
        }
    }

    //希尔
    public void sort4(int[] array) {
        int length = array.length;
        int gap = length / 2;
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                int start = i - gap;
                int temp = array[i];
                while (start >= 0 && array[start] > temp) {
                    array[start + gap] = array[start];
                    start -= gap;
                }
                array[start + gap] = temp;
            }
            gap /= 2;
        }
    }

    public static int[] ShellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    //归并
    public int[] sort5(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int middle = array.length / 2;

        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);

        return merge(sort5(left), sort5(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (leftIndex >= left.length) {
                result[i] = right[rightIndex++];
            } else if (rightIndex >= right.length) {
                result[i] = left[leftIndex++];
            } else if (left[leftIndex] >= right[rightIndex]) {
                result[i] = right[rightIndex++];
            } else {
                result[i] = left[leftIndex++];
            }
        }
        return result;
    }

    //快速
    public void sort6(int[] array, int start, int end) {
        if (array.length < 2 || start < 0 || end >= array.length || start > end) {
            return;
        }
        int standard = array[start];
        int i = start, j = end;
        while (i < j) {
            while (i < j && array[j] >= standard) {
                j--;
            }
            if (i < j) {
                array[i++] = array[j];
            }
            while (i < j && array[i] < standard) {
                i++;
            }
            if (i < j) {
                array[j--] = array[i];
            }
        }
        array[i] = standard;
        sort6(array, start, i - 1);
        sort6(array, i + 1, end);
    }

    //堆
    public void sort7(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int length = array.length;
        while (length > 1) {
            int lastIndex = length / 2 - 1;
            for (int i = lastIndex; i >= 0; i--) {
                repalceMax(array, i, length);
            }
            length--;
            swap(array, 0, length);
        }

    }

    private void repalceMax(int[] array, int index, int length) {
        int max = index;
        if (length > (2 * index + 1) && array[max] < array[2 * index + 1]) {
            max = 2 * index + 1;
        }
        if (length > (2 * index + 2) && array[max] < array[2 * index + 2]) {
            max = 2 * index + 2;
        }
        if (max != index) {
            swap(array, index, max);
            repalceMax(array, max, length);
        }

    }


    static int len;

    /**
     * 堆排序算法
     *
     * @param array
     * @return
     */
    public static int[] HeapSort(int[] array) {
        len = array.length;
        if (len < 1) return array;
        //1.构建一个最大堆
        buildMaxHeap(array);
        //2.循环将堆首位（最大值）与末位交换，然后在重新调整最大堆
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
        return array;
    }

    /**
     * 建立最大堆
     *
     * @param array
     */
    public static void buildMaxHeap(int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        for (int i = (len / 2 - 1); i >= 0; i--) { //感谢 @让我发会呆 网友的提醒，此处应该为 i = (len/2 - 1)
            adjustHeap(array, i);
        }
    }

    /**
     * 调整使之成为最大堆
     *
     * @param array
     * @param i
     */
    public static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[i * 2] > array[maxIndex])
            maxIndex = i * 2;
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
            maxIndex = i * 2 + 1;
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }

    /**
     * 交换数组内两个元素
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //计数
    public void sort8(int[] array) {
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int[] numArray = new int[max - min + 1];

        for (int i = 0; i < array.length; i++) {
            numArray[array[i] - min]++;
        }
        int j = 0;
        for (int i = 0; i < numArray.length; i++) {
            int cur = numArray[i];
            while (cur > 0) {
                array[j] = i;
                j++;
                cur--;
            }
        }
    }

    //桶
    public ArrayList<Integer> sort9(ArrayList<Integer> list, int saveNum) {
        if (list == null || list.size() < 2) {
            return list;
        }

        int max = list.get(0);
        int min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            max = Math.max(max, list.get(i));
            min = Math.min(min, list.get(i));
        }

        //计算桶的数量
        int bucketNum = (max - min) / saveNum + 1;
        //初始化桶的容器
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new ArrayList<>());
        }
        //把数据放入桶
        for (int i = 0; i < list.size(); i++) {
            bucketList.get((list.get(i) - min) / saveNum).add(list.get(i));
        }
        ArrayList<Integer> result = new ArrayList<>();
        //循环桶
        for (int i = 0; i < bucketNum; i++) {
            if (saveNum == 1) {
                for (int j = 0; j < bucketList.get(i).size(); j++) {
                    result.add(bucketList.get(i).get(j));
                }
            } else {
                //如果只存了多种数据，那么需要递归
                if (bucketNum == 1) {
                    saveNum--;
                }
                ArrayList<Integer> temp = sort9(bucketList.get(i), saveNum);
                for (int k = 0; k < temp.size(); k++) {
                    result.add(temp.get(k));
                }
            }

        }

        return result;

    }

    public static ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2)
            return array;
        int max = array.get(0), min = array.get(0);
        // 找到最大值最小值
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max)
                max = array.get(i);
            if (array.get(i) < min)
                min = array.get(i);
        }
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) { // 如果带排序数组中有重复数字时  感谢 @见风任然是风 朋友指出错误
                for (int j = 0; j < bucketArr.get(i).size(); j++)
                    resultArr.add(bucketArr.get(i).get(j));
            } else {
                if (bucketCount == 0)
                    bucketSize--;
                ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
                for (int j = 0; j < temp.size(); j++)
                    resultArr.add(temp.get(j));
            }
        }
        return resultArr;
    }

    //基数
    public void sort10(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        //找到最大数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        //初始化桶 0-9
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }
        int digit = 0;
        while (max > 0) {
            max /= 10;
            digit++;
        }

        for (int i = 0; i < digit; i++) {
            for (int j = 0, rem = powerBase10(i + 1), dev = powerBase10(i); j < array.length; j++) {
                bucketList.get(array[j] % rem / dev).add(array[j]);
            }
            int cur = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    array[cur++] = bucketList.get(j).get(k);
                }
                bucketList.get(j).clear();
            }
        }
    }

    private static int powerBase10(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }


    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2)
            return array;
        // 1.先算出最大数的位数；
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<Integer>());
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++)
                    array[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();
            }
        }
        return array;
    }
}
