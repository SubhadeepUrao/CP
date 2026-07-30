public class Main {

    private static int[] head, next, to;
    private static int edgeID = 0;

    private static void addEdge(int u, int v) {
        to[edgeID] = v;
        next[edgeID] = head[u];
        head[u] = edgeID++;
    }

    private static int[] bfs(int root, int n, int[] parent) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        int[] q = new int[n];
        int front = 0, end = -1;

        q[++end] = root;
        dist[root] = 0;
        int farthest = root;
        int maxDist = 0;

        while (front <= end) {
            int u = q[front++];

            if (maxDist < dist[u]) {
                maxDist = dist[u];
                farthest = u;
            }

            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                if (dist[v] == -1) { // unvisited
                    dist[v] = dist[u] + 1;
                    q[++end] = v;
                    parent[v] = u;
                }
            }
        }

        return new int[] { farthest, maxDist };
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        if (n == 3) {
            System.out.println(2);
            System.out.println("1 2 3");
            return;
        }

        // initialize flat array
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        int[] resultA = bfs(1, n, new int[n + 1]); // passing dummy parent
        int a = resultA[0];

        int[] parent = new int[n + 1];
        int[] resultB = bfs(a, n, parent);
        int b = resultB[0];
        int diameter = resultB[1];

        int[] q = new int[n];
        int front = 0, end = -1;

        // find third point i.e. 'C'
        int[] distFromPath = new int[n + 1]; // dist from nodes in path (a -> b) to remaining nodes
        Arrays.fill(distFromPath, -1);

        // multi-source BFS
        int curr = b;
        while (curr != 0) {
            q[++end] = curr;
            distFromPath[curr] = 0;
            curr = parent[curr];
        }

        int c = -1;
        int branch = 0;
        while (front <= end) {
            int u = q[front++];

            if (u != a && u != b && branch < distFromPath[u]) {
                branch = distFromPath[u];
                c = u;
            }

            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                if (distFromPath[v] == -1) { // unvisited
                    distFromPath[v] = distFromPath[u] + 1;
                    q[++end] = v;
                }
            }
        }

        // Fallback for line graphs where no node exists off the path
        if (c < 0) {
            for (int i = 1; i <= n; ++i) {
                if (i != a && i != b) {
                    c = i;
                    break;
                }
            }
        }

        System.out.println(diameter + branch);
        System.out.println(a + " " + b + " " + c);
    }
}