public class Main {

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        int[][] dist = new int[n + 1][n + 1];
        for (int u = 1; u <= n; ++u) {
            for (int v = 1; v <= n; ++v) {
                dist[u][v] = sc.nextInt();
            }
        }

        int[] stack = new int[n + 1];
        int top = -1;
        for (int i = 1; i <= n; ++i)
            stack[++top] = sc.nextInt();

        boolean[] active = new boolean[n + 1];
        long[] sums = new long[n];
        while (top >= 0) {
            int added = stack[top];
            active[added] = true;

            // relax paths if shortcut
            for (int u = 1; u <= n; ++u) {
                for (int v = 1; v <= n; ++v) {
                    dist[u][v] = Math.min(dist[u][v], dist[u][added] + dist[added][v]);
                }
            }

            // compute total sum of shortest paths
            long sum = 0;
            for (int u = 1; u <= n; ++u) {
                for (int v = 1; v <= n; ++v) {
                    if (active[u] && active[v])
                        sum += dist[u][v];
                }
            }

            sums[top] = sum;
            --top;
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; ++i)
            res.append(sums[i]).append(i == n - 1 ? "" : ' ');
        System.out.println(res);
    }
}