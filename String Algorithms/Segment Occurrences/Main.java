public class Main {
    private static void modifiedKMP(char[] str, char[] pattern, int[] isStart) {
        int n = str.length;
        int m = pattern.length;

        if (m > n) return;

        // compute LPS array
        int[] LPS = new int[m];
        LPS[0] = 0;
        int i = 1;
        int len = 0;
        while (i < m) {
            if (pattern[len] == pattern[i]) {
                LPS[i] = ++len;
                ++i;
            } else {
                if (len > 0)
                    len = LPS[len - 1];
                else {
                    LPS[i] = 0;
                    ++i;
                }
            }
        }

        // string matching
        i = 0;
        int j = 0;
        while (i < n) {
            if (str[i] == pattern[j]) {
                ++i;
                ++j;
            }
            if (j == m) {
                isStart[i - m + 1] = 1;
                j = LPS[j - 1];
            } else if (i < n && str[i] != pattern[j]) {
                if (j > 0)
                    j = LPS[j - 1];
                else
                    ++i;
            }
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        char[] s = sc.next().toCharArray();
        char[] t = sc.next().toCharArray();

        // 1-based indexing
        int[] isStart = new int[n + 1];
        modifiedKMP(s, t, isStart);

        // build prefix sum
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            prefixSum[i] = prefixSum[i - 1] + isStart[i];
        }

        StringBuilder res = new StringBuilder();
        while (q-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();

            // l <= i <= (r - m + 1)
            int validRightBound = r - m + 1;
            if (l > validRightBound)
                res.append("0\n");
            else
                res.append(prefixSum[validRightBound] - prefixSum[l - 1]).append('\n');
        }

        System.out.println(res);
    }
}