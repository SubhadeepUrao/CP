public class Main {

    static int[] disc, low;
    static int[] stackNode, stackEdge;
    static boolean[] inStack;
    static int[] tarjanStack;
    static int tarjanTop = -1;
    static List<List<Integer>> SCC;
    static int timer = 0;

    static class DirectedGraph {
        int[] head, to, next;
        int edgeId = 0;

        DirectedGraph(int n) {
            head = new int[n + 1];
            Arrays.fill(head, -1);
            to = new int[2 * n];
            next = new int[2 * n];
        }

        void addEdge(int u, int v) {
            to[edgeId] = v;
            next[edgeId] = head[u];
            head[u] = edgeId++;
        }
    }

    private static void dfs(int root, DirectedGraph graph) {
        int top = -1;

        // configure root
        stackNode[++top] = root;
        stackEdge[top] = graph.head[root];
        tarjanStack[++tarjanTop] = root;
        inStack[root] = true;
        disc[root] = low[root] = timer++;

        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = graph.to[edgeId];
                stackEdge[top] = graph.next[edgeId];

                if (disc[v] < 0) { // undiscovered
                    disc[v] = low[v] = timer++;
                    inStack[v] = true;
                    tarjanStack[++tarjanTop] = v;

                    stackNode[++top] = v;
                    stackEdge[top] = graph.head[v];
                } else if (inStack[v]) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            } else {
                --top;

                if (top >= 0) { // update parent's low value
                    int parent = stackNode[top];
                    low[parent] = Math.min(low[parent], low[u]);
                }

                if (disc[u] == low[u]) { // found SSC
                    List<Integer> currSCC = new ArrayList<>();
                    int v;
                    do {
                        v = tarjanStack[tarjanTop--];
                        inStack[v] = false;
                        currSCC.add(v);
                    } while (u != v);

                    SCC.add(currSCC);
                }
            }
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] P = new int[n];
        int[] Q = new int[n];

        for (int i = 0; i < n; ++i) P[i] = sc.nextInt();
        for (int i = 0; i < n; ++i) Q[i] = sc.nextInt();

        // construct graph
        DirectedGraph graph = new DirectedGraph(n);
        for (int i = 1; i < n; ++i) {
            graph.addEdge(P[i - 1], P[i]);
            graph.addEdge(Q[i - 1], Q[i]);
        }

        disc = new int[n + 1];
        low = new int[n + 1];
        Arrays.fill(disc, -1);
        stackNode = new int[n];
        stackEdge = new int[n];
        tarjanStack = new int[n];
        inStack = new boolean[n + 1];
        SCC = new ArrayList<>();

        // perform Tarjan's Algo
        for (int i = 1; i <= n; ++i) {
            if (disc[i] < 0) // undiscovered
                dfs(i, graph);
        }

        if (SCC.size() < k) {
            System.out.println("NO");
            return;
        }

        // reverse the order to form topological order
        Collections.reverse(SCC);

        int[] groupId = new int[n + 1];
        for (int groupCnt = 0; groupCnt < SCC.size(); ++groupCnt) {
            for (int u : SCC.get(groupCnt))
                groupId[u] = groupCnt;
        }

        StringBuilder res = new StringBuilder();
        res.append("YES\n");
        for (int i = 1; i <= n; ++i) {
            int index = Math.min(25, groupId[i]);
            res.append((char) ('a' + index));
        }
        System.out.println(res);
    }
}