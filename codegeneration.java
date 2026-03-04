import java.util.*;
import java.util.regex.*;

public class CodeGenerator {
    private Stack<String> operatorStack = new Stack<>();
    private Stack<String> operandStack = new Stack<>();
    private int tempCount = 0;
    private static final Map<String, Integer> PRECEDENCE;

    static {
        PRECEDENCE = new HashMap<>();
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
        PRECEDENCE.put("^", 3);
    }

    private String newTemp() {
        tempCount++;
        return "t" + tempCount;
    }

    private String generateInstruction(String op, String arg1, String arg2) {
        String tempVar = newTemp();
        System.out.println(tempVar + " = " + arg1 + " " + op + " " + arg2);
        return tempVar;
    }

    private void processOperator() {
        if (operandStack.size() < 2) return;
        String op = operatorStack.pop();
        String right = operandStack.pop();
        String left = operandStack.pop();
        String resultTemp = generateInstruction(op, left, right);
        operandStack.push(resultTemp);
    }

    public void parseAndGenerate(String expression) {
        System.out.println("\n--- Generating Code for: " + expression + " ---");
        System.out.println("Generated Instructions:");

        String lhs = null;
        if (expression.contains("=")) {
            String[] parts = expression.split("=", 2);
            lhs = parts[0].trim();
            expression = parts[1].trim();
        }

        // Regex to tokenize numbers, identifiers, operators, and parentheses
        Pattern pattern = Pattern.compile("\\d+|[a-zA-Z_]\\w*|[-+*/^()]");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String token = matcher.group();

            if (token.matches("\\w+")) {
                operandStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    processOperator();
                }
                operatorStack.pop(); 
            } else if (PRECEDENCE.containsKey(token)) {
                while (!operatorStack.isEmpty() && 
                       PRECEDENCE.containsKey(operatorStack.peek()) && 
                       PRECEDENCE.get(operatorStack.peek()) >= PRECEDENCE.get(token)) {
                    processOperator();
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator();
        }

        if (lhs != null && !operandStack.isEmpty()) {
            String finalResult = operandStack.pop();
            System.out.println(lhs + " = " + finalResult);
        }
        System.out.println("---------------------------------------");
        
        // Reset for next input
        tempCount = 0;
        operatorStack.clear();
        operandStack.clear();
    }

    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator();
        Scanner sc = new Scanner(System.in);

        System.out.println("Compiler Design: Simple Code Generator");
        System.out.println("Enter expressions like: x = a + b * (c - d)");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            System.out.print("Enter expression: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            try {
                generator.parseAndGenerate(input);
            } catch (Exception e) {
                System.out.println("Error parsing expression: " + e.getMessage());
            }
        }
        sc.close();
    }
}