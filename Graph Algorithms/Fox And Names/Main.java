public class Main {

    static int[] head, to, next;
    static int edgeId;
    static int[] inDegree;
    static char[][] names;

    private static void addEdge(int u, int v) {
        to[edgeId] = v;
        next[edgeId] = head[u];
        head[u] = edgeId++;
    }

    private static void initialize(int n) {
        edgeId = 0;
        head = new int[26];
        Arrays.fill(head, -1);
        to = new int[n];
        next = new int[n];

        inDegree = new int[26];
        names = new char[n][];
    }

    private static boolean genGraph(int n) {
        for (int i = 1; i < n; ++i) {
            char[] str1 = names[i - 1];
            char[] str2 = names[i];

            int len = Math.min(str1.length, str2.length);
            boolean foundDiff = false;

            for (int j = 0; j < len; ++j) {
                char first = str1[j];
                char second = str2[j];
                if (first != second) {
                    addEdge(first - 'a', second - 'a');
                    ++inDegree[second - 'a'];
                    foundDiff = true;
                    break;
                }
            }

            if (!foundDiff && str1.length > str2.length)
                return false;
        }
        return true;
    }

    private static void topoSort(StringBuilder res) {
        int[] q = new int[26];
        int front = 0, back = -1;

        for (int i = 0; i < 26; ++i)
            if (inDegree[i] == 0)
                q[++back] = i;

        while (front <= back) {
            int u = q[front++];
            res.append((char) (u + 'a'));

            for (int edgeId = head[u]; edgeId >= 0; edgeId = next[edgeId]) {
                int v = to[edgeId];
                --inDegree[v];
                if (inDegree[v] == 0) {
                    q[++back] = v;
                }
            }
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        initialize(n);

        for (int i = 0; i < n; ++i) {
            names[i] = sc.next().toCharArray();
        }

        StringBuilder res = new StringBuilder();

        // generate graph
        if (!genGraph(n)) {
            System.out.println("Impossible");
            return;
        }

        // topological sort
        topoSort(res);

        if (res.length() == 26)
            System.out.println(res);
        else
            System.out.println("Impossible");
    }
}