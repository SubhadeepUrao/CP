public class Main {
    private static int getCost(int originalMask, int currMask) {
        return Integer.bitCount(originalMask ^ currMask);
    }

    private static boolean isValidSubMatrix(int prevMask, int currMask, int m) {
        int diff = prevMask ^ currMask;
        if (m == 2) { // only single block of 2X2
            return (Integer.bitCount(diff) & 1) == 1; // odd
        }
        // m == 3
        int leftblock = (diff & 1) ^ ((diff >> 1) & 1);
        int rightblock = ((diff >> 1) & 1) ^ ((diff >> 2) & 1);
        return leftblock == 1 && rightblock == 1;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] grid = new char[n][m];
        for (int i = 0; i < n; ++i) {
            grid[i] = sc.next().toCharArray();
        }

        if (n >= 4 && m >= 4) {
            System.out.println(-1);
            return;
        }
        if (n == 1 || m == 1) {
            System.out.println(0);
            return;
        }

        if (n < m) {
            char[][] transpose = new char[m][n];
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    transpose[i][j] = grid[j][i];

            grid = transpose;
            int temp = m;
            m = n;
            n = temp;
        }

        int[] originalMasks = new int[n];
        for (int i = 0; i < n; ++i) {
            int mask = 0;
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == '1') {
                    mask |= 1 << j;
                }
            }
            originalMasks[i] = mask;
        }

        int totalMask = 1 << m; // total possible masks for a row
        int[] prevMask = new int[totalMask];
        for (int mask = 0; mask < totalMask; ++mask)
            prevMask[mask] = getCost(originalMasks[0], mask);

        int[] currMask = new int[totalMask];
        int MAX = 1_000_001;
        for (int i = 1; i < n; ++i) {
            Arrays.fill(currMask, MAX);
            int original = originalMasks[i];
            for (int curr = 0; curr < totalMask; ++curr) {
                int cost = getCost(original, curr);
                for (int prev = 0; prev < totalMask; ++prev)
                    if (isValidSubMatrix(prev, curr, m))
                        currMask[curr] = Math.min(currMask[curr], prevMask[prev] + cost);
            }
            int[] temp = prevMask;
            prevMask = currMask;
            currMask = temp;
        }

        int minFlips = MAX;
        for (int mask = 0; mask < totalMask; ++mask)
            minFlips = Math.min(minFlips, prevMask[mask]);
        System.out.println(minFlips < MAX ? minFlips : -1);
    }
}