import java.util.*;

class Production {
    String lhs;
    List<String> rhs;
    Production(String lhs, List<String> rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}

class Item {
    Production production;
    int dot;
    Item(Production production, int dot) {
        this.production = production;
        this.dot = dot;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder(production.lhs + " -> ");
        for (int i = 0; i < production.rhs.size(); i++) {
            if (i == dot) sb.append("• ");
            sb.append(production.rhs.get(i)).append(" ");
        }
        if (dot == production.rhs.size()) sb.append("•");
        return sb.toString().trim();
    }
}

public class LR0 {
    static List<Production> grammar = new ArrayList<>();
    static Set<Item> closure(Set<Item> items) {
        Set<Item> result = new HashSet<>(items);
        boolean changed;
        do {
            changed = false;
            Set<Item> newItems = new HashSet<>();
            for (Item item : result) {
                if (item.dot < item.production.rhs.size()) {
                    String symbol = item.production.rhs.get(item.dot);
                    for (Production p : grammar) {
                        if (p.lhs.equals(symbol)) {
                            Item newItem = new Item(p, 0);
                            if (!result.contains(newItem)) {
                                newItems.add(newItem);
                                changed = true;
                            }
                        }
                    }
                }
            }
            result.addAll(newItems);
        } while (changed);
        return result;
    }
    static Set<Item> gotoSet(Set<Item> items, String symbol) {
        Set<Item> moved = new HashSet<>();
        for (Item item : items) {
            if (item.dot < item.production.rhs.size() && item.production.rhs.get(item.dot).equals(symbol)) {
                moved.add(new Item(item.production, item.dot + 1));
            }
        }
        return closure(moved);
    }
    public static void main(String[] args) {
        grammar.add(new Production("S'", Arrays.asList("S")));
        grammar.add(new Production("S", Arrays.asList("A", "B")));
        grammar.add(new Production("A", Arrays.asList("a")));
        grammar.add(new Production("B", Arrays.asList("b")));
        Set<Item> start = new HashSet<>();
        start.add(new Item(grammar.get(0), 0));
        Set<Item> c = closure(start);
        System.out.println("Closure of start:");
        for (Item i : c) System.out.println(i);
        Set<Item> g = gotoSet(c, "S");
        System.out.println("\nGoto on S:");
        for (Item i : g) System.out.println(i);
    }
}
