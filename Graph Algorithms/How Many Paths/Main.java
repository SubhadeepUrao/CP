public class Main {

    static class DirectedGraph {
        int[] head, to, next;
        int edgeId = 0;
        int[] path;
        int n, m;

        DirectedGraph(int n, int m) {
            this.n = n;
            this.m = m;

            head = new int[n + 1];
            Arrays.fill(head, -1);
            to = new int[m];
            next = new int[m];

            path = new int[n + 1]; // path[i] stores path from 1 to i
        }

        void addEdge(int u, int v) {
            to[edgeId] = v;
            next[edgeId] = head[u];
            head[u] = edgeId++;
        }

        void propagateInfinte(int root, boolean[] visited) {
            int[] stackEdge = new int[n];
            int top = -1;

            stackEdge[++top] = head[root];
            visited[root] = true;
            path[root] = -1;

            while (top >= 0) {
                int e = stackEdge[top];

                if (e >= 0) {
                    int v = to[e];
                    stackEdge[top] = next[e];

                    if (!visited[v]) {
                        path[v] = -1;
                        visited[v] = true;
                        stackEdge[++top] = head[v];
                    }
                } else {
                    --top;
                }
            }
        }

        void propagateDownwards() {
            boolean[] visited = new boolean[n + 1];
            for (int i = 1; i <= n; ++i) {
                if (path[i] == -1 && !visited[i]) {
                    propagateInfinte(i, visited);
                }
            }
        }

        void detectCycle() {
            int root = 1;
            int[] state = new int[n + 1]; // 0: unvisited, 1: visiting, 2: visited

            path[root] = 1;

            int[] stackNode = new int[n];
            int[] stackEdge = new int[n];
            int top = -1;

            stackNode[++top] = root;
            stackEdge[top] = head[root];
            state[root] = 1; // root set to visiting

            while (top >= 0) {
                int u = stackNode[top];
                int edgeId = stackEdge[top];

                if (edgeId >= 0) {
                    int v = to[edgeId];
                    stackEdge[top] = next[edgeId];

                    if (state[v] == 0) { // explore v
                        state[v] = 1; // visiting
                        stackNode[++top] = v;
                        stackEdge[top] = head[v];
                    } else if (state[v] == 1) // back-edge
                        path[v] = -1;

                } else {
                    state[u] = 2; // fully processed node
                    --top;
                }
            }
        }

        void countPaths() {
            int root = 1;
            if (path[root] == -1)
                return;

            int[] stack = new int[n];
            int top = -1;

            stack[++top] = root;
            path[root] = 1;

            while (top >= 0) {
                int u = stack[top--];

                for (int e = head[u]; e >= 0; e = next[e]) {
                    int v = to[e];

                    if (path[v] == -1)
                        continue;

                    // push v only if path state changes
                    if (path[v] == 0) {
                        path[v] = path[u];
                        stack[++top] = v;
                    } else if (path[v] == 1) {
                        path[v] = 2;
                        stack[++top] = v;
                    } else if (path[u] == 2 && path[v] < 2) {
                        path[v] = 2;
                        stack[++top] = v;
                    }
                }
            }
        }
    }

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            DirectedGraph graph = new DirectedGraph(n, m);
            for (int i = 1; i <= m; ++i) {
                int u = sc.nextInt();
                int v = sc.nextInt();

                graph.addEdge(u, v);
            }

            graph.detectCycle();

            // propagate -1 downwards
            graph.propagateDownwards();

            // count paths
            graph.countPaths();

            for (int i = 1; i <= n; ++i) {
                res.append(graph.path[i]).append(' ');
            }
            res.append('\n');
        }
        System.out.println(res);
    }
}