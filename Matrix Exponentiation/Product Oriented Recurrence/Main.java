public class Main {

    static long MOD_VAL = 1_000_000_007L;
    // since we will operate on exponents, we consider mod (P - 1) by Fermat's
    // Little Theorem
    static long MOD_EXP = MOD_VAL - 1;

    private static long[][] multiply(long[][] A, long[][] B, int m, long MOD) {
        long[][] C = new long[m][m];
        for (int i = 0; i < m; ++i) {
            for (int k = 0; k < m; ++k) {
                if (A[i][k] == 0) continue; // Optimization
                for (int j = 0; j < m; ++j) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private static long[][] power(long[][] base, long exp, int m, long MOD) {
        long[][] res = new long[m][m];
        // Identity matrix
        for (int i = 0; i < m; ++i) res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, base, m, MOD);
            base = multiply(base, base, m, MOD);
            exp >>>= 1;
        }
        return res;
    }

    private static long power(long base, long exp, long MOD) {
        long res = 1L;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>>= 1;
        }
        return res;
    }

    private static void solve(FastScanner sc) {
        long n = sc.nextLong();
        int f1 = sc.nextInt();
        int f2 = sc.nextInt();
        int f3 = sc.nextInt();
        int c = sc.nextInt();

        long[][] T = {
                { 1, 1, 1 },
                { 1, 0, 0 },
                { 0, 1, 0 } };

        long[][] T_n = power(T, n - 3, 3, MOD_EXP);

        // [ [E3 E2 E1], [...], [...] ] = Identity * T^(n-3)
        long E3 = T_n[0][0];
        long E2 = T_n[0][1];
        long E1 = T_n[0][2];

        long[][] V = {
                { 1, 1, 1, 2, -4 + MOD_EXP },
                { 1, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0 },
                { 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 1 } };

        long[][] V_n = power(V, n - 3, 5, MOD_EXP);

        // vector at x = 3 ==> [0 0 0 x 1] i.e. [0 0 0 3 1]
        long Ec = (V_n[0][3] * 3 + V_n[0][4] * 1) % MOD_EXP;

        long ans = 1;
        ans = (ans * power(f1, E1, MOD_VAL)) % MOD_VAL;
        ans = (ans * power(f2, E2, MOD_VAL)) % MOD_VAL;
        ans = (ans * power(f3, E3, MOD_VAL)) % MOD_VAL;
        ans = (ans * power(c, Ec, MOD_VAL)) % MOD_VAL;

        System.out.println(ans);
    }
}