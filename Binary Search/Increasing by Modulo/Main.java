public class Main {
    private static boolean isPossible(int k) {
        int prev = 0;
        for (int i = 0; i < n; ++i) {
            if (prev <= nums[i]) {
                if (nums[i] + k < m || (nums[i] + k) % m < prev)
                    prev = nums[i];
            } else {
                if (prev > nums[i] + k)
                    return false;
            }
        }
        return true;
    }

    private static int n = 0, m = 0;
    private static int[] nums;

    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();
        nums = new int[n];
        for (int i = 0; i < n; ++i)
            nums[i] = sc.nextInt();

        int low = 0;
        int high = m - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (isPossible(mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        System.out.println(low);
    }
}