public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i)
            nums[i] = sc.nextInt();

        int[] left = new int[n];
        int[] right = new int[n];

        // calculate left bounds for each index i
        for (int i = 0; i < n; ++i) {
            int l = i;
            int curr = nums[i];
            while (l > 0 && nums[l - 1] % curr == 0) {
                l = left[l - 1];
            }
            left[i] = l;
        }
        // calculate right bounds for each index i
        for (int i = n - 1; i >= 0; --i) {
            int r = i;
            int curr = nums[i];
            while (r < n - 1 && nums[r + 1] % curr == 0) {
                r = right[r + 1];
            }
            right[i] = r;
        }

        // find max length r - l
        int maxlen = 0;
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; ++i) {
            int diff = right[i] - left[i]; // r - l
            if (diff > maxlen) {
                maxlen = diff;
                top = 0;
                stack[top] = left[i];
            } else if (diff == maxlen) {
                if (top < 0 || stack[top] != left[i])
                    stack[++top] = left[i];
            }
        }

        System.out.println((top + 1) + " " + maxlen);
        for (int i = 0; i <= top; ++i)
            System.out.print((stack[i] + 1) + " ");
    }
}