public class Main {
    
    static long[][][] dp = new long[20][4][2];

    private static long countClassy(int idx, int count, int tight, String s) {
        // If non-zero digits exceed 3, this path is invalid
        if (count > 3) {
            return 0;
        }

        // Base case: processed all digits successfully
        if (idx == s.length()) {
            return 1;
        }

        // Return memoized result if available
        if (dp[idx][count][tight] != -1) {
            return dp[idx][count][tight];
        }

        long ans = 0;
        int limit = (tight == 1) ? (s.charAt(idx) - '0') : 9;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = (tight == 1 && digit == limit) ? 1 : 0;
            int newCount = count + (digit != 0 ? 1 : 0);

            ans += countClassy(idx + 1, newCount, newTight, s);
        }

        return dp[idx][count][tight] = ans;
    }

    private static long solve(long n) {
        if (n < 0) return 0;
        
        String s = String.valueOf(n);
        
        // Reset memoization table
        for (long[][] layer : dp) {
            for (long[] row : layer) {
                Arrays.fill(row, -1);
            }
        }

        return countClassy(0, 0, 1, s);
    }

    public static void main(String[] args) throws IOException {
        // Fast I/O for Codeforces
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line = br.readLine();
        if (line == null) return;
        
        int t = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            long L = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());

            long ans = solve(R) - solve(L - 1);
            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}