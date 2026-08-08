public class Main {

    static int[] head, next, to;
    static int edgeId = 0;
    static int[] parent, depth;
    static int[] stackNode, stackEdge;
    static int maxDepth = 0;

    private static void initialize(int n) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[n];
        next = new int[n];

        parent = new int[n + 1];
        depth = new int[n + 1];

        stackNode = new int[n];
        stackEdge = new int[n];
    }

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void dfs(int root) {
        int top = -1;

        stackNode[++top] = root;
        stackEdge[top] = head[root];

        while (top >= 0) {
            int u = stackNode[top];
            int e = stackEdge[top];

            if (e >= 0) {
                int v = to[e];
                stackEdge[top] = next[e];

                depth[v] = depth[u] + 1;
                maxDepth = Math.max(maxDepth, depth[v]);

                stackNode[++top] = v;
                stackEdge[top] = head[v];
            } else
                --top;
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        initialize(n);

        int[] stack = new int[n];
        int top = -1;
        for (int i = 1; i <= n; ++i) {
            parent[i] = sc.nextInt();
            if (parent[i] < 0)
                stack[++top] = i;
            else
                addEdge(parent[i], i);
        }

        while (top >= 0) {
            dfs(stack[top--]);
        }

        System.out.println(maxDepth + 1);
    }
}