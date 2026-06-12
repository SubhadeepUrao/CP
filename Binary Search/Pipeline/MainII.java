public class Main {
    private static long totalNeeded;
    private static long k;

    // Checks if taking 'mid' number of the largest splitters is enough
    private static boolean isValid(long mid) {
        // Sum of all splitters from 1 to k-1
        long totalSum = (k - 1) * k / 2;

        // Sum of the remaining smaller splitters that we DO NOT take
        long remainingCount = (k - 1) - mid;
        long remainingSum = remainingCount * (remainingCount + 1) / 2;

        // The net gain from taking the 'mid' largest splitters
        long currentGain = totalSum - remainingSum;

        // long currentGain = x * (2 * k - 1 - x) / 2;

        return currentGain >= totalNeeded;
    }

    private static void solve(FastScanner sc) {
        long n = sc.nextLong();
        k = sc.nextLong();

        // If we already have enough or need no splitters
        if (n == 1) {
            System.out.println(0);
            return;
        }

        totalNeeded = n - 1;
        long maxPossibleGain = (k - 1) * k / 2;

        // If even using all splitters isn't enough
        if (maxPossibleGain < totalNeeded) {
            System.out.println(-1);
            return;
        }

        long low = 1;
        long high = k - 1;
        long ans = high;

        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (isValid(mid)) {
                ans = mid; // This number of splitters works, try to find a smaller valid amount
                high = mid - 1;
            } else {
                low = mid + 1; // Not enough pipes, we need more splitters
            }
        }

        System.out.println(ans);
    }
}