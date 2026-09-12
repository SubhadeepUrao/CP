public class Main {

    static final int MAX_NODES = 3_00_000 * 31 + 5;
    static int[][] trie = new int[MAX_NODES][2];
    static int[] count = new int[MAX_NODES];
    static int nodeCnt = 1;

    private static void insert(int val) {
        int curr = 1;
        ++count[curr];
        for (int i = 29; i >= 0; --i) {
            int bit = (val >> i) & 1;
            if (trie[curr][bit] == 0) {
                trie[curr][bit] = ++nodeCnt;
            }
            curr = trie[curr][bit];
            ++count[curr];
        }
    }

    private static int queryAndRemove(int val) {
        int curr = 1;
        int bestMatch = 0;
        --count[curr];
        for (int i = 29; i >= 0; --i) {
            int bit = (val >> i) & 1;
            int opposite_bit = 1 - bit;

            if (trie[curr][bit] > 0 && count[trie[curr][bit]] > 0) {
                bestMatch |= (bit << i);
                curr = trie[curr][bit];
            } else {
                bestMatch |= (opposite_bit << i);
                curr = trie[curr][opposite_bit];
            }
            --count[curr];
        }
        return val ^ bestMatch;
    }

    private static void solve(FastScanner sc) {
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; ++i)
            A[i] = sc.nextInt();

        for (int i = 0; i < N; ++i) {
            int P = sc.nextInt();
            insert(P);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            int minXOR = queryAndRemove(A[i]);
            res.append(minXOR).append(' ');
        }
        System.out.println(res);
    }
}