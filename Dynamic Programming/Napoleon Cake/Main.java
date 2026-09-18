public class Main {

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt();

            int[] A = new int[n];
            for (int i = 0; i < n; ++i)
                A[i] = sc.nextInt();

            int[] ans = new int[n];
            int rem = 0;
            for (int i = n - 1; i >= 0; --i) {
                rem = Math.max(rem, A[i]);
                if (rem > 0) {
                    ans[i] = 1;
                    --rem;
                }
            }

            for (int layer : ans)
                res.append(layer).append(' ');
            res.append('\n');
        }
        System.out.println(res);
    }
}