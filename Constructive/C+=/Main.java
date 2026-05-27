public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int n = sc.nextInt();

            int c = 0;
            while (a <= n && b <= n) {
                if (a < b)
                    a += b;
                else
                    b += a;
                ++c;
            }
            System.out.println(c);
        }
    }
}