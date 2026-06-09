public class Main {
    private static void solve(FastScanner sc) {
        long n = sc.nextLong();
        long m = sc.nextLong();
        long a = sc.nextLong();
        System.out.println(((m + a - 1) / a) * ((n + a - 1) / a));
    }
}