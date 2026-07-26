public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int w = sc.nextInt();

        // Edge Case 1: Pattern length 1 matches every position in bear
        if (w == 1) {
            System.out.println(n);
            return;
        }

        // Edge Case 2: Pattern longer than bear wall
        if (w > n) {
            System.out.println(0);
            return;
        }

        int[] bear = new int[n];
        int[] elephant = new int[w];

        for (int i = 0; i < n; ++i)
            bear[i] = sc.nextInt();
        for (int i = 0; i < w; ++i)
            elephant[i] = sc.nextInt();

        // relative difference is invariant
        int[] diffBear = new int[n - 1];
        int[] diffElephant = new int[w - 1];
        for (int i = 1; i < n; ++i)
            diffBear[i - 1] = bear[i] - bear[i - 1];
        for (int i = 1; i < w; ++i)
            diffElephant[i - 1] = elephant[i] - elephant[i - 1];

        int patternLen = w - 1;
        int[] LPS = new int[patternLen];
        LPS[0] = 0; // base case
        int len = 0;
        int i = 1;

        // compute LPS array
        while (i < patternLen) {
            if (diffElephant[len] == diffElephant[i]) {
                LPS[i++] = ++len;
            } else {
                if (len > 0)
                    len = LPS[len - 1];
                else
                    ++i;
            }
        }

        i = 0;
        int j = 0;
        int segment = 0;
        int searchLen = n - 1;
        while (i < searchLen) {
            if (diffBear[i] == diffElephant[j]) {
                ++i;
                ++j;
            }

            if (j == patternLen) {
                ++segment;
                j = LPS[j - 1];
            } else if (i < searchLen && diffBear[i] != diffElephant[j]) {
                if (j > 0)
                    j = LPS[j - 1];
                else
                    ++i;
            }
        }

        System.out.println(segment);
    }
}