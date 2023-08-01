package datastructure.stack;

import java.util.Stack;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-08-23 5:46
 */
public class StackDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈;stack.add()
        stack.add("Jack");
        stack.add("Tom");
        stack.add("Smith");
        //出栈;stack.pop()
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
