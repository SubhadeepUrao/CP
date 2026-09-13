public class Main {

    public static int solve(int[] a, int left, int right, int bit) {
        if (bit < 0 || left > right) {
            return 0;
        }

        // Partition the subarray a[left..right] into two regions based on bit
        int mid = left;
        for (int i = left; i <= right; i++) {
            if (((a[i] >> bit) & 1) == 0) {
                int temp = a[i];
                a[i] = a[mid];
                a[mid] = temp;
                mid++;
            }
        }

        // mid points to the first element with the bit set to 1
        int v0Count = mid - left;
        int v1Count = right - mid + 1;

        if (v0Count == 0) return solve(a, mid, right, bit - 1);
        if (v1Count == 0) return solve(a, left, mid - 1, bit - 1);

        return (1 << bit) | Math.min(solve(a, left, mid - 1, bit - 1), solve(a, mid, right, bit - 1));
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();

        System.out.println(solve(a, 0, n - 1, 29));
    }
}