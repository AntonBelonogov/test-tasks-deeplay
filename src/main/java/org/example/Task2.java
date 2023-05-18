package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task2 {

    public static void run() {
        List<Integer> list = Task1.createRandomList(20, 5);
        System.out.println(list);

        getIntegerCount(list);
    }

    public static void getIntegerCount(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();

        for (Integer integer : list) {
            if (map.containsKey(integer)) {
                map.compute(integer, (integer1, integer2) -> integer2 + 1);
            } else {
                map.put(integer, 1);
            }
        }

        int maxCount = Collections.max(map.values());

        map = map.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
    }
}
