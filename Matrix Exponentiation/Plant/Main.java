public class Main {

    static final long MOD = 1_000_000_007L;

    private static long power(long base, long exp) {
        long res = 1L;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>>= 1;
        }
        return res;
    }

    private static void solve(FastScanner sc) {
        long n = sc.nextLong();

        if (n == 0) {
            System.out.println(1);
            return;
        }

        long sum1 = power(2, 2 * n - 1);
        long sum2 = power(2, n - 1);
        System.out.println((sum1 + sum2) % MOD);
    }
}