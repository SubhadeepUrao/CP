public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[] parent, catsSeen;
    static boolean[] hasChild, hasCat;

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
        hasChild = new boolean[n + 1];

        hasCat = new boolean[n + 1];
        catsSeen = new int[n + 1];
    }

    private static int countReachableRestaurants(int n, int m) {
        int[] stackNode = new int[n];
        int[] stackEdge = new int[n];
        int top = -1;

        int reachable = 0;
        int root = 1;
        stackNode[++top] = root;
        stackEdge[top] = head[root];
        catsSeen[root] = hasCat[root] ? 1 : 0;

        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = to[edgeId];
                stackEdge[top] = next[edgeId];

                if (parent[u] != v) { // explore v
                    parent[v] = u;
                    hasChild[u] = true;

                    catsSeen[v] = hasCat[v] ? catsSeen[u] + 1 : 0;
                    if (catsSeen[v] > m) continue;

                    stackNode[++top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                if (u != root && !hasChild[u]) {
                    ++reachable;
                }
                --top;
            }
        }
        return reachable;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        initialize(n);

        for (int i = 1; i <= n; ++i) {
            hasCat[i] = sc.nextInt() == 0 ? false : true;
        }

        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        System.out.println(countReachableRestaurants(n, m));
    }
}