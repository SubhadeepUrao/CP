public class Main {

    static int MAX_NODES = 200_000 * 31 + 5;
    static int[][] trie = new int[MAX_NODES][2];
    static int[] count = new int[MAX_NODES];
    static int nodeCnt = 1;

    private static void insert(int value) {
        int curr = 1; // root
        ++count[curr];
        for (int i = 29; i >= 0; --i) {
            int bit = (value >>> i) & 1;
            if (trie[curr][bit] == 0)
                trie[curr][bit] = ++nodeCnt;
            curr = trie[curr][bit];
            ++count[curr];
        }
    }

    private static void remove(int value) {
        int curr = 1; // root
        --count[curr];
        for (int i = 29; i >= 0; --i) {
            int bit = (value >>> i) & 1;
            curr = trie[curr][bit];
            --count[curr];
        }
    }

    private static int query(int value) {
        int curr = 1;
        int maxXOR = 0;
        for (int i = 29; i >= 0; --i) {
            int bit = (value >>> i) & 1;
            int desired = 1 - bit;
            if (trie[curr][desired] > 0 && count[trie[curr][desired]] > 0) {
                maxXOR |= (1 << i);
                curr = trie[curr][desired];
            } else {
                curr = trie[curr][bit];
            }
        }
        return maxXOR;
    }

    private static void solve(FastScanner sc) {
        int q = sc.nextInt();
        StringBuilder res = new StringBuilder();

        insert(0);

        while (q-- > 0) {
            char type = sc.next().charAt(0);
            int x = sc.nextInt();
            if (type == '+') insert(x);
            else if (type == '-') remove(x);
            else res.append(query(x)).append('\n');
        }
        System.out.println(res);
    }
}