public class Main {

    static int[] head, next, to;
    static int edgeId = 0;
    static int[] cost;
    static long[] arrivalTime;
    static int[][] travellers_arrival;
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
        travellers_arrival = new int[n + 1][];

        arrivalTime = new long[n + 1];
        Arrays.fill(arrivalTime, INF);
    }

    // Binary search to find valid departure time skipping contiguous blocked segments
    private static long getValidDepartureTime(int u, long currentTime) {
        int[] arrivals = travellers_arrival[u];
        if (arrivals.length == 0) return currentTime;

        // Binary search to find if currentTime exists or where it sits
        int idx = Arrays.binarySearch(arrivals, (int) currentTime);
        if (idx < 0) {
            return currentTime; // Time is free
        }

        // If time is blocked, advance to the end of the contiguous blocked segment
        while (idx < arrivals.length && arrivals[idx] == currentTime) {
            currentTime++;
            idx++;
        }
        return currentTime;
    }

    private static long leastTimeToReach(int n) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));
        queue.add(new Node(1, 0));
        arrivalTime[1] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int u = node.u;
            long c = node.cost;

            if (c > arrivalTime[u]) continue;
            if (u == n) return c;

            // Compute valid departure time for node u
            long currTime = getValidDepartureTime(u, c);

            for (int eId = head[u]; eId >= 0; eId = next[eId]) {
                int v = to[eId];
                long time_to_reach_V = currTime + cost[eId >>> 1];

                if (time_to_reach_V < arrivalTime[v]) {
                    arrivalTime[v] = time_to_reach_V;
                    queue.add(new Node(v, time_to_reach_V));
                }
            }
        }
        return arrivalTime[n] != INF ? arrivalTime[n] : -1;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

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
            int k = sc.nextInt();
            travellers_arrival[i] = new int[k];
            for (int j = 0; j < k; j++) {
                travellers_arrival[i][j] = sc.nextInt();
            }
        }

        System.out.println(leastTimeToReach(n));
    }
}