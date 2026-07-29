import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // Using Fast Scanner to handle large inputs efficiently
        FastScanner sc = new FastScanner();
        solve(sc);
    }

    private static List<Integer>[] adj;
    private static int matching = 0;

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i)
            adj[i] = new ArrayList<>();

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        int[][] dp = new int[n + 1][2];
        int[] parent = new int[n + 1];
        int[] childCnt = new int[n + 1];

        int[] stack = new int[n];
        stack[0] = 1; // root node
        int top = 0;
        while (top >= 0) {
            int u = stack[top--];
            for (int v : adj[u]) {
                if (parent[u] != v) {
                    parent[v] = u;
                    ++childCnt[u];
                    stack[++top] = v;
                }
            }
        }

        int[] q = new int[n];
        int front = 0, back = -1;
        for (int i = 1; i <= n; ++i) {
            if (childCnt[i] == 0)
                q[++back] = i;
        }

        while (front <= back) {
            int u = q[front++];

            int maxSumChild = 0;
            for (int v : adj[u]) {
                if (parent[u] != v) {
                    maxSumChild += Math.max(dp[v][0], dp[v][1]);
                }
            }

            dp[u][0] = maxSumChild;

            for (int v : adj[u]) {
                if (parent[u] != v) {
                    dp[u][1] = Math.max(dp[u][1], 1 + dp[v][0] + (dp[u][0] - Math.max(dp[v][0], dp[v][1])));
                }
            }

            int p = parent[u];
            if (p > 0) {
                --childCnt[p];
                if (childCnt[p] == 0)
                    q[++back] = p;
            }

        }

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    // Fast I/O Utility Class
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        boolean hasNext() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return false;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return false;
                }
            }
            return true;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}