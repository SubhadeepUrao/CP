public class Main {

    static class Edge {
        int u, v, wt, id;

        Edge(int u, int v, int wt, int id) {
            this.u = u;
            this.v = v;
            this.wt = wt;
            this.id = id;
        }
    }

    static class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; ++i)
                parent[i] = i;
        }

        int find(int i) {
            if (parent[i] == i)
                return i;
            return parent[i] = find(parent[i]);
        }

        boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);

            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                return true;
            }
            return false;
        }

    }

    static int[] head, to, next, edgeWt;
    static int edgeId = 0;
    static int MAX_JUMPS;
    static int[][] ancestor, max;
    static int[] parent, depth;

    private static void initializeMST(int n) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];
        edgeWt = new int[2 * n];

        MAX_JUMPS = 31 - Integer.numberOfLeadingZeros(n);
        ancestor = new int[n + 1][MAX_JUMPS + 1];
        max = new int[n + 1][MAX_JUMPS + 1];

        parent = new int[n + 1];
        depth = new int[n + 1];
    }

    private static void addEdgeMST(int u, int v, int wt) {
        to[edgeId] = v;
        edgeWt[edgeId] = wt;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void dfs(int n) {
        int[] stackNode = new int[n];
        int[] stackEdge = new int[n];
        int top = -1;

        int root = 1; // root node
        stackNode[++top] = root;
        stackEdge[top] = head[root];

        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = to[edgeId];
                stackEdge[top] = next[edgeId];

                if (parent[u] != v) { // explore v
                    parent[v] = u;
                    depth[v] = depth[u] + 1;

                    ancestor[v][0] = u;
                    max[v][0] = edgeWt[edgeId];
                    for (int j = 1; j <= MAX_JUMPS; ++j) {
                        int anc = ancestor[v][j - 1];
                        ancestor[v][j] = ancestor[anc][j - 1];
                        max[v][j] = Math.max(max[v][j - 1], max[anc][j - 1]);
                    }

                    stackNode[++top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                --top;
            }
        }
    }

    private static int getMaxEdgeWeightOnPath(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int k = depth[u] - depth[v];
        int maxWt = 0;
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((k >> j) & 1) == 1) {
                maxWt = Math.max(maxWt, max[u][j]);
                u = ancestor[u][j];
            }
        }

        if (u == v)
            return maxWt;

        for (int j = MAX_JUMPS; j >= 0; --j) {
            if (ancestor[u][j] != ancestor[v][j]) {
                maxWt = Math.max(maxWt, max[u][j]);
                maxWt = Math.max(maxWt, max[v][j]);
                u = ancestor[u][j];
                v = ancestor[v][j];
            }
        }

        // include edges from u and v to their LCA
        maxWt = Math.max(maxWt, max[u][0]);
        maxWt = Math.max(maxWt, max[v][0]);
        return maxWt;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Edge[] originalEdges = new Edge[m];
        Edge[] sortedEdges = new Edge[m];

        for (int i = 0; i < m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int wt = sc.nextInt();
            Edge e = new Edge(u, v, wt, i);
            originalEdges[i] = e;
            sortedEdges[i] = e;
        }

        // sort edges by weight for Kruskal's Algorithm
        Arrays.sort(sortedEdges, Comparator.comparingInt(e -> e.wt));

        initializeMST(n);

        // generate MST
        DSU dsu = new DSU(n);
        long totalWeightMST = 0L;
        boolean[] inMST = new boolean[m];

        for (Edge e : sortedEdges) {
            int u = e.u;
            int v = e.v;
            if (dsu.union(u, v)) {
                int wt = e.wt;
                totalWeightMST += wt;
                inMST[e.id] = true;
                addEdgeMST(u, v, wt);
                addEdgeMST(v, u, wt);
            }
        }

        // perform DFS on MST
        dfs(n);

        // process answer for each edge
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            if (inMST[i]) {
                res.append(totalWeightMST).append('\n');
            } else {
                int u = originalEdges[i].u;
                int v = originalEdges[i].v;

                int maxEdgeWeight = getMaxEdgeWeightOnPath(u, v);
                long ans = totalWeightMST - maxEdgeWeight + originalEdges[i].wt;
                res.append(ans).append('\n');
            }
        }
        System.out.println(res);
    }
}