package com.hiido.shop.scawelshop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(0);
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
    }
}
