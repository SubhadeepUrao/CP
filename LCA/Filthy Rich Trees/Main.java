public class Main {
    static int[] head, to, next;
    static int edgeId = 0;
    static int[] parent, in, out;
    static double[] val;
    static double[] bit;
    static int timer = 1;
    static final double MAX_VAL = 1_000_000_000.0;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        parent = new int[n + 1];
        in = new int[n + 1];
        out = new int[n + 1];

        val = new double[n + 1];
        Arrays.fill(val, 1.0);

        // Initial value is 1, log10(1) = 0
        bit = new double[n + 1];
    }

    // Fenwick Tree (BIT) operations for point update and range query
    private static void updateBIT(int idx, double delta, int n) {
        while (idx <= n) {
            bit[idx] += delta; // added difference
            idx += idx & -idx;
        }
    }

    private static double queryBIT(int idx) {
        double sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx &= idx - 1;
        }
        return sum;
    }

    private static double queryRange(int l, int r) {
        return queryBIT(r) - queryBIT(l - 1);
    }

    private static void dfs(int n) {
        int[] stackNode = new int[n + 1];
        int[] stackEdge = new int[n + 1];
        int top = -1;

        int root = 1;
        stackNode[++top] = root;
        stackEdge[top] = head[root];
        in[root] = timer++;

        while (top >= 0) {
            int u = stackNode[top];
            int e = stackEdge[top];

            if (e >= 0) {
                int v = to[e];
                stackEdge[top] = next[e];

                if (v != parent[u]) {
                    parent[v] = u;
                    in[v] = timer++;
                    stackNode[++top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                // Every descendant v of u satisfies: in[u] <= in[v] <= out[u]
                out[u] = timer - 1;
                --top;
            }
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        
        if (n == 0) return;

        initialize(n);

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        dfs(n);

        StringBuilder res = new StringBuilder();
        int q = sc.nextInt();

        while (q-- > 0) {
            int type = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (type == 1) {
                // Update node value
                double oldLog = Math.log10(val[x]);
                double newLog = Math.log10(y);
                val[x] = y;
                updateBIT(in[x], newLog - oldLog, n);
            } else {
                // Calculate ratio using log properties: log10(ans) = log10(subtree_x) -
                // log10(subtree_y)
                double logSubtreeX = queryRange(in[x], out[x]);
                double logSubtreeY = queryRange(in[y], out[y]);

                double diff = logSubtreeX - logSubtreeY;
                if (diff >= 9.0) // Math.log10(1,000,000,000) = 9.0
                    res.append(1000000000).append('\n');
                else {
                    double ans = Math.pow(10, diff);
                    if (ans >= MAX_VAL)
                        res.append(1000000000).append('\n');
                    else
                        res.append(String.format("%.10f", ans)).append('\n');

                }
            }
        }
        System.out.print(res);
    }
}