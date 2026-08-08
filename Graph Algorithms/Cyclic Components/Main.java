public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[] degree;
    static boolean[] visited;
    static int[] stackNode, stackEdge;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n, int m) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * m];
        next = new int[2 * m];

        visited = new boolean[n + 1];
        degree = new int[n + 1];

        stackNode = new int[n];
        stackEdge = new int[n];
    }

    private static boolean isCyclicComponent(int root) {
        boolean isValidCycle = true;
        int top = -1;
        stackNode[++top] = root;
        stackEdge[top] = head[root];
        visited[root] = true;

        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = to[edgeId];
                stackEdge[top] = next[edgeId];

                if (!visited[v]) { // explore v
                    visited[v] = true;
                    stackNode[++top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                if (degree[u] != 2) isValidCycle = false;
                --top;
            }
        }
        return isValidCycle;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        initialize(n, m);

        for (int i = 1; i <= m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);

            ++degree[u];
            ++degree[v];
        }

        int cyclicComp = 0;
        for (int i = 1; i <= n; ++i) {
            if (!visited[i] && isCyclicComponent(i)) {
                ++cyclicComp;
            }
        }
        System.out.println(cyclicComp);
    }
}