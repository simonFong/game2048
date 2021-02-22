package com.simonfong.game2048;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by fengzimin  on  2021/2/22.
 * interface by
 */
public class DataHelper {
    public static List<Integer> start(List<Integer> list) {
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        List<Integer> unSelect = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                unSelect.add(i);
            }
        }
        Random random = new Random();
        int n = random.nextInt(unSelect.size());
        list.set(unSelect.get(n), 1);
        return list;
    }

    public static List<Integer> up(List<Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            List<Integer> objects = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (j % count == i) {
                    objects.add(list.get(j));
                }
            }
            calc(objects);
            sort(objects);
            for (int k = 0; k < objects.size(); k++) {
                list.set(k * count + i, objects.get(k));
            }
        }
        return list;
    }

    public static List<Integer> sort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) != 0) {
                continue;
            }
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) == 0) {
                    continue;
                } else {
                    Collections.swap(list, i, j);
                    break;
                }
            }
        }
        return list;
    }

    public static List<Integer> calc(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) == 0) {
                continue;
            }
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) == 0) {
                    continue;
                } else if (list.get(i).intValue() == list.get(j).intValue()) {
                    list.set(i, list.get(i) + 1);
                    list.set(j, 0);
                } else {
                    break;
                }
            }
        }
        return list;

    }

    public static void print(List list, int count) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "");
            if (i % count == count - 1) {
                System.out.print("\n");
            }
        }
        System.out.println("----");
    }
}
