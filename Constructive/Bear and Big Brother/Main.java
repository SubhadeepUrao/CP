public class Main {
    private static void solve(FastScanner sc) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        int res = (int) (Math.log(y / (double)x) / Math.log(1.5)) + 1;
        System.out.println(res);
    }
}