public class Main {

    private static int countSubstrings(String T, String prefix, String suffix) {
        int n = T.length();
        int prefLen = prefix.length();
        int suffLen = suffix.length();
        int minLen = Math.max(prefLen, suffLen);

        boolean[] start = new boolean[n];
        boolean[] end = new boolean[n];

        for (int i = 0; i <= n - prefLen; ++i)
            if (T.startsWith(prefix, i))
                start[i] = true;

        for (int i = suffLen - 1; i < n; ++i)
            if (T.startsWith(suffix, i - suffLen + 1))
                end[i] = true;

        int MAX_NODES = 2000 * 2001 / 2 + 5;
        int[][] trie = new int[MAX_NODES][26];
        boolean[] isCounted = new boolean[MAX_NODES];
        int nodeCnt = 1;

        int ans = 0;

        for (int i = 0; i < n; ++i) {
            if (!start[i]) continue;

            int curr = 1;
            for (int j = i; j < n; ++j) {
                int idx = T.charAt(j) - 'a';

                if (trie[curr][idx] == 0) {
                    trie[curr][idx] = ++nodeCnt;
                }
                curr = trie[curr][idx];

                int len = j - i + 1;
                if (minLen <= len && end[j] && !isCounted[curr]) {
                    isCounted[curr] = true;
                    ++ans;
                }
            }
        }

        return ans;
    }

    private static void solve(FastScanner sc) {
        String T = sc.next();
        String prefix = sc.next();
        String suffix = sc.next();

        System.out.println(countSubstrings(T, prefix, suffix));
    }
}