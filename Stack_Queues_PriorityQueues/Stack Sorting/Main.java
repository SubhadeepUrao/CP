public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] stack = new int[n];
        int top = -1;

        int[] res = new int[n];
        int lowest = 1;
        for (int i = 0; i < k; ++i) {
            int curr = sc.nextInt();
            res[i] = curr;
            if (top < 0 || stack[top] > curr) {
                stack[++top] = curr;
            } else {
                System.out.println(-1);
                return;
            }

            if (stack[top] == lowest) {
                while (top > 0 && stack[top - 1] == stack[top] + 1) {
                    --top;
                }
                lowest = stack[top] + 1;
                --top;
            }
        }

        while (top >= 0) {
            for (int i = stack[top] - 1; i >= lowest; --i)
                res[k++] = i;
            lowest = stack[top] + 1;
            --top;
        }
        for (int i = n; i >= lowest; --i)
            res[k++] = i;

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; ++i)
            str.append(res[i]).append(" ");
        System.out.println(str);
    }
}