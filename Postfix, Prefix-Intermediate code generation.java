import java.util.*;

public class PrefixPostfixGenerator {
    static int precedence(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return -1;
    }

    static String infixToPostfix(String expr) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isLetterOrDigit(c)) result.append(c);
            else if (c == '(') stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') result.append(stack.pop());
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) result.append(stack.pop());
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) result.append(stack.pop());
        return result.toString();
    }

    static String infixToPrefix(String expr) {
        StringBuilder sb = new StringBuilder(expr).reverse();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') sb.setCharAt(i, ')');
            else if (sb.charAt(i) == ')') sb.setCharAt(i, '(');
        }
        String reversed = sb.toString();
        String postfix = infixToPostfix(reversed);
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        String expr = "(a+b)*c-d";
        String postfix = infixToPostfix(expr);
        String prefix = infixToPrefix(expr);
        System.out.println("Infix: " + expr);
        System.out.println("Postfix: " + postfix);
        System.out.println("Prefix: " + prefix);
    }
}
