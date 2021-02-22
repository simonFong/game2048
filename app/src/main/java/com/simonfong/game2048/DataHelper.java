package com.simonfong.game2048;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by fengzimin  on  2021/2/22.
 * interface by
 */
public class DataHelper {
    public static boolean start(List<Integer> list) {
        if (list.size() == 0) {
            return false;
        }
        List<Integer> unSelect = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                unSelect.add(i);
            }
        }
        if (unSelect.size() == 0) {
            return false;
        }
        Random random = new Random();
        int n = random.nextInt(unSelect.size());
        list.set(unSelect.get(n), 1);
        return true;
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

    public static List<Integer> down(List<Integer> list, int count) {
        for (int i = count - 1; i >= 0; i--) {
            List<Integer> objects = new ArrayList<>();
            for (int j = list.size() - 1; j >= 0; j--) {
                if (j % count == i) {
                    objects.add(list.get(j));
                }
            }
            calc(objects);
            sort(objects);
            for (int k = objects.size() - 1; k >= 0; k--) {
                list.set(k * count + i, objects.get(objects.size() - 1 - k));
            }
        }
        return list;
    }

    public static List<Integer> left(List<Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            List<Integer> objects = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (count * i <= j && j < count * (i + 1)) {
                    objects.add(list.get(j));
                }
            }
            calc(objects);
            sort(objects);
            print(objects, count);
            for (int k = 0; k < objects.size(); k++) {
                list.set(k + count * i, objects.get(k));
            }
        }
        return list;
    }

    public static List<Integer> right(List<Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            List<Integer> objects = new ArrayList<>();
            for (int j = list.size() - 1; j >= 0; j--) {
                if (j >= count * (count - 1 - i) && j < count * (count - i)) {
                    objects.add(list.get(j));
                }
            }
            calc(objects);
            sort(objects);
            print(objects, count);
            for (int k = objects.size() - 1; k >= 0; k--) {
                list.set(count * (count - i) - 1 - k, objects.get(k));
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

    public static void saveMaxPoint(int point) {
        //获取sharedPreferences对象
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences("game", Context.MODE_PRIVATE);
        //获取editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        //存储键值对
        editor.putInt("maxPoint", point);

        //提交
        editor.commit();//提交修改
    }

    public static int getMaxPoint() {
        //获取sharedPreferences对象
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences("game", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("maxPoint", 0);
    }
}
