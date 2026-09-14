public class Main {

    static int MAX_NODES = 70_000 * 45;
    static int[][] trie = new int[MAX_NODES][10];
    static int nodeCnt = 1;
    static int[] count = new int[MAX_NODES];
    static int[] lastVisited = new int[MAX_NODES];

    private static void insert(char[] phoneNumber, int start, int end, int phoneId) { // end is inclusive
        int curr = 1;
        for (int i = start; i <= end; ++i) {
            int digit = phoneNumber[i] - '0';
            if (trie[curr][digit] == 0) {
                trie[curr][digit] = ++nodeCnt;
            }
            curr = trie[curr][digit];
        }
        if (lastVisited[curr] != phoneId) {
            lastVisited[curr] = phoneId;
            ++count[curr];
        }
    }

    private static int getFrequency(char[] phoneNumber, int start, int end) { // end is exclusive
        int curr = 1;
        for (int i = start; i < end; ++i) {
            int digit = phoneNumber[i] - '0';
            curr = trie[curr][digit];
        }
        return count[curr];
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        char[][] phoneNumbers = new char[n][9];

        for (int i = 0; i < n; ++i) {
            phoneNumbers[i] = sc.next().toCharArray();

            for (int l = 0; l < 9; ++l) {
                for (int r = l; r < 9; ++r) {
                    insert(phoneNumbers[i], l, r, i + 1);
                }
            }
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            int startBest = 0;
            int endBest = 0; // end is exclusive
            boolean foundBest = false;
            char[] phoneNumber = phoneNumbers[i];
            for (int len = 1; len <= 9; ++len) {
                for (int start = 0; start <= 9 - len; ++start) {
                    if (getFrequency(phoneNumbers[i], start, start + len) == 1) {
                        startBest = start;
                        endBest = start + len;
                        foundBest = true;
                        break;
                    }
                }
                if (foundBest) break;
            }
            for (int j = startBest; j < endBest; ++j)
                res.append(phoneNumber[j]);
            res.append('\n');
        }
        System.out.println(res);
    }
}