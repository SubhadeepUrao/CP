public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (b < a) {
                int temp = a;
                a = b;
                b = temp;
            }

            System.out.println(2 * a < b ? b * b : 4 * a * a);

        }
    }
}