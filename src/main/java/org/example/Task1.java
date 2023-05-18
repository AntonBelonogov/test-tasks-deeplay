package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Task1 {

    public static void run() {
        Integer size = 20;
        Integer maxValue = 5;

        List<Integer> list = createRandomList(size, maxValue);
        System.out.println(list);

        sortList(list);
        System.out.println(list);
    }

    public static List<Integer> createRandomList(Integer size, Integer maxValue) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(maxValue));
        }
        return list;
    }

    private static List<Integer> sortList(List<Integer> list) {
        Collections.sort(list, (a, b) -> {
            boolean aOdd = a % 2 != 0;
            boolean bOdd = b % 2 != 0;

            if (aOdd && !bOdd) {
                return -1;
            } else if (!aOdd && bOdd) {
                return 1;
            } else if (aOdd && bOdd) {
                return a.compareTo(b);
            } else if (a == 0 && b != 0) {
                return -1;
            } else if (a != 0 && b == 0) {
                return 1;
            } else {
                return b.compareTo(a);
            }
        });
        return list;
    }
}
