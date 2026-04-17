import java.util.*;

public class LL1Parser {
    static Map<String, Map<String, String>> parsingTable = new HashMap<>();
    static Stack<String> stack = new Stack<>();

    static void initTable() {
        parsingTable.put("E", new HashMap<>());
        parsingTable.get("E").put("id", "T E'");
        parsingTable.get("E").put("(", "T E'");

        parsingTable.put("E'", new HashMap<>());
        parsingTable.get("E'").put("+", "+ T E'");
        parsingTable.get("E'").put(")", "ε");
        parsingTable.get("E'").put("$", "ε");

        parsingTable.put("T", new HashMap<>());
        parsingTable.get("T").put("id", "F T'");
        parsingTable.get("T").put("(", "F T'");

        parsingTable.put("T'", new HashMap<>());
        parsingTable.get("T'").put("*", "* F T'");
        parsingTable.get("T'").put("+", "ε");
        parsingTable.get("T'").put(")", "ε");
        parsingTable.get("T'").put("$", "ε");

        parsingTable.put("F", new HashMap<>());
        parsingTable.get("F").put("id", "id");
        parsingTable.get("F").put("(", "( E )");
    }

    static void parse(List<String> input) {
        stack.push("$");
        stack.push("E");
        int index = 0;
        while (!stack.isEmpty()) {
            String top = stack.peek();
            String current = input.get(index);
            if (top.equals(current)) {
                stack.pop();
                index++;
            } else if (!parsingTable.containsKey(top)) {
                System.out.println("Error");
                return;
            } else {
                String production = parsingTable.get(top).get(current);
                if (production == null) {
                    System.out.println("Error");
                    return;
                }
                stack.pop();
                if (!production.equals("ε")) {
                    List<String> symbols = Arrays.asList(production.split(" "));
                    Collections.reverse(symbols);
                    for (String s : symbols) stack.push(s);
                }
            }
        }
        if (index == input.size()) System.out.println("Parsing successful");
        else System.out.println("Error");
    }

    public static void main(String[] args) {
        initTable();
        List<String> input = Arrays.asList("id", "+", "id", "*", "id", "$");
        parse(input);
    }
}
