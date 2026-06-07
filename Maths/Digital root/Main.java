public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            long k = sc.nextLong();
            int x = sc.nextInt();

            System.out.println(x + (k - 1) * 9);
        }
    }
}