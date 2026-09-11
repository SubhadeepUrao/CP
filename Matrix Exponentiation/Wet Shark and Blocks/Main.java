public class Main {
    
    static final long MOD = 1_000_000_007L;

    private static long[][] multiply(long[][] A, long[][] B, int x) {
        long[][] C = new long[x][x];
        for (int i = 0; i < x; ++i) {
            for (int k = 0; k < x; ++k) {
                if (A[i][k] == 0)
                    continue;
                for (int j = 0; j < x; ++j) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private static long[][] power(long[][] base, int exp, int x) {
        long[][] res = new long[x][x];
        // Identity matrix
        for (int i = 0; i < x; ++i)
            res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1)
                res = multiply(res, base, x);
            base = multiply(base, base, x);
            exp >>>= 1;
        }
        return res;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt(); // # digits in one block
        int b = sc.nextInt(); // # blocks
        int k = sc.nextInt(); // target remainder mod x
        int x = sc.nextInt(); // modulo x

        int[] freq = new int[10];
        for (int i = 0; i < n; ++i) {
            int digit = sc.nextInt();
            ++freq[digit];
        }

        long[][] T = new long[x][x]; // T[i][j] = # ways to transition from rem i to rem j
        for (int i = 0; i < x; ++i) {
            for (int digit = 1; digit <= 9; ++digit) {

                if (freq[digit] > 0) {
                    int j = (10 * i + digit) % x;
                    T[i][j] = (T[i][j] + freq[digit]) % MOD;
                }
            }
        }

        long[][] T_b = power(T, b, x);

        // dp[] = [(ways to rem 0) (ways to rem 1) (ways to rem 2) ... (ways to rem x-1)]
        // dp[seen 0 blocks] = [1 0 0 ... 0]
        // dp[seen b blocks] = dp[seen 0 blocks] * T[][]^b

        System.out.println(T_b[0][k]);
    }
}