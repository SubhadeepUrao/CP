public class Main {
    private static void solve(FastScanner sc) {
        long BASE = 131;
        long MOD = 1_000_000_007l;

        char[] str = sc.next().toCharArray();
        int n = str.length;

        int[] dp = new int[n + 1];
        long forwardHash = 0;
        long backwardHash = 0;
        long power = 1;

        int degree = 0;

        for (int i = 1; i <= n; ++i) {
            char ch = str[i - 1];

            // update forward hash
            forwardHash = (forwardHash * BASE + ch) % MOD;

            // update backward hash
            backwardHash = (backwardHash + ch * power) % MOD;
            power = (BASE * power) % MOD;

            if (forwardHash == backwardHash)
                dp[i] = dp[i / 2] + 1;

            degree += dp[i];
        }

        System.out.println(degree);
    }
}