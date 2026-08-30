public class Main {

    static class UndirectedGraph {
        int[] head, to, next;
        int edgeId = 0;

        UndirectedGraph(int n, int m) {
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

    static UndirectedGraph graph;
    static int n, m;

    private static int[] bfs(int root) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        dist[root] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int e = graph.head[u]; e >= 0; e = graph.next[e]) {
                int v = graph.to[e];
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }
        return dist;
    }

    private static void solve(FastScanner sc) {
        StringBuilder res = new StringBuilder();

        int t = sc.nextInt();

        while (t-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            int[] price = new int[m + 1];
            for (int i = 1; i <= m; ++i)
                price[i] = sc.nextInt();
            Arrays.sort(price);

            long[] prefixSum = new long[m + 1];
            for (int i = 1; i <= m; ++i) {
                prefixSum[i] = prefixSum[i - 1] + price[i];
            }

            graph = new UndirectedGraph(n, m);
            for (int i = 0; i < m; ++i) {
                int u = sc.nextInt();
                int v = sc.nextInt();

                graph.addEdge(u, v);
                graph.addEdge(v, u);
            }

            int[] distA = bfs(a);
            int[] distB = bfs(b);
            int[] distC = bfs(c);

            long minCost = Long.MAX_VALUE;
            for (int i = 1; i <= n; ++i) {
                int da = distA[i];
                int db = distB[i];
                int dc = distC[i];

                if (da + db + dc <= m)
                    minCost = Math.min(minCost, prefixSum[db] + prefixSum[da + db + dc]);
            }
            res.append(minCost).append('\n');
        }
        System.out.println(res);
    }
}