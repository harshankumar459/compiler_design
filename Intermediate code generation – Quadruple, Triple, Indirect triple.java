import java.util.*;

class Quadruple {
    String op, arg1, arg2, result;
    Quadruple(String op, String arg1, String arg2, String result) {
        this.op = op; this.arg1 = arg1; this.arg2 = arg2; this.result = result;
    }
    public String toString() { return "(" + op + ", " + arg1 + ", " + arg2 + ", " + result + ")"; }
}

class Triple {
    String op, arg1, arg2;
    Triple(String op, String arg1, String arg2) {
        this.op = op; this.arg1 = arg1; this.arg2 = arg2;
    }
    public String toString() { return "(" + op + ", " + arg1 + ", " + arg2 + ")"; }
}

class IndirectTriple {
    List<Integer> pointers = new ArrayList<>();
    List<Triple> triples;
    IndirectTriple(List<Triple> triples) {
        this.triples = triples;
        for (int i = 0; i < triples.size(); i++) pointers.add(i);
    }
    public void print() {
        for (int i = 0; i < pointers.size(); i++) {
            System.out.println("Pointer " + i + " -> " + triples.get(pointers.get(i)));
        }
    }
}

public class IntermediateCode {
    public static void main(String[] args) {
        List<Quadruple> quads = new ArrayList<>();
        quads.add(new Quadruple("+", "a", "b", "t1"));
        quads.add(new Quadruple("*", "t1", "c", "t2"));
        quads.add(new Quadruple("-", "t2", "d", "t3"));
        System.out.println("Quadruples:");
        for (Quadruple q : quads) System.out.println(q);

        List<Triple> triples = new ArrayList<>();
        triples.add(new Triple("+", "a", "b"));
        triples.add(new Triple("*", "(0)", "c"));
        triples.add(new Triple("-", "(1)", "d"));
        System.out.println("\nTriples:");
        for (int i = 0; i < triples.size(); i++) System.out.println(i + ": " + triples.get(i));

        IndirectTriple indirect = new IndirectTriple(triples);
        System.out.println("\nIndirect Triples:");
        indirect.print();
    }
}
