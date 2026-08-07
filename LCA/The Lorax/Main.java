public class Main {

    static int[] head, to, next;
    static int edgeId;
    static int[] parent, depth, in, out;
    // static int timer = 1; // to comply with fenwick tree
    static int timer;
    static long[] bit; // { + for seeds, - for pots}
    static int[] stackNode, stackEdge;
    static int top = -1;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n) {
        edgeId = 0;
        timer = 0;

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        parent = new int[n + 1];
        depth = new int[n + 1];
        in = new int[n + 1];
        out = new int[n + 1];

        bit = new long[n + 1];

        stackNode = new int[n];
        stackEdge = new int[n];
    }

    private static void dfs(int n) {
        int root = 1;
        stackNode[++top] = root;
        stackEdge[top] = head[root];
        // in[root] = timer++;
        in[root] = ++timer;

        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = to[edgeId];
                stackEdge[top] = next[edgeId];
                if (parent[u] != v) { // explore v
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    // in[v] = timer++;
                    in[v] = ++timer;

                    stackNode[++top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                // out[u] = timer - 1;
                out[u] = timer;
                --top;
            }
        }
    }

    private static void updateBIT(int idx, long updateVal, int n) {
        while (idx <= n) {
            bit[idx] += updateVal;
            idx += idx & -idx; // add LSB
        }
    }

    private static long queryBIT(int idx) {
        long sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx &= idx - 1; // remove LSB
        }
        return sum;
    }

    private static long queryRange(int l, int r) {
        return queryBIT(r) - queryBIT(l - 1);
    }

    private static void solve(FastScanner sc) {
        int c = sc.nextInt();

        StringBuilder res = new StringBuilder();

        while (c-- > 0) {
            int n = sc.nextInt();
            int q = sc.nextInt();

            initialize(n);

            for (int i = 1; i < n; ++i) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                addEdge(u, v);
                addEdge(v, u);
            }

            dfs(n);

            while (q-- > 0) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int x = sc.nextInt();

                if (x > 0) { // update
                    updateBIT(in[a], x, n); // update seeds i.e. +X
                    updateBIT(in[b], -x, n); // update pots i.e. -X
                } else { // query
                    int child = depth[a] > depth[b] ? a : b;
                    long ans = Math.abs(queryRange(in[child], out[child]));
                    res.append(ans).append('\n');
                }
            }
        }
        System.out.println(res);
    }
}