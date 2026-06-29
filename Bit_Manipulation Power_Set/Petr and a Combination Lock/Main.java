public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; ++i) {
            A[i] = sc.nextInt();
        }

        for (int mask = 0; mask < (1 << n); ++mask) {
            int net = 0;
            for (int i = 0; i < n; ++i) {
                // if ((mask & (1L << i)) > 0)
                if ((mask >> i & 1) > 0)
                    net += A[i];
                else
                    net -= A[i];
            }
            if (net % 360 == 0) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}