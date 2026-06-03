public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int n = sc.nextInt();

            int max_coins = Math.max(a, Math.max(b, c));
            int need = 3 * max_coins - a - b - c;
            if (n >= need && (n - need) % 3 == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}