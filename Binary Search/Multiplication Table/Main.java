public class Main {
    private static long countLessEqual(long X, long n, long m) {
        long total = 0L;
        for (int i = 1; i <= n; ++i) {
            total += Math.min(m, X / i);
        }
        return total;
    }

    private static void solve(FastScanner sc) {
        long n = sc.nextInt();
        long m = sc.nextInt();
        long k = sc.nextLong();

        if (n > m) {
            long temp = n;
            n = m;
            m = temp;
        }

        long low = 1;
        long high = n * m;

        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (countLessEqual(mid, n, m) < k)
                low = mid + 1;
            else
                high = mid - 1;
        }
        System.out.println(low);
    }
}