public class Main {
    private static void solve(FastScanner sc) {
        int x = sc.nextInt();
        System.out.println(x / 5 + (x % 5 > 0 ? 1 : 0));
    }
}