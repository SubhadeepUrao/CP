public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int[] stack = new int[n];
        int top = -1;

        int maxheight = 0;
        while (n-- > 0) {
            int curr = sc.nextInt();
            if (top < 0 || stack[top] > curr)
                stack[++top] = curr;
            else if (stack[top] < curr) {
                System.out.println("NO");
                return;
            } else
                --top;

            maxheight = Math.max(maxheight, curr);
        }
        if (top < 0 || maxheight <= stack[top])
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}