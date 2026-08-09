public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[] color;
    static boolean[] visited;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n, int m) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * m];
        next = new int[2 * m];

        visited = new boolean[n + 1];
        color = new int[n + 1];
    }

    private static boolean color2Graph(int n) {
        // perform dfs
        int[] stack = new int[n];
        int top = -1;

        int root = 1;
        stack[++top] = root;
        color[root] = 0; // root is assigned as source node
        visited[root] = true;

        while (top >= 0) {
            int u = stack[top--];
            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                if (!visited[v]) {
                    visited[v] = true;
                    color[v] = 1 - color[u];
                    stack[++top] = v;
                }
                if (color[u] == color[v])
                    return false;
            }
        }
        return true;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        initialize(n, m);

        for (int i = 1; i <= m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            addEdge(u, v);
            addEdge(v, u);
        }

        StringBuilder res = new StringBuilder();
        if (color2Graph(n)) {
            res.append("YES").append('\n');
            for (int e = 0; e < edgeId; e += 2) {
                int v = to[e];
                if (color[v] == 1) // u is source node; v is sink node
                    res.append(0);
                else
                    res.append(1);
            }
        } else
            res.append("NO");

        System.out.println(res);
    }
}