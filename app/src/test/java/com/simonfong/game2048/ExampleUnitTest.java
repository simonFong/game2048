package com.simonfong.game2048;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void game() {
        int count = 4;
        List<Integer> views = new ArrayList<>();
        views.add(0);
        views.add(0);
        views.add(0);
        views.add(0);

        views.add(0);
        views.add(0);
        views.add(0);
        views.add(0);

        views.add(0);
        views.add(0);
        views.add(0);
        views.add(0);

        views.add(0);
        views.add(0);
        views.add(0);
        views.add(0);

        //        print(views, count);

        DataHelper.start(views);
        DataHelper.start(views);
        DataHelper.start(views);
        DataHelper.start(views);
        DataHelper.start(views);
        DataHelper.start(views);
        //        print(start, count);

        List<Integer> start1 = DataHelper.start(views);
        print(start1, count);

        List<Integer> up = DataHelper.up(start1, count);
        print(up, count);

    }

    private void print(List list, int count) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "");
            if (i % count == count - 1) {
                System.out.print("\n");
            }
        }
        System.out.println("----");
    }

    @Test
    public void sort() {
        List<Integer> views = new ArrayList<>();
        views.add(0);
        views.add(1);
        views.add(1);
        views.add(1);
        print(DataHelper.calc(views), 4);
        print(DataHelper.sort(views), 4);
    }
}