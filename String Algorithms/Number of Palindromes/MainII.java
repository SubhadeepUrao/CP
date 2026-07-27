class Main {
    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        int n = str.length;

        int count = 0;
        boolean[][] dp = new boolean[n + 1][n + 1];

        for (int l = 1; l <= n; ++l) {
            for (int i = 0; i + l - 1 < n; ++i) {
                int j = i + l - 1;

                if (i == j)
                    dp[i][i] = true;
                else if (i + 1 == j)
                    dp[i][j] = str[i] == str[j];
                else {
                    dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1];
                }

                if (dp[i][j])
                    ++count;
            }
        }

        System.out.println(count);
    }
}