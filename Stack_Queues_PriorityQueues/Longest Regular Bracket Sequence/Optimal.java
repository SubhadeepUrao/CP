public class Main {
    private static void solve(FastScanner sc) {
        char[] braces = sc.next().toCharArray();
        int[] lenStack = new int[1_000_001];
        int top = -1;
        int maxlen = 0;
        int count = 0;
        int[] freq = new int[1_000_001];
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

                    if (top < 0 && count >= maxlen) {
                        maxlen = count;
                        ++freq[count];
                    }
                } else {
                    lenStack[++top] = 1;
                }
            }
        }

        // finds substrings whose length equals maxlen
        while (top >= 0) {
            count = 0;
            while (top >= 0 && lenStack[top] > 1) {
                count += lenStack[top--];
            }
            if (count >= maxlen) {
                maxlen = count;
                ++freq[maxlen];
            }
            --top;
        }

        if (maxlen > 0)
            System.out.println(maxlen + " " + freq[maxlen]);
        else
            System.out.println("0 1");
    }
}