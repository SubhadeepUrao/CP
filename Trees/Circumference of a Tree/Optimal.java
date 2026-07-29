import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // Using Fast Scanner to handle large inputs efficiently
        FastScanner sc = new FastScanner();
        solve(sc);
    }

    private static int[] head, next, to;
    private static int edgeID = 0;

    private static void addEdge(int u, int v) {
        to[edgeID] = v; // destination vertex
        next[edgeID] = head[u];
        head[u] = edgeID++;
    }

    private static int[] bfs(int root, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        int[] q = new int[n + 1];
        int front = 0, back = 0;

        q[back++] = root;
        dist[root] = 0;

        int farthest = root;
        int maxDist = 0;

        while (front <= back) {
            int u = q[front++]; // pop from front

            if (maxDist < dist[u]) {
                maxDist = dist[u];
                farthest = u;
            }

            // iterate through edges in flat array
            for (int edgeID = head[u]; edgeID >= 0; edgeID = next[edgeID]) {
                int v = to[edgeID];
                if (dist[v] == -1) { // unvisited
                    dist[v] = dist[u] + 1;
                    q[back++] = v;
                }
            }
        }

        return new int[] { farthest, maxDist };
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        // Initialize flat graph (1-indexed, double edges)
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

        // PASS 1: find the farthest node from node 1 (assumed root)
        int[] result = bfs(1, n);
        int farthest = result[0];

        // PASS 2: find the diameter from the farthest node
        result = bfs(farthest, n);
        int diameter = result[1];

        System.out.println(diameter * 3);
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