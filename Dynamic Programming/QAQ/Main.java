public class Main {

    static char[] str;
    static int[][] dp;

    private static int solve(int i, int j, char[] target) {
        if (j == target.length) return 1;
        if (i == str.length) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (str[i] == target[j])
            return dp[i][j] = solve(i + 1, j + 1, target) + solve(i + 1, j, target);
        return dp[i][j] = solve(i + 1, j, target);
    }

    private static int countSubsequences(char[] target) {
        dp = new int[str.length][target.length];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(0, 0, target);
    }

    private static void solve(FastScanner sc) {
        char[] target = { 'Q', 'A', 'Q' };
        str = sc.next().toCharArray();
        System.out.println(countSubsequences(target));
    }
}