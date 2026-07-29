public class Main {

    private static int[] head, next, to;
    private static int edgeID = 0;

    private static void addEdge(int u, int v) {
        to[edgeID] = v;
        next[edgeID] = head[u];
        head[u] = edgeID++;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int q = sc.nextInt();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        int[] parent = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            parent[i] = sc.nextInt();
            addEdge(parent[i], i);
        }

        // compute preorder sequence
        int[] preorder = new int[n + 1];
        int[] mpp = new int[n + 1];
        int[] stack = new int[n];
        int top = -1;
        stack[++top] = 1;
        int j = 0;
        while (top >= 0) {
            int u = stack[top--];
            preorder[j] = u;
            mpp[u] = j++;

            for (int edgeID = head[u]; edgeID >= 0; edgeID = next[edgeID]) {
                int v = to[edgeID];
                stack[++top] = v;
            }
        }

        // compute subordinates
        int[] subordinates = new int[n + 1];

        for (int i = j - 1; i > 0; --i) {
            int u = preorder[i];
            int p = parent[u];
            subordinates[p] += subordinates[u] + 1;
        }

        StringBuilder res = new StringBuilder();
        while (q-- > 0) {
            int u = sc.nextInt();
            int k = sc.nextInt();

            int totalSub = subordinates[u];
            int idx = mpp[u];
            if (k <= totalSub + 1) {
                res.append(preorder[idx + k - 1]).append('\n');
            } else
                res.append(-1).append('\n');
        }
        System.out.println(res);
    }
}