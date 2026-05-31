public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            long a = sc.nextInt();
            long b = sc.nextInt();
            long c = sc.nextInt();

            if (a < c) System.out.print(1);
            else System.out.print(-1);

            System.out.print(' ');

            if (a * b > c) System.out.println(b);
            else System.out.println(-1);
        }
    }
}