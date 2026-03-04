import java.util.*;

public class FirstFollow {
    static Map<String, List<String>> grammar = new LinkedHashMap<>();
    static List<String> nonTerminals;
    static List<String> terminals = Arrays.asList("+", "*", "(", ")", "i");
    static String startSymbol = "E";
    static Map<String, Set<String>> FIRST = new HashMap<>();
    static Map<String, Set<String>> FOLLOW = new HashMap<>();

    public static void main(String[] args) {
        grammar.put("E", Arrays.asList("TR"));
        grammar.put("R", Arrays.asList("+TR", "ε"));
        grammar.put("T", Arrays.asList("FY"));
        grammar.put("Y", Arrays.asList("*FY", "ε"));
        grammar.put("F", Arrays.asList("(E)", "i"));

        nonTerminals = new ArrayList<>(grammar.keySet());

        for (String nt : nonTerminals) {
            FIRST.put(nt, new HashSet<>());
            FOLLOW.put(nt, new HashSet<>());
        }

        for (String nt : nonTerminals) {
            computeFirst(nt);
        }

        computeFollow();

        System.out.println("FIRST Sets:");
        for (String nt : nonTerminals) {
            System.out.println("FIRST(" + nt + ") = " + FIRST.get(nt));
        }

        System.out.println("\nFOLLOW Sets:");
        for (String nt : nonTerminals) {
            System.out.println("FOLLOW(" + nt + ") = " + FOLLOW.get(nt));
        }
    }

    static Set<String> computeFirst(String symbol) {
        if (terminals.contains(symbol)) {
            return new HashSet<>(Collections.singletonList(symbol));
        }
        if (symbol.equals("ε")) {
            return new HashSet<>(Collections.singletonList("ε"));
        }

        Set<String> resultSet = FIRST.get(symbol);
        for (String production : grammar.get(symbol)) {
            boolean broke = false;
            for (int i = 0; i < production.length(); i++) {
                String charAt = String.valueOf(production.charAt(i));
                Set<String> charFirst = computeFirst(charAt);
                
                Set<String> toAdd = new HashSet<>(charFirst);
                toAdd.remove("ε");
                resultSet.addAll(toAdd);

                if (!charFirst.contains("ε")) {
                    broke = true;
                    break;
                }
            }
            if (!broke) {
                resultSet.add("ε");
            }
        }
        return resultSet;
    }

    static void computeFollow() {
        FOLLOW.get(startSymbol).add("$");

        boolean changed = true;
        while (changed) {
            changed = false;
            for (Map.Entry<String, List<String>> entry : grammar.entrySet()) {
                String head = entry.getKey();
                for (String prod : entry.getValue()) {
                    for (int i = 0; i < prod.length(); i++) {
                        String symbol = String.valueOf(prod.charAt(i));
                        if (nonTerminals.contains(symbol)) {
                            Set<String> symbolFollow = FOLLOW.get(symbol);
                            int before = symbolFollow.size();

                            if (i + 1 < prod.length()) {
                                Set<String> firstNext = new HashSet<>();
                                boolean allDeriveEpsilon = true;
                                
                                for (int j = i + 1; j < prod.length(); j++) {
                                    String nextSym = String.valueOf(prod.charAt(j));
                                    Set<String> nextFirst = computeFirst(nextSym);
                                    
                                    Set<String> toAdd = new HashSet<>(nextFirst);
                                    toAdd.remove("ε");
                                    firstNext.addAll(toAdd);
                                    
                                    if (!nextFirst.contains("ε")) {
                                        allDeriveEpsilon = false;
                                        break;
                                    }
                                }
                                
                                symbolFollow.addAll(firstNext);
                                if (allDeriveEpsilon) {
                                    symbolFollow.addAll(FOLLOW.get(head));
                                }
                            } else {
                                symbolFollow.addAll(FOLLOW.get(head));
                            }

                            if (symbolFollow.size() > before) {
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
    }
}