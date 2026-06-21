public class Main {
    private static boolean isValid(int mid) {
        Map<Integer, Integer> mpp = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int mask = 0;
            for (int j = 0; j < m; ++j) {
                if (mid <= A[i][j])
                    mask |= (1 << j);
            }
            mpp.put(mask, i + 1);
        }

        int target_mask = (1 << m) - 1;
        for (int mask1 : mpp.keySet()) {
            for (int mask2 : mpp.keySet()) {
                if ((mask1 | mask2) == target_mask) {
                    ans_i = mpp.get(mask1);
                    ans_j = mpp.get(mask2);
                    mpp.clear();
                    return true;
                }
            }
        }
        mpp.clear();
        return false;
    }

    private static int[][] A;
    private static int n, m, ans_i, ans_j;

    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();

        A = new int[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                A[i][j] = sc.nextInt();

        int low = 0;
        int high = 1_000_000_000;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (isValid(mid))
                low = mid + 1;
            else
                high = mid - 1;
        }
        System.out.println(ans_i + " " + ans_j);
    }
}