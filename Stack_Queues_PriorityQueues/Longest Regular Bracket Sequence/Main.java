public class Main {
    private static void solve(FastScanner sc) {
        char[] braces = sc.next().toCharArray();
        int[] lenStack = new int[1_000_000];
        int top = -1;
        int maxlen = 0;
        int substrings = 0, count = 0;
        for (char curr : braces) {
            if (top < 0) {
                if (curr == '(') {
                    lenStack[++top] = 1;
                }
            } else {
                if (curr == ')') {
                    count = 0;
                    while (top >= 0 && lenStack[top] > 1) {
                        count += lenStack[top];
                        --top;
                    }
                    if (top >= 0) {
                        count += 2;
                        lenStack[top] = count; // total = 1 + count + 1
                    }

                    maxlen = Math.max(maxlen, count);
                    if (top < 0) {
                        ++substrings;
                    }
                } else {
                    lenStack[++top] = 1;
                }
            }
        }

        // finds all valid substrings
        while (top >= 0) {
            count = 0;
            while (top >= 0 && lenStack[top] > 1) {
                count += lenStack[top--];
            }
            maxlen = Math.max(maxlen, count);
            if (count > 0)
                ++substrings;
            --top;
        }

        if (maxlen > 0)
            System.out.println(maxlen + " " + substrings);
        else
            System.out.println("0 1");
    }
}