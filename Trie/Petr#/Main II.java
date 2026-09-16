import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // Using Fast Scanner to handle large inputs efficiently
        FastScanner sc = new FastScanner();
        solve(sc);
    }

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
        int[] trie = new int[MAX_NODES * 26];
        boolean[] isCounted = new boolean[MAX_NODES];
        int nodeCnt = 1;

        int ans = 0;

        for (int i = 0; i < n; ++i) {
            if (!start[i]) continue;

            int curr = 1;
            for (int j = i; j < n; ++j) {
                int idx = T.charAt(j) - 'a';
                int edge = curr * 26 + idx;
                if (trie[edge] == 0) {
                    trie[edge] = ++nodeCnt;
                }
                curr = trie[edge];

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

    // Fast I/O Utility Class
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        boolean hasNext() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return false;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return false;
                }
            }
            return true;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}