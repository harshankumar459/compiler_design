import java.util.Scanner;
import java.util.regex.*;

public class SyntaxAnalyzer {
    public static void analyze(String statement) {
        statement = statement.trim();
        String pattern = "^[a-zA-Z_]\\w*\\s*=\\s*([a-zA-Z_]\\w*|\\d+)(\\s*[+\\-*/]\\s*([a-zA-Z_]\\w*|\\d+))?$";
        if (Pattern.matches(pattern, statement)) {
            System.out.println("VALID SYNTAX   : " + statement);
        } else {
            System.out.println("INVALID SYNTAX : " + statement);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SYNTAX ANALYSIS RESULT:\n");
        System.out.println("Type 'exit' to stop.\n");
        while (true) {
            System.out.print("Enter a statement: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Program terminated.");
                break;
            }
            if (!input.trim().isEmpty()) {
                analyze(input);
            }
        }
        scanner.close();
    }
}