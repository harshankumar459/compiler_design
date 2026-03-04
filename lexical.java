import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer {
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
        "int", "float", "if", "else", "while", "return", "for", "switch", "elif"
    ));
    private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList(
        "+", "-", "*", "/", "=", ">", "<"
    ));
    private static final Set<String> DELIMITERS = new HashSet<>(Arrays.asList(
        ";", ",", "(", ")", "{", "}"
    ));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        System.out.println("Enter the source code (end input with an empty line):");

        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            sb.append(line).append("\n");
        }

        String code = sb.toString();
        System.out.println("\nLEXICAL ANALYSIS OUTPUT:\n");

        Pattern pattern = Pattern.compile("[A-Za-z_]\\w*|\\d+|\\S");
        Matcher matcher = pattern.matcher(code);

        while (matcher.find()) {
            String token = matcher.group();
            String type;

            if (KEYWORDS.contains(token)) {
                type = "KEYWORD";
            } else if (OPERATORS.contains(token)) {
                type = "OPERATOR";
            } else if (DELIMITERS.contains(token)) {
                type = "DELIMITER";
            } else if (token.matches("\\d+")) {
                type = "NUMBER";
            } else {
                type = "IDENTIFIER";
            }
            
            System.out.printf("%-10s → %s\n", token, type);
        }
        scanner.close();
    }
}