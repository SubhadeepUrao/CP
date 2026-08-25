public class Main {

    static int[] head, next, to;
    static int edgeId = 0;
    static int[] cost;
    static long[] arrivalTime;
    static Set<Integer>[] travellers_arrival;
    static final long INF = Long.MAX_VALUE >>> 1;

    static class Node {
        int u;
        long cost;

        Node(int u, long cost) {
            this.u = u;
            this.cost = cost;
        }
    }

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n, int m) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * m];
        next = new int[2 * m];

        cost = new int[m];
        travellers_arrival = new HashSet[n + 1];

        arrivalTime = new long[n + 1];
        Arrays.fill(arrivalTime, INF);
    }

    private static long leastTimeToReach(int n) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));
        int root = 1;
        queue.add(new Node(root, 0));
        arrivalTime[root] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int u = node.u;
            long c = node.cost;

            // skip outdated entries
            if (c > arrivalTime[u])
                continue;

            // shortest path to destination found
            if (u == n) return c;

            long depTime = c;
            while (travellers_arrival[u].contains((int) depTime)) {
                ++depTime;
            }

            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                long time_to_reach_V = depTime + cost[edgeId >>> 1];
                if (time_to_reach_V < arrivalTime[v]) {
                    arrivalTime[v] = time_to_reach_V;
                    queue.add(new Node(v, time_to_reach_V));
                }

            }
        }
        return arrivalTime[n] != INF ? arrivalTime[n] : -1;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt(); // #planets in galaxy
        int m = sc.nextInt(); // #pairs of planets which Jack can travel

        initialize(n, m);

        for (int i = 0; i < m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();

            cost[i] = c;
            addEdge(u, v);
            addEdge(v, u);
        }

        for (int i = 1; i <= n; ++i) {
            travellers_arrival[i] = new HashSet<>();
            int k = sc.nextInt();
            while (k-- > 0) {
                travellers_arrival[i].add(sc.nextInt());
            }
        }

        System.out.println(leastTimeToReach(n));

    }
}