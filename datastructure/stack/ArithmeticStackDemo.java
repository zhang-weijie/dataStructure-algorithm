package datastructure.stack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * //+、-、*、/、（）四则运算的栈实现（包括负数和小数）
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-25 17:08
 */
public class ArithmeticStackDemo {
    public static void main(String[] args) {
        String string = "(-4.1/(5.2-6.3)*2.4+2.5)/2.6";
        try {
            System.out.println(recursiveCalculate(string));
        } catch (Exception e) {
            System.out.println("算式有误！");
        }
    }

    public static String recursiveCalculate(String string) {
        if (bracketRegex(string) == null) {
            if (arithRegex(string) != null) {
                return calculate(string);
                //考虑到在递归过程最后可能会传入单独一个数字如4.0作为string，因此需要单独判断;
            } else if (!(string.contains("+") || string.contains("-") || string.contains("*") || string.contains("/"))) {
                return string;
            }
            throw new RuntimeException();
        } else {
            String bracketRegexExp = bracketRegex(string);
            String replacement = calculate(bracketRegexExp.substring(1, bracketRegexExp.length() - 1));
            String result = string.replace(bracketRegexExp, replacement);
            return recursiveCalculate(result);
        }
    }

    public static NumStack numStack = new NumStack();
    public static CharStack charStack = new CharStack();

    public static String calculate(String string) {
        char key = string.charAt(0);
        //判断首位是否为-;
        if (key == '-') {
            //是则在算式首添上0;
            string = "0" + string;
        }
        //添加一个$标记结尾，防止算式的最后一个数被遗漏;
        string += '$';
        String subString = "";
        for (int i = 0; i < string.length(); i++) {
            key = string.charAt(i);
            if ((key > 47 && key < 58) || key == '.') {
                subString += key;
                continue;
            }

            if (subString != "") {
                //遍历完毕后将subString转换为double型，分整数和小数两种情况;
                double num = stringToLong(subString);
                //将获取到的num压入数栈;
                numStack.push(num);
                //将subString清空;
                subString = "";
            }

            if (key != '$') {
                if ("+-*/".indexOf(key) != -1) {
                    if (!charStack.isEmpty()) {
                        ArithChar topChar = charStack.pop();
                        if (topChar.getPriority() >= getPriority(key)) {
                            double num1 = numStack.pop();
                            double num2 = 0;
                            double num3 = 0;
                            //考虑出现a--b/a+-b/a*-b/a/-b的情形;
                            //此时数栈和符号栈单位数目一致;
                            if (numStack.getSize() != charStack.getSize()) {
                                num2 = numStack.pop();
                                num3 = simplyCalculate(num1, num2, topChar.getArithChar());
                            } else {
                                switch (topChar.getArithChar()) {
                                    case '+':
                                        break;
                                    case '-':
                                        key = '+';
                                        break;
                                    case '*':
                                        num2 = -1;
                                        key = '*';
                                        break;
                                    case '/':
                                        num2 = -1;
                                        key = '/';
                                }
                                //注意此处num1和num2的顺序与上面不同;
                                num3 = simplyCalculate(num2, num1, topChar.getArithChar());
                            }
                            numStack.push(num3);
                        } else {
                            charStack.push(topChar);
                        }
                        //考虑到可能会出现a-b*c+d转换为a-e+d的情形，因此需要再做一次运算;
                        if (!charStack.isEmpty()) {
                            ArithChar nextArithChar = charStack.pop();
                            if (nextArithChar.getPriority() >= getPriority(key)) {
                                double num4 = numStack.pop();
                                double num5 = numStack.pop();
                                double num6 = simplyCalculate(num4, num5, nextArithChar.getArithChar());
                                numStack.push(num6);
                            } else {
                                charStack.push(nextArithChar);
                            }
                        }
                    }
                    charStack.push(new ArithChar(key, getPriority(key)));
                } else {
                    //另一种情形是算式中出现了残缺的括号或其他符号如1+2)*3+@4等
                    throw new RuntimeException();
                }
            }
        }
        return "" + getStackResult();
    }

