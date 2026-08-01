public class Main {

    static int[] head, to, next, edgeWt;
    static int edgeId = 0;
    static int[] parent, depth;
    static int MAX_JUMPS, MAX_FLOW = 1_000_000_000;
    static int[][] ancestor, max_flow;

    static void addEdge(int u, int v, int wt) {
        to[edgeId] = v;
        edgeWt[edgeId] = wt;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void precompute(int n) {
        MAX_JUMPS = 31 - Integer.numberOfLeadingZeros(n);

        ancestor = new int[n + 1][MAX_JUMPS + 1];
        max_flow = new int[n + 1][MAX_JUMPS + 1];
        parent = new int[n + 1];
        depth = new int[n + 1];

        // default values
        for (int i = 1; i <= n; ++i) {
            Arrays.fill(max_flow[i], MAX_FLOW);
        }

        // perform DFS
        int[] stack = new int[n];
        int top = -1;
        stack[++top] = 1; // root
        while (top >= 0) {
            int u = stack[top--];

            for (int e = head[u]; e >= 0; e = next[e]) {
                int v = to[e];
                int wt = edgeWt[e];
                if (parent[u] != v) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;

                    ancestor[v][0] = u;
                    max_flow[v][0] = wt;

                    stack[++top] = v;
                }
            }
        }

        // compute ancestor table
        for (int j = 1; j <= MAX_JUMPS; ++j) {
            for (int u = 1; u <= n; ++u) {
                int ancest = ancestor[u][j - 1];
                ancestor[u][j] = ancestor[ancest][j - 1];
                max_flow[u][j] = Math.min(max_flow[u][j - 1], max_flow[ancest][j - 1]);
            }
        }
    }

    private static int getKthAncestor(int u, int K) {
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((K >> j) & 1) == 1)
                u = ancestor[u][j];
        }
        return u;
    }

    private static int findLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        u = getKthAncestor(u, depth[u] - depth[v]);

        // v is an ancestor of u
        if (u == v)
            return v;

        for (int j = MAX_JUMPS; j >= 0; --j) {
            if (ancestor[u][j] == 0)
                continue;
            if (ancestor[u][j] != ancestor[v][j]) {
                u = ancestor[u][j];
                v = ancestor[v][j];
            }
        }

        return parent[u];
    }

    private static int getMaxFlow(int u, int k) {
        int max = MAX_FLOW;
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((k >> j) & 1) == 1) {
                max = Math.min(max, max_flow[u][j]);
                u = ancestor[u][j];
            }
        }
        return max;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];
        edgeWt = new int[2 * m];

        for (int i = 0; i < m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int wt = sc.nextInt();
            addEdge(u, v, wt);
            addEdge(v, u, wt);
        }

        precompute(n);

        // handle queries
        int q = sc.nextInt();
        StringBuilder res = new StringBuilder();
        while (q-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            int lca = findLCA(a, b);
            int lenA = depth[a] - depth[lca];
            int lenB = depth[b] - depth[lca];

            int ans = Math.min(getMaxFlow(a, lenA), getMaxFlow(b, lenB));
            res.append(ans).append('\n');
        }
        System.out.println(res);
    }
}