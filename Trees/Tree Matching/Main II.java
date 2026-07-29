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

    static void dfs(int u, int parent) {
        for (int v : adj[u]) {
            if (v != parent) {
                dfs(v, u);
                if (!vis[u] && !vis[v]) {
                    vis[u] = vis[v] = true;
                    ++matching;
                }
            }
        }
    }

    private static boolean[] vis;
    private static List<Integer>[] adj;
    private static int matching = 0;

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        vis = new boolean[n + 1];
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i)
            adj[i] = new ArrayList<>();

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(1, 0);

        System.out.println(matching);
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