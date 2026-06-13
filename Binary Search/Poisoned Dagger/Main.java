import java.util.Arrays;

public class Main {
    private static boolean isValid(long x) {
        int lb = 1;
        int ub = n - 1;
        while (lb <= ub) {
            int mid = (lb + ub) >>> 1;
            if (diff[mid] <= x)
                lb = mid + 1;
            else
                ub = mid - 1;
        }
        long sum = prev_sum[ub] + (n - ub) * x;
        return sum >= h;
    }

    private static int n;
    private static long h;
    private static int[] diff;
    private static long[] prev_sum;

    // (nlogn)+(logn×logh)
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            n = sc.nextInt();
            h = sc.nextLong();

            diff = new int[n]; // 1 to n-1
            int prev = sc.nextInt();
            for (int i = 1; i < n; ++i) {
                int curr = sc.nextInt();
                diff[i] = curr - prev;
                prev = curr;
            }
            Arrays.sort(diff);

            prev_sum = new long[n]; // 1 to n-1
            long sum = 0L;
            for (int i = 1; i < n; ++i) {
                sum += diff[i];
                prev_sum[i] = sum;
            }

            long low = 1;
            long high = h;
            while (low <= high) {
                long mid = (low + high) >>> 1;
                if (isValid(mid))
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            System.out.println(low);
        }
    }
}