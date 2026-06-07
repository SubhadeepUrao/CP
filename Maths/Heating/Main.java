public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        while (n-- > 0) {
            int radiators = sc.nextInt();
            int total_sections = sc.nextInt();

            int x = total_sections / radiators;
            int y = total_sections % radiators;
            System.out.println(y * (x + 1) * (x + 1) + (radiators - y) * x * x);
        }
    }
}