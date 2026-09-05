public class Main {

    static final long INF = Long.MAX_VALUE;

    static class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; ++i) parent[i] = i;
        }

        int find(int u) {
            if (parent[u] == u) return u;
            return parent[u] = find(parent[u]);
        }

        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) parent[rootU] = rootV;
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[] C = new int[k + 1]; // bacteria types
        int[] type = new int[n + 1]; // type of each bacteria [1...n]
        int[] firstnode_of_type = new int[k + 1]; // tracks first node of each type
        int nodeId = 1;
        for (int i = 1; i <= k; ++i) {
            C[i] = sc.nextInt();
            firstnode_of_type[i] = nodeId;
            for (int j = 0; j < C[i]; ++j) {
                type[nodeId++] = i;
            }
        }

        DSU dsu = new DSU(n);
        long[][] dist = new long[k + 1][k + 1];
        for (int i = 1; i <= k; ++i) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 1; i <= m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int x = sc.nextInt();

            if (x == 0) dsu.union(u, v);

            int typeU = type[u];
            int typeV = type[v];
            dist[typeU][typeV] = Math.min(dist[typeU][typeV], x);
            dist[typeV][typeU] = Math.min(dist[typeV][typeU], x);
        }

        // check if each type i is connected to the same type i via 0-connected roads
        for (int i = 1; i <= k; ++i) {
            int root = dsu.find(firstnode_of_type[i]);
            for (int j = firstnode_of_type[i] + 1; j < firstnode_of_type[i] + C[i]; ++j) {
                if (root != dsu.find(j)) {
                    System.out.println("No");
                    return;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        res.append("Yes\n");

        // Floyd-Warshall Algorithm
        for (int t = 1; t <= k; ++t)
            for (int i = 1; i <= k; ++i)
                for (int j = 1; j <= k; ++j)
                    if (dist[i][t] < INF && dist[t][j] < INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][t] + dist[t][j]);

        for (int i = 1; i <= k; ++i) {
            for (int j = 1; j <= k; ++j)
                res.append(dist[i][j] == INF ? -1 : dist[i][j]).append(' ');
            res.append('\n');
        }
        System.out.println(res);
    }
}