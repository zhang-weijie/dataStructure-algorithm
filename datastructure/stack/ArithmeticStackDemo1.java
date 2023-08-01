package datastructure.stack;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-29 8:11
 */
public class ArithmeticStackDemo1 {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        ArrayStack1 numStack = new ArrayStack1(10);
        ArrayStack1 operStack = new ArrayStack1(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//用于拼接多位数;
        while (true) {
            //依次扫描expression的每一个字符;
            ch = expression.substring(index, index + 1).charAt(0);
            //判断是数字还是符号;
            if (operStack.isOper(ch)) {//如果是符号
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //为空则入栈;
                    operStack.push(ch);
                }
            } else {//若是数字则直接入数栈;
                numStack.push(ch - 48);
                //当处理多位数时，不能发现是一个数就立即入栈，因为可能是多位数;
                //在处理数时，需要向expression表达式的Index位再往后一位，
                //因此需要定义一个字符变量，用于拼接
                keepNum += ch;

                //如果ch已经是expression的最后一位，则直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //否则判断下一个字符是不是数字，如果是数字则继续扫描，如果是符号则此处入栈;
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //若后一位是运算符，则入栈;
                        numStack.push(Integer.parseInt(keepNum));
                        //记住此处要清空keepNum;
                        keepNum = "";
                    }
                }
            }
            //让index + 1，并判断是否扫描到expression最后;
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕后，就顺序地从数栈和符号栈中弹出相应的数和符号，并运行;
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //将数栈最后的数弹出，即为结果;
        System.out.printf("表达式%s = %d\n", expression, numStack.pop());
    }
}

//定义一个ArrayStack1栈，扩展一些ArrayStack的功能;
class ArrayStack1 extends ArrayStack {

    public ArrayStack1(int maxSize) {
        super(maxSize);
    }

    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return -1;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符;
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法;
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
        }
        return res;
    }

    //增加一个方法查看栈顶的值;
    public int peek() {
        return getStack()[getTop()];
    }
}