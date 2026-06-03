public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();

            int y = l << 1;
            if (y <= r)
                System.out.println(l + " " + y);
            else
                System.out.println(-1 + " " + -1);
        }
    }
}