    //算式遍历完毕后，数栈中剩余2n(n>=1)数字，符号栈中剩余n个符号;
    public static double getStackResult() {
        while (!charStack.isEmpty()) {
            double num1 = numStack.pop();
            double num2 = numStack.pop();
            double num3 = simplyCalculate(num1, num2, charStack.pop().getArithChar());
            numStack.push(num3);
        }
        return numStack.pop();
    }

    public static double stringToLong(String subString) {
        //判断string是否为负数;
        double bias = 1;
        if (subString.charAt(0) == '-') {
            bias = -1;
            subString = subString.substring(1);
        }

        double num = 0;
        if (subString.indexOf('.') != -1) {
            String[] strings = subString.split("\\.");
            for (int j = 0; j < strings[0].length(); j++) {
                num += (strings[0].charAt(j) - 48) * Math.pow(10, strings[0].length() - j - 1);
            }
            for (int j = 0; j < strings[1].length(); j++) {
                num += (strings[1].charAt(j) - 48) * Math.pow(10, -(j + 1));
            }
        } else {
            for (int j = 0; j < subString.length(); j++) {
                num += (subString.charAt(j) - 48) * Math.pow(10, subString.length() - j - 1);
            }
        }
        return num * bias;
    }

    public static int getPriority(char arithChar) {
        int priority = 0;
        switch (arithChar) {
            case '+':
            case '-':
                priority = 1;
                break;
            case '*':
            case '/':
                priority = 2;
                break;
            default:
        }
        return priority;
    }

    public static double simplyCalculate(double num1, double num2, char arithChar) {
        double result = 0;
        switch (arithChar) {
            //注意栈内数字的运算顺序;
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
        }
        return result;
    }


    public static String bracketRegex(String exp) {
        Pattern pattern = Pattern.compile("\\([^()]*\\)");
        Matcher matcher = pattern.matcher(exp);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static String arithRegex(String exp) {
        Pattern pattern = Pattern.compile("(-?([1-9]+\\.?\\d*)|(0\\.\\d*))([-+*/]([1-9]+\\.?\\d*|0\\.\\d*))+");
        Matcher matcher = pattern.matcher(exp);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}


class NumStack {
    private double[] numStack = new double[20];
    private int top = -1;

    public int getSize() {
        return top + 1;
    }

    public boolean isFull() {
        return top == 19;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(double value) {
        if (isFull()) {
            throw new RuntimeException("数栈满！");
        }
        top++;
        numStack[top] = value;
    }

    public double pop() {
        if (isEmpty()) {
            throw new RuntimeException("数栈空！");
        }
        double value = numStack[top];
        top--;
        return value;
    }
}

class CharStack {
    private ArithChar head = new ArithChar(' ', 0);

    public int getSize() {
        int size = 0;
        ArithChar cur = head;
        while (cur.getNext() != null) {
            size++;
            cur = cur.getNext();
        }
        return size;
    }

    public boolean isEmpty() {
        return head.getNext() == null;
    }

    public void push(ArithChar arithChar) {
        if (isEmpty()) {
            head.setNext(arithChar);
            return;
        }
        ArithChar next = head.getNext();
        head.setNext(arithChar);
        arithChar.setNext(next);
    }

    public ArithChar pop() {
        if (isEmpty()) {
            throw new RuntimeException("符号栈空！");
        }
        if (head.getNext().getNext() == null) {
            ArithChar target = head.getNext();
            head.setNext(null);
            return target;
        }
        ArithChar target = head.getNext();
        ArithChar next = target.getNext();
        head.setNext(next);
        return target;
    }
}

class ArithChar {
    private char arithChar;
    private int priority;
    private ArithChar next;

    public ArithChar(char arithChar, int priority) {
        this.arithChar = arithChar;
        this.priority = priority;
    }

    public char getArithChar() {
        return arithChar;
    }

    public void setArithChar(char arithChar) {
        this.arithChar = arithChar;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ArithChar getNext() {
        return next;
    }

    public void setNext(ArithChar next) {
        this.next = next;
    }

}