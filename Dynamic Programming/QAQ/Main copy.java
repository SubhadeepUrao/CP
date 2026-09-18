public class Main {

    private static int countSubsequences(char[] str, char[] target) {
        int n = str.length;
        int m = target.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
            dp[i][m] = 1;
        }
        Arrays.fill(dp[n], 0);
        dp[n][m] = 1;

        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                dp[i][j] = str[i] == target[j] ? dp[i + 1][j + 1] + dp[i + 1][j] : dp[i + 1][j];
            }
        }
        return dp[0][0];
    }

    private static void solve(FastScanner sc) {
        char[] target = { 'Q', 'A', 'Q' };
        char[] str = sc.next().toCharArray();
        System.out.println(countSubsequences(str, target));
    }
}