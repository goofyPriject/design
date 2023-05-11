package com.example.design.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class iterate {

    private static final Integer MAX_SEND = 3;

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int limit = countStep(list.size());
        //方法二：获取分割后的集合
        List<List<Integer>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> list.stream().skip(a * MAX_SEND).limit(MAX_SEND).parallel().collect(Collectors.toList())).collect(Collectors.toList());

        System.out.println(splitList);
    }

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_SEND - 1) / MAX_SEND;
    }
}
