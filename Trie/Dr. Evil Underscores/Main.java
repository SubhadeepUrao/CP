public class Main {

    static final int MAX_NODES = 1_00_000 * 31 + 5;
    static int[][] trie = new int[MAX_NODES][2];
    static int nodeCnt = 1;

    private static void insert(int val) {
        int curr = 1;
        for (int i = 29; i >= 0; --i) {
            int bit = (val >> i) & 1;
            if (trie[curr][bit] == 0) {
                trie[curr][bit] = ++nodeCnt;
            }
            curr = trie[curr][bit];
        }
    }

    private static int minimizeMaxXOR(int curr, int bit) {
        if (bit < 0)
            return 0;

        if (trie[curr][0] > 0 && trie[curr][1] > 0) {
            return (1 << bit)
                    | Math.min(minimizeMaxXOR(trie[curr][0], bit - 1), minimizeMaxXOR(trie[curr][1], bit - 1));
        }
        // Only 0 branch exists
        if (trie[curr][0] > 0)
            return minimizeMaxXOR(trie[curr][0], bit - 1);
        // Only 1 branch exists
        return minimizeMaxXOR(trie[curr][1], bit - 1);
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        for (int i = 0; i < n; ++i)
            insert(sc.nextInt());

        System.out.println(minimizeMaxXOR(1, 29));
    }
}