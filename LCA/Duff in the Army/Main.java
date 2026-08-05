public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[][] ancestor, people;
    static int MAX_JUMPS;
    static int[] parent, depth, peopleSize;
    static int[][][] val;
    static final int MAX_A = 10;
    static final int[] BUFFER = new int[MAX_A];

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

        MAX_JUMPS = 31 - Integer.numberOfLeadingZeros(n);
        ancestor = new int[n + 1][MAX_JUMPS + 1];
        val = new int[n + 1][MAX_JUMPS + 1][];

        people = new int[n + 1][MAX_A];
        peopleSize = new int[n + 1];

        parent = new int[n + 1];
        depth = new int[n + 1];
    }

    private static void finalizePeople(int n) {
        for (int i = 1; i <= n; ++i) {
            if (peopleSize[i] < MAX_A)
                people[i] = Arrays.copyOf(people[i], peopleSize[i]);
        }
    }

    private static int[] combine(int[] A, int[] B) {
        int sizeA = A.length;
        int sizeB = B.length;
        int targetSize = Math.min(sizeA + sizeB, MAX_A);

        int i = 0, j = 0, k = 0;
        int[] res = new int[targetSize];
        while (i < sizeA && j < sizeB && k < targetSize) {
            if (A[i] <= B[j]) res[k++] = A[i++];
            else res[k++] = B[j++];
        }

        while (i < sizeA && k < targetSize) res[k++] = A[i++];
        while (j < sizeB && k < targetSize) res[k++] = B[j++];

        return Arrays.copyOf(res, k);
    }

    private static int[] query(int u, int v) {
        int[] res = new int[] {};

        if (u == v) return people[u];

        if (depth[u] < depth[v]) { int temp = u; u = v; v = temp; }

        // lift u to the depth of v
        int k = depth[u] - depth[v];
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((k >> j) & 1) == 1) {
                res = combine(res, val[u][j]);
                u = ancestor[u][j];
            }
        }

        if (u == v) return combine(res, people[u]);

        for (int j = MAX_JUMPS; j >= 0; --j) {
            if (ancestor[u][j] != ancestor[v][j]) {
                res = combine(res, val[u][j]);
                res = combine(res, val[v][j]);
                u = ancestor[u][j];
                v = ancestor[v][j];
            }
        }

        res = combine(res, people[u]); // combine node u
        res = combine(res, people[v]); // combine node v
        return combine(res, people[parent[u]]);
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        initialize(n);

        // read tree structure
        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }

        // read node's people are associated to
        for (int i = 1; i <= m; ++i) {
            int c = sc.nextInt();
            int size = peopleSize[c];
            if (size < MAX_A) {
                people[c][size] = i;
                ++peopleSize[c];
            }
        }
        finalizePeople(n);

        // perform DFS
        int[] stackNode = new int[n];
        int[] stackEdge = new int[n];
        int top = -1;

        int root = 1;
        stackNode[++top] = root;
        stackEdge[top] = head[root];
        
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            ancestor[root][j] = 0;
            val[root][j] = people[root];
        }

        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = to[edgeId];
                stackEdge[top] = next[edgeId];

                if (parent[u] != v) { // explore v
                    parent[v] = u;
                    depth[v] = depth[u] + 1;

                    ancestor[v][0] = u;
                    val[v][0] = people[v];
                    for (int j = 1; j <= MAX_JUMPS; ++j) {
                        int anc = ancestor[v][j - 1];
                        ancestor[v][j] = ancestor[anc][j - 1];
                        if(anc > 0)
                            val[v][j] = combine(val[v][j - 1], val[anc][j - 1]);
                        else
                            val[v][j] = val[v][j - 1];
                    }

                    stackNode[++top] = v;
                    stackEdge[top] = head[v];
                }
            } else {
                --top;
            }
        }

        StringBuilder res = new StringBuilder();
        while (q-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int a = sc.nextInt();

            int[] ans = query(u, v);

            int k = Math.min(ans.length, a);

            res.append(k);
            for (int i = 0; i < k; ++i)
                res.append(' ').append(ans[i]);
            res.append('\n');
        }

        System.out.println(res);
    }
}