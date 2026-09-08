public class Main {

    static final long MOD = 1_000_000_007L;

    private static long[][] multiply(long[][] A, long[][] B, int M) {
        long[][] C = new long[M][M];
        for (int i = 0; i < M; ++i) {
            for (int k = 0; k < M; ++k) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < M; ++j) C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
            }
        }
        return C;
    }

    private static long[][] power(long[][] base, long exp, int M) {
        long[][] res = new long[M][M];
        // setup Identity matrix
        for (int i = 0; i < M; ++i) res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, base, M);
            base = multiply(base, base, M);
            exp >>>= 1;
        }
        return res;
    }

    private static long getTotalConfig(long N, int M) {
        if (N < M) return 1;

        // setting up Transition Matrix (M X M)
        long[][] T = new long[M][M]; // transition matrix
        T[0][0] = T[0][M - 1] = 1;
        for (int i = 1; i < M; ++i) T[i][i - 1] = 1;

        T = power(T, N, M); // multiply T, N times

        // it's a shortcut
        // otherwise we have to multiply dp vector with the transition matrix
        // dp vector (V_0) : [1 0 0 ... 0]
        // so multiplying T by V_0 will result as [T[0][0] 0 0 ... 0]
        return T[0][0];
    }

    private static void solve(FastScanner sc) {
        long N = sc.nextLong();
        int M = sc.nextInt();

        System.out.println(getTotalConfig(N, M));
    }
}