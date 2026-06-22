public class Optimal {
    private static void solve(FastScanner sc) {
        int N = sc.nextInt();
        int[] nums = new int[N];
        int maxi = 0;
        for (int i = 0; i < N; ++i) {
            nums[i] = sc.nextInt();
            maxi = Math.max(maxi, nums[i]);
        }

        // Sieve should clear composites up to 'maxi'
        boolean[] isComposite = new boolean[(maxi / 2) + 1];
        int maxfactor = (int) Math.sqrt(maxi);
        for (int p = 3; p <= maxfactor; p += 2) {
            if (!isComposite[p >> 1]) {
                int stride = p << 1;
                // Run all the way to maxi
                for (int i = p * p; i <= maxi; i += stride) {
                    isComposite[i >> 1] = true;
                }
            }
        }

        int[] dp = new int[(maxi / 2) + 1];
        int overallMax = 1;

        for (int i = 0; i < N; ++i) {
            int temp = nums[i];
            List<Integer> primefactors = new ArrayList<>();

            if (temp % 2 == 0) {
                primefactors.add(0); // 1st prime = 2
                while (temp % 2 == 0) {
                    temp >>= 1;
                }
            }

            for (int p = 3; p * p <= temp; p += 2) {
                if (!isComposite[p >> 1] && temp % p == 0) {
                    primefactors.add(p >> 1);
                    while (temp % p == 0) {
                        temp /= p;
                    }
                }
            }

            // Catch the final remaining prime factor if it's > 1
            if (temp > 1) {
                primefactors.add(temp >> 1);
            }

            // Find the best existing chain we can extend
            int maxlen = 0;
            for (int p : primefactors) {
                maxlen = Math.max(dp[p], maxlen);
            }

            // Update DP array for all prime factors of the current number
            for (int p : primefactors) {
                dp[p] = Math.max(dp[p], maxlen + 1);
            }

            overallMax = Math.max(overallMax, maxlen + 1);
        }

        System.out.println(overallMax);
    }
}