import java.util.*;

public class SemanticAnalyzer {
    public static void runSemantic() {
        System.out.println("\n--- PHASE 3: SEMANTIC ANALYSIS ---\n");
        System.out.println("Enter statements (type 'exit' to stop)\n");

        Scanner scanner = new Scanner(System.in);
        Set<String> symbolTable = new HashSet<>();

        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }

            if (line.endsWith(";")) {
                line = line.substring(0, line.length() - 1);
            }

            if (line.startsWith("int ")) {
                String[] parts = line.split("\\s+", 2);
                if (parts.length >= 2) {
                    String[] variables = parts[1].split(",");
                    for (String var : variables) {
                        var = var.trim();
                        if (symbolTable.contains(var)) {
                            System.out.println("ERROR: " + var + " already declared");
                        } else {
                            symbolTable.add(var);
                            System.out.println("Declared: " + var);
                        }
                    }
                } else {
                    System.out.println("Invalid declaration");
                }
            } else if (line.contains("=")) {
                String[] parts = line.split("=", 2);
                String lhs = parts[0].trim();

                if (!symbolTable.contains(lhs)) {
                    System.out.println("ERROR: " + lhs + " not declared");
                } else {
                    System.out.println("OK: " + line);
                }
            } else {
                System.out.println("Invalid statement");
            }
        }

        System.out.println("\nFinal Symbol Table: " + symbolTable);
        scanner.close();
    }

    public static void main(String[] args) {
        runSemantic();
    }
}