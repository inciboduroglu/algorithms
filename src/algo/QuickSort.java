package algo;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.swap;

public class QuickSort {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-3, 9999, 60, -200, 5, 3, 1, 0, -2, -3);

        sort(list);
        list.forEach(System.out::println);
    }

    private static void sort(List<Integer> list) {
        int low = 0;
        int high = list.size() - 1;
        sort(list, low, high);
    }

    private static void sort(List<Integer> list, int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(list, low, high);
        sort(list, low, p - 1);
        sort(list, p + 1, high);
    }

    private static int partition(List<Integer> list, int low, int high) {
        for (int i = low; i < high; i++) {
            if (list.get(i) <= list.get(high)) {
                swap(list, i, low);
                low++;
            }
        }
        swap(list, low, high);
        return low;
    }
}
