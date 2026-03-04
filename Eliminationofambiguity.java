import java.util.*;

public class GrammarTransformations {

    public static void main(String[] args) {
        String nt = "A";
        List<String> productions = Arrays.asList("Aa", "Ab", "c");

        System.out.println("Original Productions: " + productions);

        System.out.println("\nAfter Eliminating Left Recursion:");
        eliminateLeftRecursion(nt, productions);

        System.out.println("\nAfter Left Factoring:");
        leftFactoring("A", Arrays.asList("ab", "ac"));
    }

    public static void eliminateLeftRecursion(String nt, List<String> productions) {
        List<String> alpha = new ArrayList<>();
        List<String> beta = new ArrayList<>();

        for (String prod : productions) {
            if (prod.startsWith(nt)) {
                alpha.add(prod.substring(nt.length()));
            } else {
                beta.add(prod);
            }
        }

        if (alpha.isEmpty()) {
            System.out.println(productions);
            return;
        }

        String newNt = nt + "'";
        List<String> newProductions = new ArrayList<>();
        for (String b : beta) {
            newProductions.add(b + newNt);
        }

        List<String> newAlpha = new ArrayList<>();
        for (String a : alpha) {
            newAlpha.add(a + newNt);
        }
        newAlpha.add("ε");

        System.out.println(nt + " -> " + newProductions);
        System.out.println(newNt + " -> " + newAlpha);
    }

    public static void leftFactoring(String nt, List<String> productions) {
        if (productions.isEmpty()) return;

        String prefix = productions.get(0);
        for (int i = 1; i < productions.size(); i++) {
            String prod = productions.get(i);
            int j = 0;
            while (j < Math.min(prefix.length(), prod.length()) && prefix.charAt(j) == prod.charAt(j)) {
                j++;
            }
            prefix = prefix.substring