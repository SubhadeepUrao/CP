public class Main {

    static final long MOD = 1_000_000_007L;

    private static int charToIndex(char ch) {
        if ('a' <= ch && ch <= 'z') return ch - 'a';
        return ch - 'A' + 26;
    }

    private static long[][] multiply(long[][] A, long[][] B, int m) {
        long[][] C = new long[m][m];
        for (int i = 0; i < m; ++i) {
            for (int k = 0; k < m; ++k) {
                if (A[i][k] == 0) continue; // Optimization
                for (int j = 0; j < m; ++j)
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;        
            }
        }
        return C;
    }

    private static long[][] power(long[][] base, long exp, int m) {
        long[][] res = new long[m][m];
        // identity matrix
        for (int i = 0; i < m; ++i)
            res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, base, m);
            base = multiply(base, base, m);
            exp >>>= 1;
        }
        return res;
    }

    private static void solve(FastScanner sc) {
        long n = sc.nextLong();
        int m = sc.nextInt();
        int k = sc.nextInt();

        long[][] T = new long[m][m];
        for (int i = 0; i < m; ++i)
            Arrays.fill(T[i], 1);

        while (k-- > 0) {
            String forbidden = sc.next();
            int i = charToIndex(forbidden.charAt(0));
            int j = charToIndex(forbidden.charAt(1));
            T[i][j] = 0;
        }

        long[][] T_n = power(T, n - 1, m); // T^(n-1)

        // long[][] dp = new long[1][m];
        // Arrays.fill(dp, 1);
        // T_n = multiply(dp, T_n, m); // T^n = dp * T^(n-1)

        long total = 0L;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < m; ++j)
                total = (total + T_n[i][j]) % MOD;

        System.out.println(total);
    }
}