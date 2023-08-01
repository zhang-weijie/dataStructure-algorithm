//package datastructure.stack;
//
//import java.security.PublicKey;
//import java.util.Stack;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * 中缀表达式转后缀表达式（逆波兰表达式），
// * 将空格随意的中缀表达式转换为以一个空格均匀隔开的后缀表达式
// * 支持加减乘除四则运算，小括号
// * 支持负数，暂时不考虑小数的情形
// * 例如1+2*3 --> 1 2 3 * +
// * 例如1+2*3/4 --> 1 2 3 * 4 / +
// * 例如1+2-3*4 --> 1 2 + 3 4 * -
// * 例如1+2-3*4/5 -->
// * 例如 (2.5+(-4/(5.1-6.1)*2+2))/2.5 --> 2.5 -4 5.1 6.1 - / 2 * 2 + + 2.5 /
// * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
// * @create 2020-08-29 9:38
// */
//public class NormalToPolandNotation {
//    public static void main(String[] args) {
//
//
//    }
//
//    //创建两个ArrayStack分别用来存放数字和运算符号
//    static ArrayStack2 numStack = new ArrayStack2(20);
//    static ArrayStack2 operStack = new ArrayStack2(20);
//
//    //定义一个方法将不带括号的中缀表达式转为后缀表达式
//    //例如1*2+3-4 --> 1 2 * 3 + 4 -
//    //转换前数栈内由顶到底依次是4,3,2,1
//    //转换前符号栈由顶到底依次是-,+,*
//    //1+2*3/4 --> 1 2 3 * 4 / +
//    //1,2,3,4
//    //
//    public static String transfer(String string){
//
//    }
//
//    //将不带括号的中缀表达式中的数字和符号分别压入栈
//    public static void pushIntoStack(String string){
//        String minusNumExp = "";
//        while ((minusNumExp = minusNumRegex(string)) != null){
//            //考虑到可能会出现-a开头的情形，它们的-符号会干扰判断，因此将其替换为@;
//            if (minusNumExp.charAt(0) == '-'){
//                string = "@" + string.substring(1,string.length() - 1);
//            }
//        }
//        int index = 0;
//        char ch = ' ';
//        String keepNum = "";
//        //分别将数字和运算符按顺序压入栈内，注意此时原运算式中的负数字符已经替换成@a的形式了
//        //在入栈时将其转化为负数;
//        while (true){
//            if (index == string.length()){
//                break;
//            }
//            ch = string.substring(index,index + 1).charAt(0);
//            if (ch == '@') {//如果扫描到的为@;则将后面的一位或多位数扫描完毕并转换为负数
//                keepNum = getMultiBitNum(string,index);
//                //将keepNum中保存的数字转化为负数并入栈，暂时不考虑小数的情形
//                numStack.push(-Integer.parseInt(keepNum));
//                //将index向后移动keepNum.length位
//                index += keepNum.length();
//                //然后将keepNum清空;
//                keepNum = "";
//            }
//            else if ("+-*/".indexOf(ch) != -1){
//                //如果扫描到的字符为运算符,直接入栈
//                //注意不用考虑小括号，因为我们会将小括号里的内容计算出结果再入栈
//                operStack.push(ch);
//            }
//            else {//如果扫描到的为数字，则将后面的一位或多位数扫描完毕;
//                keepNum = getMultiBitNum(string,index);
//                numStack.push(Integer.parseInt(keepNum));
//                index += keepNum.length();
//                keepNum = "";
//            }
//        }
//    }
//
//    //定义一个扫描一位数或多位数的静态方法
//    public static String getMultiBitNum(String string, int index){
//        String numString = "";
//        while (true){
//            String nextChar = string.substring(index + 1, index + 2);
//            if ("+-*/@".contains(nextChar)){
//                break;
//            }
//            numString += nextChar;
//            index++;
//        }
//        return numString;
//    }
//
//    //递归运算
//    public static String recur(String string){
//        if (bracketRegex(string) == null){
//            if (arithmeticRegex(string) == null){
//                throw new RuntimeException("算式有误！");
//            } else {
//                return transfer(string);
//            }
//        } else {
//            String regexExp = bracketRegex(string);
//            String replacement = transfer(regexExp.substring(1,regexExp.length() - 1));
//            String totransfer = string.replace(regexExp,replacement);
//            //在递归调用时更新两个ArrayStack栈的数据;
//
//            return recur(totransfer);
//        }
//    }
//
//    //正则匹配不带嵌套的最内层括号算式并返回
//    public static String bracketRegex(String string){
//        Pattern pattern = Pattern.compile("\\([^()]\\)");
//        Matcher matcher = pattern.matcher(string);
//        if (matcher.find()){
//            return matcher.group();
//        }
//        return null;
//    }
//
//    //正则匹配完整算式并返回
//    public static String arithmeticRegex(String string){
//        Pattern pattern = Pattern.compile("(-?([1-9]+\\.?\\d*)|(0\\.\\d+))([-+*/](([1-9]+\\.?\\d*)|(0\\.\\d+)))*");
//        Matcher matcher = pattern.matcher(string);
//        if (matcher.find()){
//            return matcher.group();
//        }
//        return null;
//    }
//
//    //正则匹配负数并返回
//    public static String minusNumRegex(String string){
//        Pattern pattern = Pattern.compile("(^-([1-9]+\\.?\\d*)|(0\\.\\d+))|(\\(-(([1-9]+\\.?\\d*)|(0\\.\\d+))\\))");
//        Matcher matcher = pattern.matcher(string);
//        if (matcher.find()){
//            return matcher.group();
//        }
//        return null;
//    }
//}
//
//class ArrayStack2 extends ArrayStack{
//
//    public ArrayStack2(int maxSize) {
//        super(maxSize);
//    }
//
//    public int getPriority(int oper){
//        int priority = 0;
//        switch (oper){
//            case '+':
//            case '-':priority = 0;break;
//            case '*':
//            case '/':priority = 1;break;
//            default:priority = -1;
//        }
//        return priority;
//    }
//
//    //查看栈顶，但不弹出
//    public int peek(){
//        return getStack()[getTop()];
//    }
//}