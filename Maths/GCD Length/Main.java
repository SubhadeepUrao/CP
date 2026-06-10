public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            int x = (int) Math.pow(10, a - 1);
            int y = (int) (Math.pow(10, b - 1) + Math.pow(10, c - 1));

            System.out.println(x + " " + y);
        }
    }
}