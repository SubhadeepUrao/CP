public class Main {
    private static long power(long base, long exp) {
        long res = 1l;
        while (exp > 0) {
            if ((exp & 1) == 1) { // odd
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    private static int MOD = 1_000_000_007;

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            long n = sc.nextInt();
            int k = sc.nextInt();

            long ans = 0l;
            long temp = k;
            long prev = 1;
            long zeros = 0; // trailing zeros
            while (temp > 0) {
                long rightmost = temp & (temp - 1) ^ temp;
                long curr = rightmost;

                while (curr > prev) { // count trailing zeros
                    ++zeros;
                    curr >>= 1;
                }
                ans = (ans + power(n, zeros)) % MOD;
                prev = rightmost;
                temp &= temp - 1;
            }
            System.out.println(ans);
        }
    }
}