public class Optimal {
    private static int MAX = 5_000_000;
    private static int[] minPrime = new int[MAX + 1];
    private static int[] totalPrimeFactors = new int[MAX + 1];
    private static int[] prefixSum = new int[MAX + 1];

    private static void precompute() {
        // Step 1: Modified Sieve to store the smallest prime factor (SPF)
        for (int i = 2; i <= MAX; ++i) {
            if (minPrime[i] == 0) {
                for (int j = i; j <= MAX; j += i) {
                    if (minPrime[j] == 0)
                        minPrime[j] = i;
                }
            }
        }

        // Step 2: Compute total prime factors count using DP based on SPF
        // Total factors of x = 1 + Total factors of (x / SPF(x))
        for (int i = 2; i <= MAX; ++i) {
            totalPrimeFactors[i] = 1 + totalPrimeFactors[i / minPrime[i]];
        }

        // Step 3: Compute the Prefix Sums
        for (int i = 2; i <= MAX; ++i) {
            prefixSum[i] = prefixSum[i - 1] + totalPrimeFactors[i];
        }
    }

    private static void solve(FastScanner sc) {
        PrintWriter out = new PrintWriter(System.out);
        precompute();
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            out.println(prefixSum[a] - prefixSum[b]);
        }
        out.flush();
    }
}