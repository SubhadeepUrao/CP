public class Main {
    private static long n = 0, k = 0;

    private static boolean isValid(long x) {
        long maxsum = x * (2 * k - x + 1) / 2;
        long target = n + x - 1;
        return target <= maxsum;
    }

    private static void solve(FastScanner sc) {
        n = sc.nextLong();
        k = sc.nextLong();

        if (n == 1) {
            System.out.println(0);
            return;
        }
        // If even the maximum capacity is not enough, it's impossible.
        // long maxPossibleSplitters = k - 1;
        // long maxPossibleSum = maxPossibleSplitters * (2 * k - maxPossibleSplitters + 1) / 2;
        // long absoluteMaxPipes = maxPossibleSum - maxPossibleSplitters + 1;

        // if (absoluteMaxPipes < n) {
        //     System.out.println(-1);
        //     return;
        // }

        long low = 1;
        long high = k - 1;

        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (isValid(mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        System.out.println(low < k ? low : -1);
    }
}