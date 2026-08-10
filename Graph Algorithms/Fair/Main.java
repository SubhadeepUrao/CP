public class Main {

    static int[] head, to, next;
    static int edgeId;
    static int[] coins;
    static List<Integer>[] group;
    static int[][] dist;
    static Deque<Integer> queue;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n, int m, int k) {
        edgeId = 0;
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * m];
        next = new int[2 * m];

        coins = new int[n + 1];

        group = new ArrayList[k + 1];
        for (int i = 1; i <= k; ++i)
            group[i] = new ArrayList<>();

        dist = new int[k + 1][n + 1];
        for (int i = 1; i <= k; ++i)
            Arrays.fill(dist[i], -1);

        queue = new ArrayDeque<>();
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int s = sc.nextInt();

        initialize(n, m, k);

        // group towns that produce same type of goods
        for (int i = 1; i <= n; ++i) {
            int t = sc.nextInt();
            group[t].add(i);
        }

        for (int i = 1; i <= m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        for (int t = 1; t <= k; ++t) {
            // initialize the multi-source BFS
            for (int town : group[t]) {
                dist[t][town] = 0;
                queue.add(town);
            }

            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                    int v = to[edgeId];
                    if (dist[t][v] == -1) {
                        dist[t][v] = dist[t][u] + 1;
                        queue.add(v);
                    }
                }
            }
        }

        // calculate cost for each town
        StringBuilder res = new StringBuilder();
        int[] cost = new int[k];
        for (int town = 1; town <= n; ++town) {
            for (int type = 1; type <= k; ++type) {
                cost[type - 1] = dist[type][town];
            }

            Arrays.sort(cost);

            int total = 0;
            for (int i = 0; i < s; ++i) {
                total += cost[i];
            }
            res.append(total).append(' ');
        }
        System.out.println(res);
    }
}