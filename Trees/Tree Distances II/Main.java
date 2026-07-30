public class Main {

    private static int[] head, next, to;
    private static int edgeID = 0;
    private static int[] preorder, parent;

    private static void addEdge(int u, int v) {
        to[edgeID] = v;
        next[edgeID] = head[u];
        head[u] = edgeID++;
    }

    private static long computeTreeDistances(int root, int n) {
        int[] dist = new int[n + 1];
        long totalDist = 0L;

        int[] stack = new int[n];
        int top = -1;

        stack[++top] = root;
        parent = new int[n + 1];
        preorder = new int[n];
        int j = 0;
        while (top >= 0) {
            int u = stack[top--];
            preorder[j++] = u;

            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                if (parent[u] != v) {
                    parent[v] = u;
                    stack[++top] = v;

                    dist[v] = dist[u] + 1; // update dist of v from root
                    totalDist += dist[v]; // update total sum of distances of the tree
                }
            }
        }
        return totalDist;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

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

        long totalDist = computeTreeDistances(1, n);

        // compute subordinates
        int[] subordinates = new int[n + 1];
        for (int i = n - 1; i > 0; --i) {
            int u = preorder[i];
            int p = parent[u];
            subordinates[p] += subordinates[u] + 1;
        }

        long[] res = new long[n + 1];
        res[1] = totalDist;

        for (int i = 1; i < n; ++i) {
            int u = preorder[i];
            int p = parent[u];
            long subtree = subordinates[u] + 1; // total nodes in the subtree rooted at i
            // totalDist - subtree + (n - subtree)
            res[u] = res[p] + n - (subtree << 1);
        }

        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= n; ++i) {
            str.append(res[i]).append(' ');
        }
        System.out.println(str);
    }
}