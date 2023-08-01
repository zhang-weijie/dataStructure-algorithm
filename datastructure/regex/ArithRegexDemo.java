package datastructure.regex;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 包括负数和小数的、带括号的四则运算正则表达式测试
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-26 15:50
 */
public class ArithRegexDemo {
    public static void main(String[] args) {
        String regex1 = "\\([^()]*\\)";
        String regex2 = "(-?([1-9]+\\.?\\d*)|(0\\.\\d*))([-+*/]([1-9]+\\.?\\d*|0\\.\\d*))+";
        System.out.println("创建的pattern是：" + regex2);
        Pattern pattern = Pattern.compile(regex2);
        String target = "(-0.2+12.3)*(2.3-4.5)/2.3";
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
        }
    }

    public static String bracketRegex(String exp) {
        Pattern pattern = Pattern.compile("\\([^()]\\)");
        Matcher matcher = pattern.matcher(exp);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new RuntimeException("语法有误！");
    }

    public static String arithRegex(String exp) {
        Pattern pattern = Pattern.compile("(-?([1-9]+\\.?\\d*)|(0\\.\\d*))([-+*/]([1-9]+\\.?\\d*|0\\.\\d*))+");
        Matcher matcher = pattern.matcher(exp);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new RuntimeException("语法有误！");
    }

}
