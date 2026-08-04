public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[][] ancestor;
    static int MAX_JUMPS;
    static int[] parent, depth, edgeMap;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initializeVar(int n) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        MAX_JUMPS = 31 - Integer.numberOfLeadingZeros(n);
        ancestor = new int[n + 1][MAX_JUMPS + 1];

        parent = new int[n + 1];
        depth = new int[n + 1];
        edgeMap = new int[n + 1];
    }

    private static int getKthAncestor(int u, int k) {
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((k >> j) & 1) == 1)
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

        if (u == v)
            return v;

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
        initializeVar(n);

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        // perform DFS
        int[] stackNode = new int[n];
        int top = -1;
        int root = 1;
        stackNode[++top] = root; // root
        while (top >= 0) {
            int u = stackNode[top--];

            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                if (parent[u] != v) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    edgeMap[v] = edgeId;

                    ancestor[v][0] = u;
                    for (int j = 1; j <= MAX_JUMPS; ++j)
                        ancestor[v][j] = ancestor[ancestor[v][j - 1]][j - 1];

                    stackNode[++top] = v;
                }
            }
        }

        int[] diff = new int[n + 1]; // DIFFERENCE ARRAY CONCEPT

        int k = sc.nextInt();
        while (k-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            int lca = findLCA(u, v);
            diff[u] += 1;
            diff[v] += 1;
            diff[lca] -= 2;
        }

        // cumulative sum of diff array
        int[] stackEdge = new int[n];
        stackNode[++top] = root;
        stackEdge[top] = head[root];

        int[] res = new int[edgeId];

        while (top >= 0) {
            int u = stackNode[top];
            int edge = stackEdge[top];

            if (edge >= 0) {
                int v = to[edge];
                stackEdge[top] = next[edge];
                if (parent[v] == u) {
                    ++top;
                    stackNode[top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                if (u != root) {
                    diff[parent[u]] += diff[u];
                    res[edgeMap[u]] = diff[u];
                }
                --top;
            }
        }

        StringBuilder str = new StringBuilder();
        int len = edgeId;
        for (int i = 1; i < len; i += 2) {
            str.append(res[i - 1] + res[i]).append(' ');
        }
        System.out.println(str);
    }
}