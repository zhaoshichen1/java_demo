package j2seDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatternMatcher {
    public static void main(String[] args){
        // 示例的正则，20xx年份
        String regex = "20\\d\\d";

        // 1. 基本用法，判断是否符合正则的格式
        System.out.println("2019".matches(regex)); // true

        // 1. 依次找到所有符合条件的字符串并输出
        Matcher m = Pattern.compile(regex).matcher("2033，2088，1977，哈哈");
        while (m.find()) {
            System.out.println(m.group() + "子串的起始位置：" + m.start() + "，其结束位置：" + m.end());
        }

        // 2.1 正则匹配后替换，用matcher
        System.out.println(m.replaceAll("[%year%]"));
        // 2.2 直接用String的方法
        System.out.println("2033，2088，1977，哈哈".replaceAll(regex, "[%year%]"));
    }
}
