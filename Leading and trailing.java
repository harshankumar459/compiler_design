import java.util.*;

public class LeadingTrailing {
    static Map<String, List<String>> grammar = new HashMap<>();
    static Set<String> terminals = new HashSet<>();

    static void addProduction(String lhs, String rhs) {
        grammar.computeIfAbsent(lhs, k -> new ArrayList<>()).add(rhs);
    }

    static Set<String> leading(String nonTerminal) {
        Set<String> result = new HashSet<>();
        for (String prod : grammar.get(nonTerminal)) {
            String first = prod.substring(0,1);
            if (terminals.contains(first)) result.add(first);
            else result.addAll(leading(first));
        }
        return result;
    }

    static Set<String> trailing(String nonTerminal) {
        Set<String> result = new HashSet<>();
        for (String prod : grammar.get(nonTerminal)) {
            String last = prod.substring(prod.length()-1);
            if (terminals.contains(last)) result.add(last);
            else result.addAll(trailing(last));
        }
        return result;
    }

    public static void main(String[] args) {
        terminals.add("a"); terminals.add("b"); terminals.add("c"); terminals.add("+"); terminals.add("*");

        addProduction("E", "TA");
        addProduction("A", "+TA");
        addProduction("A", "ε");
        addProduction("T", "FB");
        addProduction("B", "*FB");
        addProduction("B", "ε");
        addProduction("F", "a");
        addProduction("F", "b");
        addProduction("F", "(E)");

        System.out.println("LEADING(E): " + leading("E"));
        System.out.println("TRAILING(E): " + trailing("E"));
        System.out.println("LEADING(T): " + leading("T"));
        System.out.println("TRAILING(T): " + trailing("T"));
    }
}
