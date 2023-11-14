package cn.aireco.platform.function;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Multiple {


    public static void main(String[] args) {
        String address = "江苏省南京市鼓楼区中山北路1号";
        List<Term> segResult = HanLP.segment(address);
        System.out.println("省份：" + segResult.get(0));
        System.out.println("城市：" + segResult.get(1));
        System.out.println("区县：" + segResult.get(2));
    }

    /**
     * 在这个函数中，首先我们对输入的数字进行排序。然后，我们从第一个数字开始，每次都将当前的最小公倍数与下一个数字的最大公约数相乘，再除以它们的最小公倍数，
     * 得到新的最小公倍数。最后返回这个最小公倍数。
     * gcd函数是用于计算两个数字的最大公约数的。它是通过不断地将较大的数字除以较小的数字，并交换这两个数字，直到其中一个变为0为止。此时剩下的那个数字就是最大公约数。
     */
    public static int findLCM(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Arrays.sort(nums);
        int lcm = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int a = lcm * nums[i];
            int b = gcd(lcm, nums[i]);
            System.out.println(a+"<--------->"+b);
            lcm = lcm * nums[i] / gcd(lcm, nums[i]);
            System.out.println(lcm);
        }
        return lcm;
    }

    private static int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 % num2;
            num1 = num2;
            num2 = temp;
        }
        return num1;
    }

}

