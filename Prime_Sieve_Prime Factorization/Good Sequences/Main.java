public class Main {
    private static int gcd(int m, int n) {
        while (n > 0) {
            int rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    private static void solve(FastScanner sc) {
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; ++i)
            nums[i] = sc.nextInt();

        int[] prev = new int[N + 1];

        for (int j = 1; j < N; ++j)
            prev[j] = gcd(nums[j], nums[0]) > 1 ? 1 : 0;
        prev[N] = 1;

        for (int i = 1; i < N; ++i) {
            int diagonal = prev[i];
            for (int j = i + 1; j <= N; ++j) {
                int take = (j == N || gcd(nums[j], nums[i]) > 1) ? diagonal + 1 : 0;
                int not_take = prev[j];
                prev[j] = Math.max(take, not_take);
            }
        }
        System.out.println(prev[N]);
    }
}