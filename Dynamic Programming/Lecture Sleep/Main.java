public class Main {

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] theorems = new int[n + 1];
        int[] behaviour = new int[n + 1];
        int[] prefix = new int[n + 1]; // stores prefix sum of lectures based on behaviour
        int[] sum = new int[n + 1]; // stores prefix sum of lectures

        for (int i = 1; i <= n; ++i) {
            theorems[i] = sc.nextInt();
            sum[i] = sum[i - 1] + theorems[i];
        }
        for (int i = 1; i <= n; ++i) {
            behaviour[i] = sc.nextInt();
            prefix[i] = prefix[i - 1] + theorems[i] * behaviour[i];
        }

        int next = 0;
        int maxT = 0;
        int nextPos = n;
        for (int i = n - k + 1; i >= 1; --i) {
            maxT = Math.max(maxT, prefix[i - 1] + (sum[nextPos] - sum[i - 1]) + next);
            next += theorems[nextPos] * behaviour[nextPos];
            --nextPos;
        }
        System.out.println(maxT);
    }
}