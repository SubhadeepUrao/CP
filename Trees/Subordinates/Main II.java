public class Main {
    private static void dfs(int i) {
        subordinates[i] = 0;

        for (int children : adj[i]) {
            dfs(children);
            subordinates[i] += subordinates[children] + 1;
        }
    }

    private static List<Integer>[] adj;
    private static int[] subordinates;

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i)
            adj[i] = new ArrayList<>();

        for (int i = 2; i <= n; ++i) {
            int boss = sc.nextInt();
            adj[boss].add(i);
        }

        subordinates = new int[n + 1];

        dfs(1);

        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= n; ++i)
            res.append(subordinates[i]).append(' ');

        System.out.println(res);
    }
}