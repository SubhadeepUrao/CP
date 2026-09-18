public class Main {

    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        int q = sc.nextInt();

        int n = str.length;
        int[] presum = new int[n];
        for (int i = 1; i < n; ++i) {
            presum[i] = presum[i - 1] + (str[i - 1] == str[i] ? 1 : 0);
        }

        StringBuilder res = new StringBuilder();
        while (q-- > 0) {
            int l = sc.nextInt() - 1; // 0-based index
            int r = sc.nextInt() - 1; // 0-based index

            res.append(presum[r] - presum[l]).append('\n');
        }
        System.out.println(res);
    }
}