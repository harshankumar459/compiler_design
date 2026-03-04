import java.util.*;

public class NfaToDfa {
    public static void main(String[] args) {
        Map<String, Map<Integer, Set<Integer>>> nfa = new HashMap<>();
        
        Map<Integer, Set<Integer>> transA = new HashMap<>();
        transA.put(0, new HashSet<>(Arrays.asList(0, 1)));
        transA.put(1, new HashSet<>(Collections.singletonList(2)));
        nfa.put("a", transA);

        Map<Integer, Set<Integer>> transB = new HashMap<>();
        transB.put(0, new HashSet<>(Collections.singletonList(0)));
        transB.put(1, new HashSet<>(Collections.singletonList(1)));
        transB.put(2, new HashSet<>(Collections.singletonList(2)));
        nfa.put("b", transB);

        int startState = 0;
        Set<Integer> nfaFinalStates = new HashSet<>(Collections.singletonList(2));

        convert(nfa, startState, nfaFinalStates);
    }

    public static void convert(Map<String, Map<Integer, Set<Integer>>> nfa, int start, Set<Integer> nfaFinals) {
        Map<Set<Integer>, Map<String, Set<Integer>>> dfa = new HashMap<>();
        Queue<Set<Integer>> unmarkedStates = new LinkedList<>();
        List<Set<Integer>> dfaStates = new ArrayList<>();

        Set<Integer> startSet = new HashSet<>(Collections.singletonList(start));
        unmarkedStates.add(startSet);
        dfaStates.add(startSet);

        while (!unmarkedStates.isEmpty()) {
            Set<Integer> current = unmarkedStates.poll();
            dfa.put(current, new HashMap<>());

            for (String symbol : nfa.keySet()) {
                Set<Integer> nextState = new HashSet<>();
                for (int state : current) {
                    if (nfa.get(symbol).containsKey(state)) {
                        nextState.addAll(nfa.get(symbol).get(state));
                    }
                }

                if (!nextState.isEmpty()) {
                    dfa.get(current).put(symbol, nextState);

                    if (!dfaStates.contains(nextState)) {
                        dfaStates.add(nextState);
                        unmarkedStates.add(nextState);
                    }
                }
            }
        }

        System.out.println("DFA States:");
        for (Set<Integer> state : dfaStates) {
            System.out.println(state);
        }

        System.out.println("\nDFA Transitions:");
        for (Map.Entry<Set<Integer>, Map<String, Set<Integer>>> entry : dfa.entrySet()) {
            for (Map.Entry<String, Set<Integer>> trans : entry.getValue().entrySet()) {
                System.out.println(entry.getKey() + " --" + trans.getKey() + "--> " + trans.getValue());
            }
        }

        System.out.println("\nDFA Final States:");
        for (Set<Integer> state : dfaStates) {