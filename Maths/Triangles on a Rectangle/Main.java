public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            long w = sc.nextLong();
            long h = sc.nextLong();
            long maxi = 0;
            for (int x = 0; x < 2; ++x) {
                int n = sc.nextInt();
                long first = sc.nextLong();
                while (--n > 1)
                    sc.nextInt();
                long last = sc.nextLong();
                maxi = Math.max(maxi, (last - first) * h);
            }
            for (int y = 0; y < 2; ++y) {
                int n = sc.nextInt();
                long first = sc.nextLong();
                while (--n > 1)
                    sc.nextInt();
                long last = sc.nextLong();
                maxi = Math.max(maxi, (last - first) * w);
            }
            System.out.println(maxi);
        }
    }
}