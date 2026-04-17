import java.util.*;

public class GrammarTransform {
    static Map<String, List<String>> grammar = new HashMap<>();

    static void addProduction(String lhs, String rhs) {
        grammar.computeIfAbsent(lhs, k -> new ArrayList<>()).add(rhs);
    }

    static void removeLeftRecursion(String nonTerminal) {
        List<String> prods = grammar.get(nonTerminal);
        List<String> alpha = new ArrayList<>();
        List<String> beta = new ArrayList<>();
        for (String p : prods) {
            if (p.startsWith(nonTerminal)) alpha.add(p.substring(nonTerminal.length()));
            else beta.add(p);
        }
        if (!alpha.isEmpty()) {
            String newNT = nonTerminal + "'";
            List<String> newProds = new ArrayList<>();
            for (String b : beta) newProds.add(b + newNT);
            grammar.put(nonTerminal, newProds);
            List<String> alphaProds = new ArrayList<>();
            for (String a : alpha) alphaProds.add(a + newNT);
            alphaProds.add("ε");
            grammar.put(newNT, alphaProds);
        }
    }

    static void leftFactor(String nonTerminal) {
        List<String> prods = grammar.get(nonTerminal);
        Map<String, List<String>> prefixMap = new HashMap<>();
        for (String p : prods) {
            String prefix = p.substring(0,1);
            prefixMap.computeIfAbsent(prefix, k -> new ArrayList<>()).add(p);
        }
        for (Map.Entry<String, List<String>> e : prefixMap.entrySet()) {
            if (e.getValue().size() > 1) {
                String newNT = nonTerminal + "'";
                List<String> newProds = new ArrayList<>();
                for (String p : e.getValue()) {
                    String rest = p.length() > 1 ? p.substring(1) : "ε";
                    newProds.add(rest);
                }
                grammar.put(newNT, newProds);
                grammar.get(nonTerminal).removeAll(e.getValue());
                grammar.get(nonTerminal).add(e.getKey() + newNT);
            }
        }
    }

    static void printGrammar() {
        for (Map.Entry<String, List<String>> e : grammar.entrySet()) {
            System.out.println(e.getKey() + " -> " + String.join(" | ", e.getValue()));
        }
    }

    public static void main(String[] args) {
        addProduction("E", "E+T");
        addProduction("E", "T");
        addProduction("T", "T*F");
        addProduction("T", "F");
        addProduction("F", "(E)");
        addProduction("F", "id");

        System.out.println("Original Grammar:");
        printGrammar();

        removeLeftRecursion("E");
        removeLeftRecursion("T");

        System.out.println("\nAfter Removing Left Recursion:");
        printGrammar();

        leftFactor("E");
        leftFactor("T");

        System.out.println("\nAfter Left Factoring:");
        printGrammar();
    }
}
