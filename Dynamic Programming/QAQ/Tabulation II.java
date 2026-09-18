public class Main {

    private static int countSubsequences(char[] str, char[] target) {
        int n = str.length;
        int m = target.length;

        int[] dp = new int[m + 1];
        dp[m] = 1;

        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                dp[j] = str[i] == target[j] ? dp[j + 1] + dp[j] : dp[j];
            }
        }
        return dp[0];
    }

    private static void solve(FastScanner sc) {
        char[] target = { 'Q', 'A', 'Q' };
        char[] str = sc.next().toCharArray();
        System.out.println(countSubsequences(str, target));
    }
}