public class Main {
    private static void solve(FastScanner sc) {
        char[] s = sc.next().toCharArray();
        char[] t = sc.next().toCharArray();

        int n = s.length;
        int m = t.length;

        int zeros = 0, ones = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == '0')
                ++zeros;
            else
                ++ones;
        }

        // compute LPS array for t
        int[] LPS = new int[m];
        LPS[0] = 0; // base case
        int i = 1;
        int len = 0;

        while (i < m) {
            if (t[len] == t[i]) {
                LPS[i++] = ++len;
            } else {
                if (len > 0)
                    len = LPS[len - 1];
                else
                    ++i;
            }
        }

        StringBuilder res = new StringBuilder();

        int overlapIdx = LPS[m - 1];
        int idx = 0;
        while (zeros > 0 || ones > 0) {
            char ch = t[idx];

            if (ch == '0' && zeros > 0) {
                res.append(ch);
                --zeros;
            } else if (ch == '1' && ones > 0) {
                res.append(ch);
                --ones;
            } else {
                while (zeros > 0) { res.append('0'); --zeros; }
                while (ones > 0) { res.append('1'); --ones; }
            }
            ++idx;
            if (idx == m)
                idx = overlapIdx;
        }
        System.out.println(res);
    }
}