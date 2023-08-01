package datastructure.stack;

import javax.sound.midi.Soundbank;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-10-14 15:30
 */
public class NormalToPolandFinal {
    public static void main(String[] args) {
        String string = "1+2*(3+4)-5";//结果应为：1 2 3 4 + * + 5 -
        transfer(string);
    }

    static ArrayStack0 s1 = new ArrayStack0(10);
    static ArrayStack0 s2 = new ArrayStack0(10);

    //定义从中缀转后缀的静态方法
    public static void transfer(String string) {
        int index = 0;
        char ch = ' ';
        while (index < string.length()) {
            ch = string.substring(index, index + 1).charAt(0);
            index++;
            if ("+-*/".indexOf(ch) != -1) {
                arrangeOper(ch, s1, s2);
            } else if (ch == '(') {
                s1.push(ch);
            } else if (ch == ')') {
                while (true) {
                    int s1Top = s1.pop();
                    if (s1Top == '(') {
                        break;
                    }
                    s2.push(s1Top);
                }
            } else {//扫描到数字的情形
                s2.push(ch);
            }
        }
        //扫描完毕后将s1中剩余的运算符转移到s2中
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        //逆序输出s2中的字符
        String result = "";
        while (!s2.isEmpty()) {
            char temp = (char) s2.pop();
            result += temp;
        }
        for (int i = result.length(); i > 0; i--) {
            System.out.print(result.substring(i - 1, i) + " ");
        }
        System.out.println();
    }


    //定义一个静态递归方法用于进行运算符之间的比较
    public static void arrangeOper(char ch, ArrayStack0 s1, ArrayStack0 s2) {
        if (s1.isEmpty() || s1.peek() == '(') {
            s1.push(ch);
        } else if (s1.getPriority(ch) > s1.getPriority(s1.peek())) {
            s1.push(ch);
        } else {
            s2.push(s1.pop());
            arrangeOper(ch, s1, s2);
        }
    }
}

class ArrayStack0 extends ArrayStack {
    public ArrayStack0(int maxSize) {
        super(maxSize);
    }

    public int getPriority(char oper) {
        int priority = 0;
        switch (oper) {
            case '+':
            case '-':
                priority = 0;
                break;
            case '*':
            case '/':
                priority = 1;
                break;
            default:
                priority = -1;
        }
        return priority;
    }

    //查看栈顶，但不弹出
    public char peek() {
        return (char) getStack()[getTop()];
    }
}
