public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int x = sc.nextInt();
            if (n < 3) {
                System.out.println(1);
            } else {
                System.out.println((n - 3) / x + 2);
            }
        }
    }
}