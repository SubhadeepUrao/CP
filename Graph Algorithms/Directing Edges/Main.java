public class Main {

    static class Edges {
        int[] head, next, to;
        int edgeId = 0;

        Edges(int n, int m) {
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

    static class Edge {
        int type, u, v;

        Edge(int t, int u, int v) {
            type = t;
            this.u = u;
            this.v = v;
        }
    }

    private static int validateDirectedGraph(int[] order, Edges directed, int[] indegree, int n) {
        int rank = 0;
        Deque<Integer> queue = new ArrayDeque<>();

        for (int u = 1; u <= n; ++u) {
            if (indegree[u] == 0) {
                queue.add(u);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[u] = ++rank; // node processed

            for (int edgeId = directed.head[u]; edgeId >= 0; edgeId = directed.next[edgeId]) {
                int v = directed.to[edgeId];
                --indegree[v];
                if (indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        return rank;
    }

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] indegree = new int[n + 1];
            List<Edge> listEdges = new ArrayList<>();
            Edges directed = new Edges(n, m);

            for (int i = 0; i < m; ++i) {
                int type = sc.nextInt();
                int u = sc.nextInt();
                int v = sc.nextInt();

                listEdges.add(new Edge(type, u, v));

                if (type == 1) {
                    ++indegree[v];
                    directed.addEdge(u, v);
                }
            }

            int[] order = new int[n + 1];
            int rank = validateDirectedGraph(order, directed, indegree, n); // Topological Sort

            if (rank < n) {
                res.append("NO\n");
            } else {
                res.append("YES\n");
                for (Edge edge : listEdges) {
                    int type = edge.type;
                    int u = edge.u;
                    int v = edge.v;
                    if (type == 1) { // directed
                        res.append(u).append(' ').append(v).append(" \n");
                    } else { // undirected
                        // include directed edge from lower topological order to higher topological
                        // order
                        if (order[u] < order[v])
                            res.append(u).append(' ').append(v).append(" \n");
                        else
                            res.append(v).append(' ').append(u).append(" \n");
                    }
                }
            }
        }
        System.out.println(res);
    }
}