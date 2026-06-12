public class Main {
    private static boolean isValid(int mid, int[] recipe, int[] stock) {
        int cost = 0;
        for (int i = 0; i < n; ++i) {
            cost += Math.max(0, mid * recipe[i] - stock[i]);
        }
        return cost <= k;
    }

    private static int n = 0, k = 0;

    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        k = sc.nextInt();
        int[] recipe = new int[n];
        int[] stock = new int[n];

        for (int i = 0; i < n; ++i)
            recipe[i] = sc.nextInt();
        for (int i = 0; i < n; ++i)
            stock[i] = sc.nextInt();

        int low = 0;
        int high = 2_000;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (isValid(mid, recipe, stock))
                low = mid + 1;
            else
                high = mid - 1;
        }
        System.out.println(high);
    }
}