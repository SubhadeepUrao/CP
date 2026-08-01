public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[][] ancestor;
    static int[] parent, depth;
    static int MAX_JUMPS;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static int computeMaxJump(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    private static int findLCA(int u, int v, int[] parent, int[] depth) {
        if (depth[u] < depth[v]) { int temp = u; u = v; v = temp; }

        u = climbTree(u, depth[u] - depth[v]);

        // if v was an ancestor of u
        if (u == v) return u;

        for (int j = MAX_JUMPS; j >= 0; --j) {
            if (ancestor[u][j] != 0 && ancestor[u][j] != ancestor[v][j]) {
                u = ancestor[u][j];
                v = ancestor[v][j];
            }
        }
        return parent[u];
    }

    private static int climbTree(int u, int K) {
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((K >> j) & 1) == 1) {
                u = ancestor[u][j];
            }
        }
        return u;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        if (n > 1) {
            for (int i = 1; i < n; ++i) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                addEdge(u, v);
                addEdge(v, u);
            }
        }

        parent = new int[n + 1];
        depth = new int[n + 1];

        // perform DFS
        int[] stack = new int[n];
        int top = -1;
        stack[++top] = 1; // root
        while (top >= 0) {
            int u = stack[top--];

            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                if (parent[u] != v) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[++top] = v;
                }
            }
        }

        // compute ancestor table
        MAX_JUMPS = computeMaxJump(n);
        ancestor = new int[n + 1][MAX_JUMPS + 1];

        // nodes reached at 1 jump i.e. 2^0
        for (int u = 1; u <= n; ++u)
            ancestor[u][0] = parent[u];

        for (int j = 1; j <= MAX_JUMPS; ++j) {
            for (int node = 1; node <= n; ++node) {
                ancestor[node][j] = ancestor[ancestor[node][j - 1]][j - 1];
            }
        }

        // handle queries
        int q = sc.nextInt();
        StringBuilder res = new StringBuilder();
        while (q-- > 0) {
            int a = sc.nextInt(); // node sloth starts on
            int b = sc.nextInt(); // node to move to
            int c = sc.nextInt(); // energy

            int lca = findLCA(a, b, parent, depth);

            int lenA = depth[a] - depth[lca];
            int lenB = depth[b] - depth[lca];
            int dist = lenA + lenB;

            if (c < lenA)
                res.append(climbTree(a, c)).append('\n');
            else if (c == lenA)
                res.append(lca).append('\n');
            else if (lenA < c && c < dist)
                res.append(climbTree(b, dist - c)).append('\n');
            else
                res.append(b).append('\n');
        }
        System.out.println(res);
    }
}
