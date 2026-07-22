public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        char[] isGood = sc.next().toCharArray();
        int K = sc.nextInt();

        int n = str.length;
        int goodsubstr = 0;
        final int[][] trie = new int[1126000][26];
        int nodeCount = 1; // Node 0 is the root

        for (int i = 0; i < n; ++i) {
            int curr = 0;
            int bad = 0;
            for (int j = i; j < n; ++j) {

                if (isGood[str[j] - 'a'] == '0') ++bad;
                if (bad > K) break;

                int index = str[j] - 'a';

                if (trie[curr][index] == 0) {
                    trie[curr][index] = nodeCount++;
                    ++goodsubstr;
                }
                curr = trie[curr][index];
            }
        }
        System.out.println(goodsubstr);
    }
}