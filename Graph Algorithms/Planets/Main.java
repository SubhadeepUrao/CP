// TLE

public class Main {

    static int[] head, next, to;
    static int edgeId = 0;
    static int[] cost, arrivalTime;
    static Set<Integer>[] travellers_arrival;
    static final int MAX_TIME = 1_000_000_000;

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

        arrivalTime = new int[n + 1];
        Arrays.fill(arrivalTime, MAX_TIME);
    }

    private static int leastTimeToReach(int n) {
        Deque<Integer> queue = new ArrayDeque<>();
        int root = 1;
        queue.add(root);
        arrivalTime[root] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            int currTime = arrivalTime[u];
            while (travellers_arrival[u].contains(currTime)) {
                ++currTime;
            }

            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                int time_to_reach_V = currTime + cost[edgeId >>> 1];
                if (time_to_reach_V < arrivalTime[v]) {
                    arrivalTime[v] = time_to_reach_V;
                    queue.add(v);
                }

            }
        }
        return arrivalTime[n] != MAX_TIME ? arrivalTime[n] : -1;
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