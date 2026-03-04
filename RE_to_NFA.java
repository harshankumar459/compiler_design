import java.util.*;

class Transition {
    int from, to;
    char symbol;

    Transition(int from, char symbol, int to) {
        this.from = from;
        this.symbol = symbol;
        this.to = to;
    }
}

class NFA {
    int start;
    int accept;
    List<Transition> transitions;

    NFA(int start, int accept, List<Transition> transitions) {
        this.start = start;
        this.accept = accept;
        this.transitions = transitions;
    }
}

public class RegExToNFA {
    public static NFA reToNfa(String regex) {
        int state = 0;
        List<Transition> transitions = new ArrayList<>();

        int startState = state++;
        int currentState = startState;

        for (char symbol : regex.toCharArray()) {
            int nextState = state++;
            transitions.add(new Transition(currentState, symbol, nextState));
            currentState = nextState;
        }

        return new NFA(0, currentState, transitions);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Regular Expression: ");
        String regex = scanner.nextLine();

        NFA nfa = reToNfa(regex);

        System.out.println("\nNFA Representation");
        System.out.println("Start State: " + nfa.start);
        System.out.println("Accept State: " + nfa.accept);
        System.out.println("\nTransitions:");
        for (Transition t : nfa.transitions) {
            System.out.println(t.from + " -- " + t.symbol + " --> " + t.to);
        }
        scanner.close();
    }
}