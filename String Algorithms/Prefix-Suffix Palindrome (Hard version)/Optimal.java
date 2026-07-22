// KMP algorithm

public class Main {
    private static int longestPalinPrefix(char[] s, char[] rev) {
        int n = s.length;
        char[] str = new char[2 * n + 1];

        // concatenating both strings with '#'
        str[n] = '#';
        for (int i = 0; i < n; ++i) {
            str[i] = s[i];
            str[n + i + 1] = rev[i];
        }

        int m = str.length;
        int[] LPS = new int[m];

        LPS[0] = 0;
        int len = 0;
        int i = 1;

        while (i < m) {
            // matches --> increase the matching length
            if (str[len] == str[i]) {
                ++len;
                LPS[i] = len;
                ++i;
            } else {
                if (len > 0) {
                    len = LPS[len - 1];
                } else {
                    LPS[i] = 0;
                    ++i;
                }
            }
        }
        return LPS[m - 1];
    }

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();
        while (t-- > 0) {
            String s = sc.next();
            char[] str = s.toCharArray();
            int n = str.length;

            int l = 0, r = n - 1;
            while (l < r && str[l] == str[r]) {
                ++l;
                --r;
            }

            if (l >= r) {
                res.append(str).append('\n');
                continue;
            }

            char[] rem = new char[r - l + 1];
            char[] revRem = new char[r - l + 1];
            for (int i = l; i <= r; ++i) {
                rem[i - l] = str[i];
                revRem[i - l] = str[r - i + l];
            }

            // String rem = s.substring(l, r + 1);
            // int prefixLen = longestPalinPrefix(rem.toCharArray());
            // int suffixLen = longestPalinPrefix(new
            // StringBuilder(rem).reverse().toString().toCharArray());

            int prefixLen = longestPalinPrefix(rem, revRem);
            int suffixLen = longestPalinPrefix(revRem, rem);

            String outerPrefix = s.substring(0, l);
            String outerSuffix = s.substring(r + 1);

            if (prefixLen >= suffixLen)
                res.append(outerPrefix + new String(rem).substring(0, prefixLen) + outerSuffix + '\n');
            else
                res.append(outerPrefix + new String(rem).substring(rem.length - suffixLen) + outerSuffix + '\n');
        }
        System.out.println(res);
    }
}