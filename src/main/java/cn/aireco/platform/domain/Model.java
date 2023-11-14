package cn.aireco.platform.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Model implements Serializable {

    private String json;


    public static void main(String[] args) {
        // n = 10;
        System.out.println(tailRecur(10, 0));
    }

    static int recur(int n) {
        // 终止条件
        if (n == 1)
            return 1;
        // 递：递归调用
        int res = recur(n - 1);
        // 归：返回结果
        return n + res;
    }

    /* 尾递归 */
    static int tailRecur(int n, int res) {
    // 终止条件
        if (n == 0)
            return res;
    // 尾递归调用
        return tailRecur(n - 1, res + n);
    }

}


