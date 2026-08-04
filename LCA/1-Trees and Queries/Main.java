public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[][] ancestor;
    static int MAX_JUMPS;
    static int[] depth, parent;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static int getKthAncestor(int u, int k) {
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((k >> j) & 1) == 1) { // jth bit is set -> can jump 2^j
                u = ancestor[u][j];
            }
        }
        return u;
    }

    private static int findLCA(int u, int v) {
        if (depth[u] < depth[v]) { // swap u, v
            int temp = u;
            u = v;
            v = temp;
        }

        u = getKthAncestor(u, depth[u] - depth[v]);

        // v is the ancestor of u
        if (u == v)
            return v;

        for (int j = MAX_JUMPS; j >= 0; --j) {
            // if (ancestor[u][j] == 0) continue;
            if (ancestor[u][j] != ancestor[v][j]) {
                u = ancestor[u][j];
                v = ancestor[v][j];
            }
        }

        return parent[u];
    }

    private static int getDist(int u, int v) {
        int lca = findLCA(u, v);
        return depth[u] + depth[v] - 2 * depth[lca];
    }

    private static boolean canReach(int dist, int k) {
        return dist <= k && ((k - dist) & 1) == 0;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        parent = new int[n + 1];
        depth = new int[n + 1];

        MAX_JUMPS = 31 - Integer.numberOfLeadingZeros(n); // initialize MAX_JUMPS
        ancestor = new int[n + 1][MAX_JUMPS + 1];

        // perform DFS
        int[] stack = new int[n];
        int top = -1;
        stack[++top] = 1; // root
        while (top >= 0) {
            int u = stack[top--];
            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                if (parent[u] != v) { // explore v
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    ancestor[v][0] = u;
                    stack[++top] = v;
                }
            }
        }

        // populate ancestor table
        for (int j = 1; j <= MAX_JUMPS; ++j) {
            for (int node = 1; node <= n; ++node) {
                ancestor[node][j] = ancestor[ancestor[node][j - 1]][j - 1];
            }
        }

        StringBuilder res = new StringBuilder();
        int q = sc.nextInt();
        while (q-- > 0) {
            // added edge
            int x = sc.nextInt();
            int y = sc.nextInt();

            // find path between (a, b) that is exactly k length
            int a = sc.nextInt();
            int b = sc.nextInt();
            int k = sc.nextInt();

            // Case I: a -> b
            // Case II: a -> (x -> y) -> b [via (x, y)]
            // Case III: a -> (y -> x) -> b [via (y, x)]

            if (canReach(getDist(a, b), k) || canReach(getDist(a, x) + 1 + getDist(y, b), k)
                    || canReach(getDist(a, y) + 1 + getDist(x, b), k))
                res.append("YES\n");
            else
                res.append("NO\n");
        }
        System.out.println(res);
    }
}