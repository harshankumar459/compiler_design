import java.util.Scanner;

public class CodeOptimizer {
    public static void runOptimization() {
        System.out.println("\n--- PHASE 5: CODE OPTIMIZATION ---\n");
        System.out.println("Enter statements (type 'exit' to stop)\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }

            // Remove semicolon for processing
            if (line.endsWith(";")) {
                line = line.substring(0, line.length() - 1);
            }

            // 1. Constant Folding (e.g., x = 5 + 10 -> x = 15)
            if (line.contains("=") && line.contains("+")) {
                String[] equation = line.split("=", 2);
                String lhs = equation[0].trim();
                String rhs = equation[1].trim();

                String[] parts = rhs.split("\\+");
                if (parts.length == 2) {
                    String a = parts[0].trim();
                    String b = parts[1].trim();

                    if (isNumeric(a) && isNumeric(b)) {
                        int result = Integer.parseInt(a) + Integer.parseInt(b);
                        System.out.println(lhs + " = " + result);
                        continue;
                    }
                }
            }

            // 2. Redundant Assignment Removal (e.g., x = x)
            if (line.contains("=")) {
                String[] parts = line.split("=", 2);
                String lhs = parts[0].trim();
                String rhs = parts[1].trim();

                if (lhs.equals(rhs)) {
                    System.out.println("Removed redundant assignment: " + line);
                    continue;
                }
            }

            // Default: Print line as is
            System.out.println(line);
        }

        System.out.println("\nOptimization Finished.");
        scanner.close();
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    public static void main(String[] args) {
        runOptimization();
    }
}