public class Main {

    static class Graph {
        int[] head, next, to;
        int edgeId = 0;

        Graph(int n) {
            head = new int[n + 1];
            Arrays.fill(head, -1);
            to = new int[2 * n];
            next = new int[2 * n];
        }

        Graph(int n, int m) {
            head = new int[n + 1];
            Arrays.fill(head, -1);
            to = new int[2 * m];
            next = new int[2 * m];
        }

        void addEdge(int u, int v) {
            to[edgeId] = v;
            next[edgeId] = head[u];
            head[u] = edgeId++;
        }
    }

    static class UndirectedGraph extends Graph {
        boolean[] isBridge;

        UndirectedGraph(int n, int m) {
            super(n, m);
            isBridge = new boolean[2 * m];
        }
    }

    static int n, m;
    static int[] low, disc;
    static int timer;
    static int[] stackNode, stackEdge;
    static int top;

    private static void initialize() {
        timer = 0;
        low = new int[n + 1];
        disc = new int[n + 1];
        top = -1;
        stackNode = new int[n];
        stackEdge = new int[n];
    }

    private static void dfsTarjan(UndirectedGraph graph) {
        int root = 1;

        disc[root] = low[root] = ++timer;
        stackNode[++top] = root;
        stackEdge[top] = graph.head[root];

        int[] parentEdge = new int[n + 1];
        Arrays.fill(parentEdge, -1);

        while (top >= 0) {
            int u = stackNode[top];
            int e = stackEdge[top];

            if (e >= 0) {
                int v = graph.to[e];
                stackEdge[top] = graph.next[e];

                // Skip traversing backward over the parent edge
                if (parentEdge[u] != -1 && e == (parentEdge[u] ^ 1))
                    continue;

                if (disc[v] == 0) {
                    parentEdge[v] = e;
                    disc[v] = low[v] = ++timer;

                    stackNode[++top] = v;
                    stackEdge[top] = graph.head[v];
                } else {
                    low[u] = Math.min(low[u], disc[v]);
                }
            } else {
                --top;

                if (top >= 0) {
                    int p = stackNode[top];
                    low[p] = Math.min(low[p], low[u]);

                    if (disc[p] < low[u] && parentEdge[u] != -1) {
                        graph.isBridge[parentEdge[u]] = true;
                        graph.isBridge[parentEdge[u] ^ 1] = true;
                    }
                }

            }
        }
    }

    private static void condenseComponentDFS(int[] compId, int root, int id, UndirectedGraph graph) {
        compId[root] = id;

        stackNode[++top] = root;

        while (top >= 0) {
            int u = stackNode[top--];

            for (int e = graph.head[u]; e >= 0; e = graph.next[e]) {
                int v = graph.to[e];

                if (compId[v] == 0 && !graph.isBridge[e]) {
                    compId[v] = id;
                    stackNode[++top] = v;
                }
            }
        }
    }

    private static int computeMaxJump(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    private static void buildAncestorTableDFS(Graph tree, int n) {
        int root = 1;
        stackNode[++top] = root;
        int[] parent = new int[n + 1];

        while (top >= 0) {
            int u = stackNode[top--];

            for (int e = tree.head[u]; e >= 0; e = tree.next[e]) {
                int v = tree.to[e];
                if (parent[u] != v) { // explore
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    ancestor[v][0] = u;
                    for (int j = 1; j <= MAX_JUMPS; ++j) {
                        ancestor[v][j] = ancestor[ancestor[v][j - 1]][j - 1];
                    }
                    stackNode[++top] = v;
                }
            }
        }
    }

    private static int getKthAncestor(int u, int k) {
        for (int j = 0; j <= MAX_JUMPS; ++j) { // order doesnot matter
            if (((k >> j) & 1) == 1)
                u = ancestor[u][j];
        }
        return u;
    }

    private static int getLCA(int u, int v) {
        if (depth[u] < depth[v]) { // swap
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
        return ancestor[u][0];
    }

    static int MAX_JUMPS;
    static int[][] ancestor;
    static int[] depth;

    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();

        initialize();

        UndirectedGraph graph = new UndirectedGraph(n, m);
        for (int i = 0; i < m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.addEdge(u, v);
            graph.addEdge(v, u);
        }

        // find Bridges
        dfsTarjan(graph);

        // condense Components
        int[] compId = new int[n + 1];
        int components = 0;
        for (int i = 1; i <= n; ++i) {
            if (compId[i] == 0) {
                condenseComponentDFS(compId, i, ++components, graph);
            }
        }

        // build Bridge Tree
        Graph tree = new Graph(components);

        for (int u = 1; u <= n; ++u) {
            for (int e = graph.head[u]; e >= 0; e = graph.next[e]) {
                int v = graph.to[e];
                if (graph.isBridge[e] && compId[u] < compId[v]) {
                    tree.addEdge(compId[u], compId[v]);
                    tree.addEdge(compId[v], compId[u]);
                }
            }
        }

        // precompute LCA depth and binary lifting table
        MAX_JUMPS = computeMaxJump(components);
        ancestor = new int[components + 1][MAX_JUMPS + 1];
        depth = new int[components + 1];
        buildAncestorTableDFS(tree, components);

        int k = sc.nextInt();
        StringBuilder res = new StringBuilder();
        while (k-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int lca = getLCA(compId[u], compId[v]);
            res.append((depth[compId[u]] + depth[compId[v]] - 2 * depth[lca])).append('\n');
        }
        System.out.println(res);
    }
}