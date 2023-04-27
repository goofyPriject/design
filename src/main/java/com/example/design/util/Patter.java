package com.example.design.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patter {

    public static void main(String[] args) {
        final String regex = "^([1-9]\\d{3})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (0?\\d|1\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
        final String string = "2022-10-18 13:33:33";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.matches());

        //删除提交代码

        //或者使用
        Pattern.matches(regex, string);

        //或者使用
        System.out.println(string.matches(regex));
    }

}
