package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task4 {
    public static void run() {
        int[] numbers = {17, 12, 10, 4, 9, 8};
        int groups = 2;

        distributeNumbers(numbers, groups);
    }

    public static void distributeNumbers(int[] numbers, int groups) {
        int sum = Arrays.stream(numbers).sum();

        if (sum % groups != 0) {
            System.out.println("Невозможно распределить числа на заданное число групп.");
            return;
        }

        int targetSum = sum / groups;
        List<List<Integer>> distribution = new ArrayList<>();
        for (int i = 0; i < groups; i++) {
            distribution.add(new ArrayList<>());
        }

        boolean isPossible = distributeRecursively(numbers, 0, distribution, targetSum);
        if (isPossible) {
            System.out.println("Исходные данные: " + Arrays.toString(numbers));
            System.out.println("Результаты распределения:");
            for (List<Integer> group : distribution) {
                System.out.println(group);
            }
        } else {
            System.out.println("Невозможно распределить числа на заданное число групп.");
        }
    }

    private static boolean distributeRecursively(int[] numbers, int index, List<List<Integer>> distribution, int targetSum) {
        if (index == numbers.length) {
            for (List<Integer> group : distribution) {
                if (group.stream().mapToInt(Integer::intValue).sum() != targetSum) {
                    return false;
                }
            }
            return true;
        }

        int number = numbers[index];
        for (List<Integer> group : distribution) {
            if (group.stream().mapToInt(Integer::intValue).sum() + number <= targetSum) {
                group.add(number);
                if (distributeRecursively(numbers, index + 1, distribution, targetSum)) {
                    return true;
                }
                group.remove(group.size() - 1);
            }
        }

        return false;
    }
}
