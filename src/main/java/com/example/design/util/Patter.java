package com.example.design.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Patter {

    public static void main(String[] args) {
        final String regex = "^([1-9]\\d{3})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (0?\\d|1\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
        final String string = "2022-10-18 13:33:33";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.matches());

        String reg = "^[A-Za-z0-9,]+$";
        String str = ",";
        Pattern pattern1 = Pattern.compile(reg, Pattern.MULTILINE);
        Matcher matcher1 = pattern1.matcher(str);
        System.out.println(matcher1.matches());


        //或者使用
        Pattern.matches(regex, string);

        //或者使用
        int i = 20;
        System.out.println(i/100+1);
    }

}
