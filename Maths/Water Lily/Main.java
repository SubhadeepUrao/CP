public class Main {
    private static void solve(FastScanner sc) {
        double H = sc.nextInt();
        double L = sc.nextInt();

        System.out.println(((H * H) + (L * L)) / (2 * H) - H);
    }
}