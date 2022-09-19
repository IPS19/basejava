package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {
    public static void main(String[] args) {
        int arr[] = {1, 5, 3, 3, 2, 3};
        minValue(arr);
        System.out.println(oddOrEven(List.of(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 111, 116)));
    }

    static int minValue(int[] values) {
        int arr[] = Arrays.stream(values).distinct().sorted().toArray();
        int size = arr.length - 1;
        int result = 0;
        for (int x : arr) {
            result += x * Math.pow(10, size--);
        }
        //result = Arrays.stream(arr).map(x -> x * Math.pow(10, size--));
        System.out.println(result);
        return result;
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().reduce((a, b) -> a + b).stream().anyMatch(x -> x % 2 == 0)) {
            return integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        }
        return integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());

    }
}

