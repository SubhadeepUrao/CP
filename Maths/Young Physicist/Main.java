public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int x = 0;
        int y = 0;
        int z = 0;

        while (n-- > 0) {
            x += sc.nextInt();
            y += sc.nextInt();
            z += sc.nextInt();
        }

        System.out.println(x == 0 && y == 0 && z == 0 ? "YES" : "NO");
    }
}