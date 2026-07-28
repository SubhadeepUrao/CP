public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int[] parent = new int[n + 1];
        int[] childCnt = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            parent[i] = sc.nextInt();
            ++childCnt[parent[i]];
        }

        int[] q = new int[n];
        int front = 0, end = -1;
        for (int i = 1; i <= n; ++i) {
            if (childCnt[i] == 0)
                q[++end] = i;
        }

        int[] subordinates = new int[n + 1];
        while (front <= end) {
            int curr = q[front++];
            int p = parent[curr];

            if (p > 0) { // not the root
                subordinates[p] += subordinates[curr] + 1;
                if (--childCnt[p] == 0)
                    q[++end] = p;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= n; ++i)
            res.append(subordinates[i]).append(' ');

        System.out.println(res);
    }
}