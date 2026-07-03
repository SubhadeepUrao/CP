public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int p = sc.nextInt();

        for (int k = 1; k <= 30; ++k) {
            int target = n - k * p;
            // target should be positive as it is the sum of powers of 2
            if (target > 0 && Integer.bitCount(target) <= k && k <= target) {
                System.out.println(k);
                return;
            }
        }
        System.out.println(-1);
    }
}