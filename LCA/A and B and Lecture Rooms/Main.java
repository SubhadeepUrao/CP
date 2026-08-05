public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[][] ancestor;
    static int[] parent, depth, subtree;
    static int MAX_JUMPS;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        MAX_JUMPS = 31 - Integer.numberOfLeadingZeros(n);
        ancestor = new int[n + 1][MAX_JUMPS + 1];

        parent = new int[n + 1];
        depth = new int[n + 1];
        subtree = new int[n + 1];
    }

    private static int getKthAncestor(int u, int k) {
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((k >>> j) & 1) == 1)
                u = ancestor[u][j];
        }
        return u;
    }

    private static int findLCA(int u, int v) {
        if (depth[u] < depth[v]) { int temp = u; u = v; v = temp; }

        u = getKthAncestor(u, depth[u] - depth[v]);

        // v is the ancestor of u
        if (u == v) return v;

        for (int j = MAX_JUMPS; j >= 0; --j) {
            if (ancestor[u][j] != ancestor[v][j]) {
                u = ancestor[u][j];
                v = ancestor[v][j];
            }
        }
        return parent[u];
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        initialize(n);

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        // perform DFS
        int[] stackNode = new int[n];
        int[] stackEdge = new int[n];
        int top = -1;

        int root = 1;
        stackNode[++top] = root;
        stackEdge[top] = head[root];
        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = to[edgeId];
                stackEdge[top] = next[edgeId];

                if (parent[u] != v) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;

                    ancestor[v][0] = u;
                    for (int j = 1; j <= MAX_JUMPS; ++j)
                        ancestor[v][j] = ancestor[ancestor[v][j - 1]][j - 1];

                    stackNode[++top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                subtree[u] += 1;
                if (u != root)
                    subtree[parent[u]] += subtree[u];
                --top;
            }
        }

        StringBuilder res = new StringBuilder();
        int m = sc.nextInt();
        while (m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            int lca = findLCA(x, y);
            int dist = depth[x] + depth[y] - 2 * depth[lca];

            if ((dist & 1) == 1) // is odd
                res.append("0\n");
            else if (depth[x] == depth[y]) {
                int mid = dist / 2;
                int childX = getKthAncestor(x, mid - 1);
                int childY = getKthAncestor(y, mid - 1);
                int reachable = n - subtree[childX] - subtree[childY];
                res.append(reachable).append('\n');
            } else { // depth[x] != depth[y]
                if (depth[x] < depth[y]) { int temp = x; x = y; y = temp; }

                int mid = dist / 2;
                int childX = getKthAncestor(x, mid - 1);

                int reachable = subtree[parent[childX]] - subtree[childX];
                res.append(reachable).append('\n');
            }
        }
        System.out.println(res);
    }
}