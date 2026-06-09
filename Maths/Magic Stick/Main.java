public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (x == 1) {
                if (y == 1) System.out.println("YES");
                else System.out.println("NO");
            } else if (x == 2 || x == 3) {
                if (y <= 3) System.out.println("YES");
                else System.out.println("NO");
            } else
                System.out.println("YES");
        }
    }
}