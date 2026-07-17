public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int s = sc.nextInt();
        int l = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        final int INF = 1_000_000_007;

        // --- STEP 1: Compute left_bound[i] for all i using Monotonic Queues ---
        int[] leftBound = new int[n];
        ArrayDeque<Integer> maxDeq = new ArrayDeque<>();
        ArrayDeque<Integer> minDeq = new ArrayDeque<>();
        int j = 0; // Left pointer

        for (int i = 0; i < n; i++) {
            // Maintain max deque (decreasing order of elements)
            while (!maxDeq.isEmpty() && a[maxDeq.peekLast()] <= a[i]) {
                maxDeq.pollLast();
            }
            maxDeq.addLast(i);

            // Maintain min deque (increasing order of elements)
            while (!minDeq.isEmpty() && a[minDeq.peekLast()] >= a[i]) {
                minDeq.pollLast();
            }
            minDeq.addLast(i);

            // Shrink window if the max difference condition is violated
            while (a[maxDeq.peekFirst()] - a[minDeq.peekFirst()] > s) {
                j++;
                if (maxDeq.peekFirst() < j)
                    maxDeq.pollFirst();
                if (minDeq.peekFirst() < j)
                    minDeq.pollFirst();
            }
            leftBound[i] = j;
        }

        // --- STEP 2: Compute DP array using a 3rd Monotonic Queue ---
        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, INF);
        dp[0] = 0; // Base case: 0 partitions needed for empty prefix

        ArrayDeque<Integer> dpDeq = new ArrayDeque<>(); // Stores indices 'k' of valid DP states

        for (int i = 1; i <= n; i++) {
            // The newest candidate satisfying the length constraint is k = i - l
            int newK = i - l;
            if (newK >= 0 && dp[newK] != INF) {
                while (!dpDeq.isEmpty() && dp[dpDeq.peekLast()] >= dp[newK]) {
                    dpDeq.pollLast();
                }
                dpDeq.addLast(newK);
            }

            // Clean up candidates that violate the difference constraint
            // i-1 because leftBound array is 0-indexed, corresponding to sequence indices
            int minValidK = leftBound[i - 1];
            while (!dpDeq.isEmpty() && dpDeq.peekFirst() < minValidK) {
                dpDeq.pollFirst();
            }

            // If we have valid states available, make the optimal jump
            if (!dpDeq.isEmpty()) {
                dp[i] = dp[dpDeq.peekFirst()] + 1;
            }
        }

        // Output result
        if (dp[n] >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(dp[n]);
        }
    }
}