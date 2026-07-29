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

    static int[] head, next, to;
    static int edgeID = 0;

    static void addEdge(int u, int v) {
        to[edgeID] = v;
        next[edgeID] = head[u];
        head[u] = edgeID++;
    }

    static int[] bfs(int root, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        int[] q = new int[n];
        int front = 0, end = 0;

        q[end++] = root;
        dist[root] = 0; // update distance

        int maxDist = 0;
        int farthest = root;

        while (front < end) {
            int u = q[front++];

            if (maxDist < dist[u]) {
                maxDist = dist[u];
                farthest = u;
            }

            for (int edgeID = head[u]; edgeID >= 0; edgeID = next[edgeID]) {
                int v = to[edgeID];
                if (dist[v] == -1) { // unvisited
                    dist[v] = dist[u] + 1;
                    q[end++] = v;
                }
            }
        }

        // farthest node info is packed inside the dist array
        // since node start from 1 to n, node 0 doesn't exist
        dist[0] = farthest;
        return dist;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println(1);
            return;
        }

        head = new int[n + 1];
        Arrays.fill(head, -1);
        next = new int[2 * n];
        to = new int[2 * n];

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        // find the diameter
        int[] dist = bfs(1, n);
        int endpointA = dist[0];

        // find the farthest node from endpoint A
        int[] distA = bfs(endpointA, n);
        int endpointB = distA[0];
        int diameter = distA[endpointB];

        int[] distB = bfs(endpointB, n);

        int[] res = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            // either adding n+1 doesn't create a longer path
            // or adding a path at node i, might change the diameter, that would have node i
            // as one of its endpoints
            res[i] = Math.max(diameter, Math.max(distA[i], distB[i]) + 1);
        }

        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= n; ++i)
            str.append(res[i]).append('\n');

        System.out.println(str);
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