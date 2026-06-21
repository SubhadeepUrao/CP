public class Main {
    private static boolean costLessThanEquals(int X) {
        // Satisfying either of the cases is sufficient because we need to minimum of
        // the maximums

        int len1 = 0, len2 = 0;

        // Case I : max of odd indices <= X
        for (int i = 0; i < n; ++i) { // 0-indexed
            if ((len1 & 1) == 0) { // odd => even because of 1-indexed => 0-indexed
                if (A[i] <= X)
                    ++len1;
            } else // simply to pad the length of our subsequence to reach k
                ++len1;
        }

        // Case II : max of even indices <= X
        for (int i = 0; i < n; ++i) { // 0-indexed
            if ((len2 & 1) == 1) { // even => odd because of 1-indexed => 0-indexed
                if (A[i] <= X)
                    ++len2;
            } else // simply to pad the length of our subsequence to reach k
                ++len2;
        }

        // If either case can form a subsequence of length >= k, x is possible
        return k <= len1 || k <= len2;
    }

    private static int n, k;
    private static int[] A;

    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        k = sc.nextInt(); // 1-indexed

        A = new int[n];
        for (int i = 0; i < n; ++i)
            A[i] = sc.nextInt();

        int low = 0;
        int high = 1_000_000_000;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (costLessThanEquals(mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        System.out.println(low);
    }
}