public class Main {

    static class Event {
        int type, x, y, docId;

        Event(int type, int x, int y, int docId) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.docId = docId;
        }
    }

    static class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; ++i)
                parent[i] = i;
        }

        int find(int i) {
            if (parent[i] == i)
                return i;
            return parent[i] = find(parent[i]);
        }

        void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ)
                parent[rootI] = rootJ;
        }
    }

    static int[] head, to, next;
    static int edgeId = 0;
    static int[] parent;
    static Event[] events;
    static int[] tin, tout;
    static int timer = 0;
    static int[] stackNode, stackEdge;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n, int m) {
        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[n];
        next = new int[n];

        events = new Event[m];
        parent = new int[n + 1];

        tin = new int[n + 1];
        tout = new int[n + 1];

        stackNode = new int[n];
        stackEdge = new int[n];
    }

    private static void dfs(int root, int n) {
        int top = -1;

        stackNode[++top] = root;
        stackEdge[top] = head[root];
        tin[root] = timer++;

        while (top >= 0) {
            int u = stackNode[top];
            int edgeId = stackEdge[top];

            if (edgeId >= 0) {
                int v = to[edgeId];
                stackEdge[top] = next[edgeId];

                parent[v] = u;
                tin[v] = timer++;

                stackNode[++top] = v;
                stackEdge[top] = head[v];
            } else {
                tout[u] = timer++;
                --top;
            }
        }
    }

    // is u ancestor of v?
    private static boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        initialize(n, m);

        // Pass 1: read all events and construct final tree structure
        int[] inDegree = new int[n + 1];
        int docId = 1;
        int dummy = 0;
        for (int i = 0; i < m; ++i) {
            int type = sc.nextInt();
            if (type == 1) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                events[i] = new Event(1, x, y, dummy);
                addEdge(y, x); // y is boss of x
                ++inDegree[x];
            } else if (type == 2) {
                int x = sc.nextInt();

                events[i] = new Event(2, x, dummy, docId++);
            } else {
                int x = sc.nextInt();
                int documentId = sc.nextInt();

                events[i] = new Event(3, x, dummy, documentId);
            }
        }

        // perform dfs on forests
        for (int i = 1; i <= n; ++i)
            if (inDegree[i] == 0)
                dfs(i, n);

        // Pass 2: process events chronologically with DSU
        DSU dsu = new DSU(n);
        StringBuilder res = new StringBuilder();
        int[] docOrigin = new int[docId]; // document starts from origin
        int[] docTop = new int[docId]; // document ascents to top

        for (int i = 0; i < m; ++i) {
            Event event = events[i];
            int type = event.type;

            if (type == 1) {
                int x = event.x;
                int y = event.y;
                dsu.union(x, y);
            } else if (type == 2) {
                int x = event.x;
                int documentId = event.docId;
                docOrigin[documentId] = x;
                docTop[documentId] = dsu.find(x);
            } else {
                int x = event.x;
                int documentId = event.docId;
                int bottom = docOrigin[documentId];
                int top = docTop[documentId];

                if (isAncestor(x, bottom) && isAncestor(top, x))
                    res.append("YES\n");
                else
                    res.append("NO\n");
            }
        }
        System.out.println(res);
    }
}