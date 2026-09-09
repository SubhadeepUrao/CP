public class Main {

    static final long INF = (long) 1e18;

    private static long[][] multiply(long[][] A, long[][] B, int V) {
        // Tropical Matrix Multiplication
        // Result[i][j] = max_k ( A[i][k] + B[k][j] )

        long[][] C = new long[V][V];
        for (int i = 0; i < V; ++i) {
            Arrays.fill(C[i], -INF);
            for (int k = i; k < V; ++k) {
                if (A[i][k] == -INF) continue; // Optimization
                for (int j = k; j < V; ++j) {
                    if (B[k][j] == -INF) continue;
                    C[i][j] = Math.max(C[i][j], A[i][k] + B[k][j]);
                }
            }
        }
        return C;
    }

    private static long[][] power(long[][] base, long exp, int V) {
        long[][] res = new long[V][V];
        for (int i = 0; i < V; ++i) {
            Arrays.fill(res[i], -INF);
            res[i][i] = 0;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, base, V);
            base = multiply(base, base, V);
            exp >>>= 1;
        }
        return res;
    }

    private static long[][] buildBaseMatrix(int[] compressedA, int V) {
        // base transition matrix
        // T[i][j] = longest non-decreasing subsequence starting atleast at i and must
        // end at j
        long[][] T = new long[V][V];
        for (int i = 0; i < V; ++i)
            Arrays.fill(T[i], -INF);

        long[] dp = new long[V]; // dp[i] = longest subsequence ending at i
        for (int startVal = 0; startVal < V; ++startVal) {
            Arrays.fill(dp, -INF);

            for (int x : compressedA) {

                // skip if x is not atleast startVal
                if (x < startVal) continue;

                long bestPrev = 0;
                for (int i = startVal; i <= x; ++i)
                    bestPrev = Math.max(bestPrev, dp[i]);

                dp[x] = Math.max(dp[x], bestPrev + 1);
            }

            for (int endVal = startVal; endVal < V; ++endVal)
                T[startVal][endVal] = dp[endVal];
        }

        return T;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        long T = sc.nextLong();

        int[] A = new int[n];
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            A[i] = sc.nextInt();
            set.add(A[i]);
        }

        int V = set.size();
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 0;
        for (int value : set)
            map.put(value, rank++);

        int[] compressedA = new int[n];
        for (int i = 0; i < n; ++i)
            compressedA[i] = map.get(A[i]);

        // build base matrix (1-block matrix)
        long[][] transition = buildBaseMatrix(compressedA, V);
        // tropical matrix exponentiation [transition]^T
        long[][] transitionT = power(transition, T, V);

        long maxlen = 0;
        for (int i = 0; i < V; ++i) {
            for (int j = i; j < V; ++j)
                maxlen = Math.max(maxlen, transitionT[i][j]);
        }
        System.out.println(maxlen);
    }
}