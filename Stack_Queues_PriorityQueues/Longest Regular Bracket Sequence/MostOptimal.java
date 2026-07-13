public class Main {
    private static void solve(FastScanner sc) {
        String s = sc.next();
        if (s == null || s.isEmpty()) {
            System.out.println("0 1");
            return;
        }

        int n = s.length();
        int[] stack = new int[n + 1];
        int top = -1;

        // Push initial base boundary index (-1)
        stack[++top] = -1;

        int maxLen = 0;
        int count = 1; // Default: length 0 occurs 1 time

        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);

            if (curr == '(') {
                stack[++top] = i;
            } else {
                top--; // Pop matching '(' index

                if (top < 0) {
                    // Stack was empty; push current index as new boundary base
                    stack[++top] = i;
                } else {
                    // Valid substring length = current index - base index
                    int currentLen = i - stack[top];

                    if (currentLen > maxLen) {
                        maxLen = currentLen;
                        count = 1;
                    } else if (currentLen == maxLen && maxLen > 0) {
                        count++;
                    }
                }
            }
        }

        System.out.println(maxLen + " " + count);
    }
}