public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int l = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int p = sc.nextInt();
        int nl = sc.nextInt();
        int np = sc.nextInt();

        int res = Math.min(k * l / nl, Math.min(c * d, p / np)) / n;
        System.out.print(Integer.valueOf(res));
    }
}