package cn.aireco.platform.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据脱敏
 */
public class DesensitizationUtil {

    private static String hide(String value) {
        boolean isChinese = isContainChinese(value);
        if (value.length() == 11 && !isChinese) {
            return hide(value, 3, 7);
        }
        if (value.length() == 18 && !isChinese) {
            return hide(value, 3, 14);
        }
        if (value.length() > 2) {
            return hide(value, 1, value.length() - 1);
        } else if (value.length() == 2) {
            return value.charAt(0) + "*";
        }
        return value;
    }

    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    protected static String hide(String str, int startInclude, int endExclude) {
        return replace(str, startInclude, endExclude, '*');
    }

    protected static String replace(String str, int startInclude, int endExclude, char replacedChar) {
        if (StringUtils.isEmpty(str)) {
            return str;
        } else if (str.length() <= 2) {
            char[] tmpChar = new char[str.length()];

            for (int i = 0; i < str.length(); ++i) {
                tmpChar[i] = replacedChar;
            }
            return new String(tmpChar);
        } else {
            int strLength = str.length();
            if (startInclude > strLength) {
                return str;
            } else {
                if (endExclude > strLength) {
                    endExclude = strLength;
                }
                if (startInclude > endExclude) {
                    return str;
                } else {
                    char[] chars = new char[strLength];

                    for (int i = 0; i < strLength; ++i) {
                        if (i >= startInclude && i < endExclude) {
                            chars[i] = replacedChar;
                        } else {
                            chars[i] = str.charAt(i);
                        }
                    }
                    return new String(chars);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(hide("浙江省"));
    }
}

