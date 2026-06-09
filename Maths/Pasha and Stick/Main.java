public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        System.out.println((n & 1) == 0 ? (n - 1) >> 2 : 0);
    }
}