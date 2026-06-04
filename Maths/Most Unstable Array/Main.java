public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.println(n > 2 ? 2 * m : n == 2 ? m : 0);
        }
    }
}