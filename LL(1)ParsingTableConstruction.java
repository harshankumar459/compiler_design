import java.util.*;

public class LL1ParsingTable {
    public static void main(String[] args) {
        List<String> nonTerminals = Arrays.asList("E", "R", "T", "Y", "F");
        List<String> terminals = Arrays.asList("+", "*", "(", ")", "i", "$");

        Map<String, Map<String, String>> table = new HashMap<>();

        table.put("E", new HashMap<>());
        table.get("E").put("(", "E → TR");
        table.get("E").put("i", "E → TR");

        table.put("R", new HashMap<>());
        table.get("R").put("+", "R → +TR");
        table.get("R").put(")", "R → ε");
        table.get("R").put("$", "R → ε");

        table.put("T", new HashMap<>());
        table.get("T").put("(", "T → FY");
        table.get("T").put("i", "T → FY");

        table.put("Y", new HashMap<>());
        table.get("Y").put("*", "Y → *FY");
        table.get("Y").put("+", "Y → ε");
        table.get("Y").put(")", "Y → ε");
        table.get("Y").put("$", "Y → ε");

        table.put("F", new HashMap<>());
        table.get("F").put("(", "F → (E)");
        table.get("F").put("i", "F → i");

        System.out.println("LL(1) Parsing Table:\n");
        for (String nt : nonTerminals) {
            for (String t : terminals) {
                if (table.containsKey(nt) && table.get(nt).containsKey(t)) {
                    System.out.println("M[" + nt + ", " + t + "] = " + table.get(nt).get(t));
                }
            }
        }
    }
}
