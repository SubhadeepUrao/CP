public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(2);

            int j = n;
            for (int i = n - 1; i >= 1; --i) {
                System.out.println(i + " " + j);
                j = i + j + 1 >> 1;
            }
        }
    }
}