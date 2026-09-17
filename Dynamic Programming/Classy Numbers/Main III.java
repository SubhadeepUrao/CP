public class Main {
    
    // dp[remainingLength][count] — remainingLength = digits left to place (length-invariant).
    static long[][] dp = new long[20][5];

    private static long countClassy(int idx, int count, boolean tight, String s) {
        if (count > 3) {
            return 0;
        }
        if (idx == s.length()) {
            return 1;
        }

        int remain = s.length() - idx;

        if (!tight && dp[remain][count] != -1) {
            return dp[remain][count];
        }

        long ans = 0;
        int limit = tight ? (s.charAt(idx) - '0') : 9;

        for (int digit = 0; digit <= limit; digit++) {
            boolean nextTight = tight && (digit == limit);
            int nextCount = count + (digit != 0 ? 1 : 0);
            ans += countClassy(idx + 1, nextCount, nextTight, s);
        }

        if (!tight) {
            dp[remain][count] = ans;
        }

        return ans;
    }

    private static long solve(long n) {
        if (n < 0) return 0;          // only negative n is invalid; n == 0 is a valid, classy number
        String s = String.valueOf(n);
        return countClassy(0, 0, true, s);
    }

    public static void main(String[] args) throws IOException {
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;

        int t = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long L = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());

            long ans = solve(R) - solve(L - 1);
            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}