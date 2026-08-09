public class Main {

    static int[] head, to, next, weight;
    static int edgeId;
    static final long INF = Long.MAX_VALUE / 2;
    static int[] parent;

    static class Node {
        int u;
        long dist;

        Node(int u, long dist) {
            this.u = u;
            this.dist = dist;
        }
    }

    private static void addEdge(int u, int v, int wt) {
        to[edgeId] = v;
        weight[edgeId] = wt;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n, int m) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * m];
        next = new int[2 * m];
        weight = new int[2 * m];
        edgeId = 0;

        parent = new int[n + 1];
    }

    private static long[] dijkstra(int n) {
        int root = 1;
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[root] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(node -> node.dist));
        queue.add(new Node(root, dist[root]));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int u = node.u;
            long d = node.dist;

            // skip outdated distance entries
            if (d > dist[u])
                continue;

            for (int e = head[u]; e >= 0; e = next[e]) {
                int v = to[e];
                int wt = weight[e];
                if (dist[u] + wt < dist[v]) { // relax v
                    parent[v] = u;
                    dist[v] = dist[u] + wt;
                    queue.add(new Node(v, dist[v]));
                }
            }
        }
        return dist;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        initialize(n, m);

        for (int i = 1; i <= m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int wt = sc.nextInt();
            addEdge(u, v, wt);
            addEdge(v, u, wt);
        }

        // perform dijkstra
        long[] dist = dijkstra(n);

        if (dist[n] == INF)
            System.out.println(-1);
        else {
            List<Integer> list = new ArrayList<>();
            int curr = n;
            while (curr > 0) {
                list.add(curr);
                curr = parent[curr];
            }
            StringBuilder res = new StringBuilder();
            for (int i = list.size() - 1; i >= 0; --i)
                res.append(list.get(i)).append(' ');
            System.out.println(res);
        }
    }
}