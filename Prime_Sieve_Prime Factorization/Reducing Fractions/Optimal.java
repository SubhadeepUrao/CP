public class Optimal {
    private static int MAX = 10_000_000;
    private static int[] spf = new int[MAX + 1];

    // Precompute the Smallest Prime Factor (SPF) for each number
    private static void preComputeSPF() {
        for (int i = 1; i <= MAX; ++i) {
            if ((i & 1) == 0)
                spf[i] = 2;
            else
                spf[i] = i;
        }

        for (int p = 3; p * p <= MAX; p += 2) {
            if (spf[p] == p) {
                int stride = p << 1;
                for (int i = p * p; i <= MAX; i += stride)
                    if (spf[i] == i)
                        spf[i] = p;
            }
        }
    }

    private static void reduce(int[] X, int[] cancel) {
        int n = X.length;
        for (int i = 0; i < n; ++i) {
            int temp = X[i];
            int val = 1;
            while (temp > 1) {
                int p = spf[temp];
                if (cancel[p] > 0)
                    --cancel[p];
                else
                    val *= p;

                temp /= p;
            }
            X[i] = val;
        }
    }

    private static void solve(FastScanner sc) {
        preComputeSPF();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] A = new int[n];
        int[] B = new int[m];

        int[] countA = new int[MAX + 1]; // count prime factors of A
        int[] countB = new int[MAX + 1]; // count prime factors of B

        for (int i = 0; i < n; ++i) {
            A[i] = sc.nextInt();

            int temp = A[i];
            while (temp > 1) {
                ++countA[spf[temp]];
                temp /= spf[temp];
            }
        }
        for (int i = 0; i < m; ++i) {
            B[i] = sc.nextInt();

            int temp = B[i];
            while (temp > 1) {
                ++countB[spf[temp]];
                temp /= spf[temp];
            }
        }

        int[] cancelA = new int[MAX + 1];
        int[] cancelB = new int[MAX + 1];
        for (int i = 2; i <= MAX; ++i) {
            int common = Math.min(countA[i], countB[i]);
            cancelA[i] = common;
            cancelB[i] = common;
        }

        // reduce A
        reduce(A, cancelA);
        // reduce B
        reduce(B, cancelB);

        StringBuilder str = new StringBuilder();
        str.append(n).append(' ').append(m).append('\n');
        for (int i = 0; i < n; ++i)
            str.append(A[i]).append(' ');
        str.append('\n');
        for (int i = 0; i < m; ++i)
            str.append(B[i]).append(' ');
        System.out.println(str.toString());
    }
}