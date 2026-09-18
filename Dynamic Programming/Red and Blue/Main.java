public class Main {

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt();
            int max_red = 0;
            int presum_red = 0;
            for (int i = 0; i < n; ++i) {
                presum_red += sc.nextInt();
                max_red = Math.max(max_red, presum_red);
            }

            int m = sc.nextInt();
            int max_blue = 0;
            int presum_blue = 0;
            for (int i = 0; i < m; ++i) {
                presum_blue += sc.nextInt();
                max_blue = Math.max(max_blue, presum_blue);
            }

            res.append(max_red + max_blue).append('\n');
        }
        System.out.println(res);
    }
}