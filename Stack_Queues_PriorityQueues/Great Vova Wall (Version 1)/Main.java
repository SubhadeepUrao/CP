public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        boolean[] stack = new boolean[n];
        int top = -1;
        while (n-- > 0) {
            // if adjacent pair have same parity, we can pop it bcoz we can match the height
            // to any adjacent pairs using horizontal blocks
            // on encounter of different parity of adjacent pair, we push it to stack

            boolean parity = (sc.nextInt() & 1) > 0 ? false : true; // false => odd and true => even
            if (top < 0 || stack[top] != parity)
                stack[++top] = parity;
            else
                --top;
        }
        System.out.println(top <= 0 ? "YES" : "NO");
    }
}