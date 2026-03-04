import java.util.Scanner;

public class IntermediateCodeGenerator {
    public static void runIntermediate() {
        System.out.println("\n--- PHASE 4: INTERMEDIATE CODE GENERATION ---\n");
        System.out.println("Enter statements (type 'exit' to stop)\n");

        Scanner scanner = new Scanner(System.in);
        int tempCount = 1;

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

            if (line.contains("=") && line.contains("+")) {
                String[] parts = line.split("=", 2);
                String lhs = parts[0].trim();
                String rhs = parts[1].trim();

                String[] operands = rhs.split("\\+", 2);
                String a = operands[0].trim();
                String b = operands[1].trim();

                System.out.println("t" + tempCount + " = " + a + " + " + b);
                System.out.println(lhs + " = t" + tempCount);
                tempCount++;
            } else if (line.contains("=")) {
                System.out.println(line);
            } else {
                System.out.println("Invalid statement");
            }
        }

        System.out.println("\nIntermediate Code Generation Finished.");
        scanner.close();
    }

    public static void main(String[] args) {
        runIntermediate();
    }
}