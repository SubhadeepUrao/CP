public class Main {
    private static void initHash() {
        Random rand = new Random(1968);
        for (int i = 0; i < 26; ++i)
            hash[i] = rand.nextLong();
    }

    private static long[] hash = new long[26];

    private static void solve(FastScanner sc) {

        initHash();

        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] grid = new char[n][m];
        for (int i = 0; i < n; ++i)
            grid[i] = sc.next().toCharArray();

        long totalBeautiful = 0;

        // fix the left boundary column
        for (int colLeft = 0; colLeft < m; ++colLeft) {
            int[][] cnt = new int[n][26];
            int[] oddCnt = new int[n]; // tracks odd count for each row
            long[] rowHash = new long[n]; // stores hash sum

            // expand the right boundary column
            for (int col = colLeft; col < m; ++col) {
                for (int row = 0; row < n; ++row) {
                    int curr = grid[row][col] - 'a';

                    ++cnt[row][curr];
                    rowHash[row] += hash[curr];

                    if (cnt[row][curr] % 2 == 0) --oddCnt[row];
                    else ++oddCnt[row];
                }

                // build 1D array for Manacher's Algo
                int t = 2 * n + 1;
                long[] T = new long[t];
                long INVALID = -1L;
                T[0] = 0; // Delimiter '#'
                for (int i = 0; i < n; ++i) {
                    T[2 * i + 1] = oddCnt[i] <= 1 ? rowHash[i] : INVALID;
                    T[2 * i + 2] = 0; // Delimiter '#'
                }

                int[] P = new int[t];
                P[0] = 0;
                int l = 0, r = 0;

                for (int i = 1; i < t; ++i) {
                    int k;
                    if (r < i) {
                        k = 0;
                    } else {
                        int j = l + r - i; // mirror image of i within boundary [l, r]
                        k = Math.min(P[j], r - i);
                    }

                    while (0 <= i - k && i + k < t) {
                        if (T[i - k] == INVALID || T[i + k] == INVALID) break;
                        if (T[i - k] != T[i + k]) break;
                        ++k;
                    }
                    --k;
                    P[i] = k;

                    // update boundary
                    if (r < i + k) {
                        l = i - k;
                        r = i + k;
                    }

                    totalBeautiful += (k + 1) / 2;
                }
            }

        }
        System.out.println(totalBeautiful);
    }
}