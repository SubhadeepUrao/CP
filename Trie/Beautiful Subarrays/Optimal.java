
public class Main {

    static final int MAX_NODES = 1_000_000 * 31 + 5;
    
    // Flattened 1D arrays for cache locality & zero object overhead
    static int[] child0 = new int[MAX_NODES];
    static int[] child1 = new int[MAX_NODES];
    static int[] count = new int[MAX_NODES];
    static int nodeCnt = 1;

    private static void insert(int P) {
        int curr = 1;
        ++count[curr];
        for (int i = 30; i >= 0; --i) {
            int bit = (P >> i) & 1;
            if (bit == 0) {
                if (child0[curr] == 0) child0[curr] = ++nodeCnt;
                curr = child0[curr];
            } else {
                if (child1[curr] == 0) child1[curr] = ++nodeCnt;
                curr = child1[curr];
            }
            ++count[curr];
        }
    }

    private static long query(int P, int K) {
        int curr = 1;
        long validCount = 0;

        for (int i = 30; i >= 0; --i) {
            if (curr == 0) return validCount;

            int bitP = (P >> i) & 1;
            int bitK = (K >> i) & 1;

            if (bitK == 1) {
                // Move down the path where bit XORs to 1
                curr = (bitP == 0) ? child1[curr] : child0[curr];
            } else {
                // The opposite branch yields bit XOR = 1 (> bitK = 0), so take all counts
                int opposite = (bitP == 0) ? child1[curr] : child0[curr];
                if (opposite > 0) {
                    validCount += count[opposite];
                }
                // Continue down matching branch (bit XOR = 0)
                curr = (bitP == 0) ? child0[curr] : child1[curr];
            }
        }

        // leaf node reached i.e. XOR sum exactly equal to k
        return validCount + count[curr];
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