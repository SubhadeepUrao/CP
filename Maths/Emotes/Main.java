public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        long m = sc.nextInt();
        long k = sc.nextInt();

        long max1, max2 = max1 = 0;
        for (int i = 0; i < n; ++i) {
            long val = sc.nextInt();
            if (val >= max1) {
                max2 = max1;
                max1 = val;
            } else if (val > max2) {
                max2 = val;
            }
        }

        // // Calculate how many full blocks of (k + 1) emotes we can use
        // long blocks = m / (k + 1);
        // long remainder = m % (k + 1);

        // // Value of a single full block: k times of max1 and 1 time of max2
        // long blockValue = (max1 * k) + max2;

        // // Total happiness calculation using entirely long variables
        // long totalHappiness = (blocks * blockValue) + (remainder * max1);

        System.out.println((max1 * k + max2) * (m / (k + 1)) + ((m % (k + 1)) * max1));
    }
}