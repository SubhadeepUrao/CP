public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            if (n <= k) System.out.println((k + n - 1) / n);
            else {
                if (n % k == 0) System.out.println(1);
                else System.out.println(2);
            }
        }
    }
}