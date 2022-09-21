package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {
    public static void main(String[] args) {
        int arr[] = {1, 5, 3, 3, 2, 3};
        System.out.println(minValue(arr));
        System.out.println(oddOrEven(List.of(10, 11, 12)));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> (a * 10) + b);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {

/*        boolean isOdd = integers
                .stream()
                .reduce((a, b) -> a + b)
                .stream()
                .anyMatch(x -> x % 2 == 0);

        return integers
                .stream()
                .filter(x -> isOdd ? (x % 2 == 0) : (x % 2 != 0))
                .collect(Collectors.toList());*/

        return integers
                .stream()
                .filter(x -> integers
                        .stream()
                        .reduce((a, b) -> a + b)
                        .stream()
                        .anyMatch(y -> y % 2 == 0) == (x % 2 != 0))
                .collect(Collectors.toList());
    }
}