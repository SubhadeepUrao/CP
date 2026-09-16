public class Main {

    static final int BITS = 60;

    private static int insert(long value, int[] trie, int nodeCnt) {
        int curr = 1;
        for (int i = BITS - 1; i >= 0; --i) {
            int bit = (int) ((value >> i) & 1);
            int edge = curr * 2 + bit;
            if (trie[edge] == 0) {
                trie[edge] = ++nodeCnt;
            }
            curr = trie[edge];
        }

        return nodeCnt;
    }

    private static long query(long value, int[] trie) {
        long res = 0L;
        int curr = 1;

        for (int i = BITS - 1; i >= 0; --i) {
            int bit = (int) ((value >> i) & 1);
            int desiredBit = 1 ^ bit;
            int edge = curr * 2 + desiredBit;

            if (trie[edge] > 0) {
                curr = trie[edge];
                res |= (1L << i);
            } else {
                curr = trie[curr * 2 + bit];
            }
        }
        return res;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        long[] a = new long[n + 1];
        long[] pref = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            a[i] = sc.nextLong();
            pref[i] = pref[i - 1] ^ a[i];
        }

        long[] suff = new long[n + 2];
        for (int i = n; i >= 1; --i)
            suff[i] = suff[i + 1] ^ a[i];

        int MAX_NODES = (n + 2) * (BITS + 1) + 5;
        int[] trie = new int[MAX_NODES * 2];
        int nodeCnt = 1;

        // insert pref[0] i.e. 0
        nodeCnt = insert(pref[0], trie, nodeCnt);
        // compare with full suffix
        long res = query(suff[1], trie);

        for (int i = 1; i <= n; ++i) {
            nodeCnt = insert(pref[i], trie, nodeCnt);
            res = Math.max(res, query(suff[i + 1], trie));
        }

        System.out.println(res);
    }
}