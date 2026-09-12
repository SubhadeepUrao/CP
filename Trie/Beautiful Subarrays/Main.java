public class Main {

    static final int MAX_NODES = 1_000_000 * 31 + 5;
    static int[][] trie = new int[MAX_NODES][2];
    static int[] count = new int[MAX_NODES];
    static int nodeCnt = 1;

    private static void insert(int P) {
        int curr = 1;
        ++count[curr];
        for (int i = 30; i >= 0; --i) {
            int bit = (P >> i) & 1;
            if (trie[curr][bit] == 0) {
                trie[curr][bit] = ++nodeCnt;
            }
            curr = trie[curr][bit];
            ++count[curr];
        }
    }

    private static long query(int P, int K) {
        int curr = 1;
        long validCount = 0;

        for (int i = 30; i >= 0; --i) {
            // path breaks
            if (curr == 0)
                break;

            int bitP = (P >> i) & 1;
            int bitK = (K >> i) & 1;

            if (bitK == 1) {
                curr = trie[curr][bitP ^ 1];
            } else {
                int opposite = trie[curr][bitP ^ 1];
                if (opposite > 0)
                    validCount += count[opposite];
                curr = trie[curr][bitP];
            }
        }
        if (curr > 0)
            validCount += count[curr]; // leaf node reached i.e. XOR sum exactly equal to k

        return validCount;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        int currXOR = 0;
        long totalSubarrays = 0;
        insert(0);
        for (int i = 0; i < n; ++i) {
            int val = sc.nextInt();
            currXOR ^= val;
            totalSubarrays += query(currXOR, k);
            insert(currXOR);
        }
        System.out.println(totalSubarrays);
    }
}