public class Main {
    private static long countWays(long n) {
        long total_ways = 0;
        long k = 2L;
        while (true) {
            if (n / k / k < k)
                break;
            long kcube = k * k * k;
            total_ways += n / kcube;
            ++k;
        }
        return total_ways;
    }

    private static void solve(FastScanner sc) {
        long m = sc.nextLong();

        long low = 1;
        long high = 8_000_000_000_000_000L;

        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (m <= countWays(mid))
                high = mid - 1;
            else
                low = mid + 1;
        }

        System.out.println(countWays(low) == m ? low : -1);
    }
}