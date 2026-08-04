import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] head, to, next;
    static int edgeId = 0;
    static int[] parent, depth;
    static int MAX_JUMPS;
    static int[][] ancestor;
    static List<Integer>[] depthNodes;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initializeVar(int n) {
        MAX_JUMPS = 31 - Integer.numberOfLeadingZeros(n);
        ancestor = new int[n + 1][MAX_JUMPS + 1];
        parent = new int[n + 1];
        depth = new int[n + 1];
        depthNodes = new ArrayList[n + 1];
        for (int d = 0; d <= n; ++d) {
            depthNodes[d] = new ArrayList<>();
        }

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];
    }

    private static int getKthAncestor(int u, int k) {
        for (int j = 0; j <= MAX_JUMPS; ++j) {
            if (((k >> j) & 1) == 1) // jth bit is set
                u = ancestor[u][j];
        }
        return u;
    }

    private static int lowerBound(List<Integer> list, int target) {
        int lb = 0, ub = list.size() - 1;
        while (lb <= ub) {
            int mid = (lb + ub) >>> 1;
            if (list.get(mid) < target)
                lb = mid + 1;
            else
                ub = mid - 1;
        }
        return lb;
    }

    private static int upperBound(List<Integer> list, int target) {
        int lb = 0, ub = list.size() - 1;
        while (lb <= ub) {
            int mid = (lb + ub) >>> 1;
            if (target < list.get(mid))
                ub = mid - 1;
            else
                lb = mid + 1;
        }
        return lb;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        List<Integer> roots = new ArrayList<>();

        initializeVar(n);

        for (int i = 1; i <= n; ++i) {
            int curr = sc.nextInt();
            addEdge(curr, i);
            addEdge(i, curr);

            parent[i] = curr;
            if (curr == 0)
                roots.add(i);
        }

        int[] inTime = new int[n + 1];
        int[] outTime = new int[n + 1];
        int timer = 0;

        // perform DFS for every root
        int[] stackNode = new int[n];
        int[] stackEdge = new int[n];
        int top = -1;

        for (int root : roots) {
            stackNode[++top] = root;
            stackEdge[top] = head[root];
            inTime[root] = timer++;
            depthNodes[0].add(inTime[root]);

            while (top >= 0) {
                int u = stackNode[top];
                int edgeId = stackEdge[top];

                // populate the ancestor table for node u
                ancestor[u][0] = parent[u];
                for (int j = 1; j <= MAX_JUMPS; ++j)
                    ancestor[u][j] = ancestor[ancestor[u][j - 1]][j - 1];

                if (edgeId >= 0) {
                    int v = to[edgeId];
                    stackEdge[top] = next[edgeId];

                    if (parent[v] == u) {
                        inTime[v] = timer++;
                        depth[v] = depth[u] + 1;
                        depthNodes[depth[v]].add(inTime[v]);
                        ++top;
                        stackNode[top] = v;
                        stackEdge[top] = head[v];
                    }
                } else {
                    outTime[u] = timer++;
                    --top;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        int m = sc.nextInt();
        while (m-- > 0) {
            int v = sc.nextInt();
            int p = sc.nextInt();

            if (depth[v] < p) {
                res.append("0 ");
                continue;
            }

            // u is the ancestor of v
            int u = getKthAncestor(v, p);
            int targetDepth = depth[v];

            int l = lowerBound(depthNodes[targetDepth], inTime[u]);
            int r = upperBound(depthNodes[targetDepth], outTime[u]);

            res.append(r - l - 1).append(' '); // exclude v
        }
        System.out.println(res);
    }
}