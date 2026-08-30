public class Main {

    static class DirectedGraph {
        int[] head, to, next;
        int edgeId = 0;

        DirectedGraph(int n, int m) {
            head = new int[n + 1];
            Arrays.fill(head, -1);
            to = new int[m];
            next = new int[m];
        }

        void addEdge(int u, int v) {
            to[edgeId] = v;
            next[edgeId] = head[u];
            head[u] = edgeId++;
        }
    }

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt();

            int[] k = new int[n + 1];
            int[][] dependency = new int[n + 1][];
            int edges = 0;
            for (int v = 1; v <= n; ++v) {
                k[v] = sc.nextInt();
                edges += k[v];
                dependency[v] = new int[k[v]];
                for (int i = 0; i < k[v]; ++i) {
                    dependency[v][i] = sc.nextInt();
                }
            }

            DirectedGraph graph = new DirectedGraph(n, edges);
            int[] indegree = new int[n + 1];
            int[] passes = new int[n + 1];
            for (int v = 1; v <= n; ++v) {
                indegree[v] = k[v];
                passes[v] = 1; // default to pass 1
                for (int i = 0; i < k[v]; ++i) {
                    int u = dependency[v][i];
                    graph.addEdge(u, v);
                }
            }

            Deque<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= n; ++i) {
                if (indegree[i] == 0)
                    queue.add(i);
            }

            int processed = 0;
            int maxPasses = 0;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                ++processed;
                maxPasses = Math.max(maxPasses, passes[u]);

                for (int e = graph.head[u]; e >= 0; e = graph.next[e]) {
                    int v = graph.to[e];

                    int extraPass = u > v ? 1 : 0;
                    passes[v] = Math.max(passes[v], passes[u] + extraPass);

                    --indegree[v];
                    if (indegree[v] == 0)
                        queue.add(v);
                }
            }

            if (processed < n)
                res.append("-1\n");
            else
                res.append(maxPasses).append('\n');
        }
        System.out.println(res);
    }
}