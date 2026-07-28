public class Main {
    private static int n, m;
    private static long k;
    private static long[][] grid;
    private static HashMap<Long, Long>[][] count;
    private static int halflen, remHalflen;
    private static long totalPaths = 0L;

    private static void dfsForward(int i, int j, long currXOR, int steps) {
        currXOR ^= grid[i][j];

        if (steps == halflen) {
            count[i][j].put(currXOR, count[i][j].getOrDefault(currXOR, 0L) + 1);
            return;
        }

        if (i + 1 <= n)
            dfsForward(i + 1, j, currXOR, steps + 1);
        if (j + 1 <= m)
            dfsForward(i, j + 1, currXOR, steps + 1);
    }

    private static void dfsBackward(int i, int j, long currXOR, int steps) {
        if (steps == remHalflen) {
            long target = k ^ currXOR;
            totalPaths += count[i][j].getOrDefault(target, 0L);
            return;
        }

        currXOR ^= grid[i][j];

        if (1 <= i - 1)
            dfsBackward(i - 1, j, currXOR, steps + 1);
        if (1 <= j - 1)
            dfsBackward(i, j - 1, currXOR, steps + 1);
    }

    @SuppressWarnings("unchecked")
    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextLong();

        grid = new long[n + 1][m + 1]; // 1-indexed
        count = new HashMap[n + 1][m + 1];

        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= m; ++j) {
                grid[i][j] = sc.nextLong();
                count[i][j] = new HashMap<>();
            }

        halflen = (n + m - 2) >> 1;
        remHalflen = (n + m - 2) - halflen;

        dfsForward(1, 1, 0, 0);
        dfsBackward(n, m, 0, 0);

        System.out.println(totalPaths);
    }
}