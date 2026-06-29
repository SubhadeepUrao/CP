public class Main {
    private static void solve(FastScanner sc) {
        long n = sc.nextLong();
        long k = sc.nextLong();

        if (k == 1) {
            System.out.println(n);
            return;
        }

        long max = 1;
        while (max <= n) {
            max <<= 1;
        }
        System.out.println(max - 1);
    }
}