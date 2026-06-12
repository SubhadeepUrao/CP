public class Main {
    private static int b = 0, s = 0, c = 0;
    private static int stock_b = 0, stock_s = 0, stock_c = 0;
    private static int price_b = 0, price_s = 0, price_c = 0;
    private static long wallet = 0;

    private static boolean isValid(long mid) {
        long extra_b = Math.max(0, mid * b - stock_b);
        long extra_s = Math.max(0, mid * s - stock_s);
        long extra_c = Math.max(0, mid * c - stock_c);

        long cost = extra_b * price_b + extra_s * price_s + extra_c * price_c;
        return cost <= wallet;
    }

    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        for (char ingredient : str) {
            if (ingredient == 'B')
                ++b;
            else if (ingredient == 'S')
                ++s;
            else
                ++c;
        }

        stock_b = sc.nextInt();
        stock_s = sc.nextInt();
        stock_c = sc.nextInt();

        price_b = sc.nextInt();
        price_s = sc.nextInt();
        price_c = sc.nextInt();

        wallet = sc.nextLong();

        long low = 0;
        long high = 1_000_000_000_100L;

        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (isValid(mid))
                low = mid + 1;
            else
                high = mid - 1;
        }
        System.out.println(high);
    }
}