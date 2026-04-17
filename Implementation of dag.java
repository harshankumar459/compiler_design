import java.util.*;

class DAG {
    private final int vertices;
    private final List<List<Integer>> adj;

    DAG(int v) {
        vertices = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    void topologicalSort() {
        int[] indegree = new int[vertices];
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < vertices; i++) if (indegree[i] == 0) q.add(i);

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : adj.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) q.add(v);
            }
        }

        if (order.size() != vertices) {
            System.out.println("Graph is not a DAG (cycle detected)");
        } else {
            System.out.println("Topological Order: " + order);
        }
    }
}

public class DAGImplementation {
    public static void main(String[] args) {
        DAG dag = new DAG(6);
        dag.addEdge(5, 2);
        dag.addEdge(5, 0);
        dag.addEdge(4, 0);
        dag.addEdge(4, 1);
        dag.addEdge(2, 3);
        dag.addEdge(3, 1);

        dag.topologicalSort();
    }
}
