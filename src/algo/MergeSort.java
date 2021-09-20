package algo;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{-1, -2, 9, 10, 67, 0, -3, 5, -99999};
        int[] sorted = sort(arr);
        Arrays.stream(sorted).asDoubleStream().forEach(System.out::println);
    }

    public static int[] sort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int left = 0;
        int right = arr.length - 1;
        return sort(arr, left, right);
    }

    public static int[] sort(int[] arr, int left, int right) {
        if (left == right) { // is sorted
            return new int[]{arr[left]};
        }

        int mid = (left + right) / 2;
        int[] leftArr = sort(arr, left, mid);
        int[] rightArr = sort(arr, mid + 1, right);

        return merge(leftArr, rightArr);
    }

    private static int[] merge(int[] leftArr, int[] rightArr) {
        int[] newArr = new int[leftArr.length + rightArr.length];
        int leftInd = 0, rightInd = 0;

        for (int i = 0; i < newArr.length; i++) {
            int left = leftInd < leftArr.length ? leftArr[leftInd] : Integer.MAX_VALUE;
            int right = rightInd < rightArr.length ? rightArr[rightInd] : Integer.MAX_VALUE;

            if (left < right) {
                newArr[i] = left;
                leftInd++;
            } else {
                newArr[i] = right;
                rightInd++;
            }
        }
        return newArr;
    }
}
