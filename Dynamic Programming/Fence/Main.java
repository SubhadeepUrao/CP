public class Main {

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] height = new int[n];
        for (int i = 0; i < n; ++i)
            height[i] = sc.nextInt();

        int sum = 0;
        for (int i = n - 1; i >= n - k; --i)
            sum += height[i];

        int min_plank_height = sum;
        int j = n - k;
        for (int i = n - k - 1; i >= 0; --i) {
            sum += height[i] - height[i + k];
            if (sum < min_plank_height) {
                min_plank_height = sum;
                j = i;
            }
        }
        System.out.println(j + 1);
    }
}