public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int n = sc.nextInt();
            int candidate = n / x * x + y;

            if (candidate > n)
                candidate -= x;

            System.out.println(candidate);
        }
    }
}