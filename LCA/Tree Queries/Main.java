public class Main {
    
    static int[] head, to, next;
    static int edgeId = 0;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] in_time = new int[n + 1];
        int[] out_time = new int[n + 1];
        int timer = 0;

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

        int[] parent = new int[n + 1];
        int[] depth = new int[n + 1];

        // run DFS
        int[] stackNode = new int[n];
        int[] stackEdge = new int[n];
        int top = -1;

        int root = 1;
        stackNode[++top] = root; // root
        stackEdge[top] = head[root];
        in_time[root] = timer++;
        parent[root] = root;

        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = to[edgeId];
                stackEdge[top] = next[edgeId];

                if (parent[u] != v) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    in_time[v] = timer++;

                    ++top;
                    stackNode[top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                out_time[u] = timer++;
                --top;
            }
        }

        StringBuilder res = new StringBuilder();

        while (m-- > 0) {
            int k = sc.nextInt();
            int[] nodes = new int[k];
            int deep = 1;

            for (int i = 0; i < k; ++i) {
                int node = parent[sc.nextInt()];
                nodes[i] = node;

                if (depth[deep] < depth[node]) {
                    deep = node;
                }
            }

            boolean possible = true;
            for (int node : nodes) {
                // ancestor of the deepest node will always contain in-out time of the deepest node
                if (!(in_time[node] <= in_time[deep] && out_time[deep] <= out_time[node])) {
                    possible = false;
                    break;
                }
            }

            if (possible)
                res.append("YES\n");
            else
                res.append("NO\n");
        }
        System.out.println(res);
    }
}