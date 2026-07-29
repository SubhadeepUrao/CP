// TLE

public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i)
            adj[i] = new ArrayList<>();

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            adj[u].add(v);
            adj[v].add(u);
        }

        int[] parent = new int[n + 1];

        int[] stackOne = new int[n];
        int topOne = 0;
        int[] stackTwo = new int[n]; // stores post order sequence
        int topTwo = -1;

        stackOne[0] = 1; // root node
        while (topOne >= 0) {
            int u = stackOne[topOne--];
            stackTwo[++topTwo] = u;

            for (int v : adj[u]) {
                if (parent[u] != v) {
                    parent[v] = u;
                    stackOne[++topOne] = v;
                }
            }
        }

        int ans = 0;
        int[] maxlenParent = new int[n + 1];
        Arrays.fill(maxlenParent, 1);
        maxlenParent[0] = 0;

        while (topTwo >= 0) {
            int u = stackTwo[topTwo--];
            for (int v : adj[u]) {
                if (parent[u] != v) {
                    ans = Math.max(ans, maxlenParent[u] + maxlenParent[v]);
                    maxlenParent[u] = Math.max(maxlenParent[u], maxlenParent[v] + 1);
                }
            }
        }

        int diameter = ans - 1;
        System.out.println(diameter * 3);
    }
}