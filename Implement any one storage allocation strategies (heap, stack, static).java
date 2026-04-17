import java.util.*;

class StackFrame {
    String functionName;
    Map<String, Integer> localVariables;

    StackFrame(String functionName) {
        this.functionName = functionName;
        this.localVariables = new HashMap<>();
    }

    void addVariable(String name, int value) {
        localVariables.put(name, value);
    }

    public String toString() {
        return "Function: " + functionName + ", Locals: " + localVariables;
    }
}

public class StackAllocationDemo {
    static Stack<StackFrame> callStack = new Stack<>();

    static void callFunction(String name) {
        StackFrame frame = new StackFrame(name);
        callStack.push(frame);
        System.out.println("Entered " + name + " -> Stack: " + callStack);
    }

    static void addLocal(String var, int value) {
        StackFrame current = callStack.peek();
        current.addVariable(var, value);
        System.out.println("Added local " + var + "=" + value + " -> Stack: " + callStack);
    }

    static void returnFunction() {
        StackFrame frame = callStack.pop();
        System.out.println("Returned from " + frame.functionName + " -> Stack: " + callStack);
    }

    public static void main(String[] args) {
        callFunction("main");
        addLocal("x", 10);
        callFunction("foo");
        addLocal("y", 20);
        returnFunction();
        addLocal("z", 30);
        returnFunction();
    }
}
