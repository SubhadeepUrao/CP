public class Main {
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
    }

    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        char[] isGood = sc.next().toCharArray();
        int K = sc.nextInt();

        int n = str.length;
        int goodsubstr = 0;
        TrieNode root = new TrieNode();

        for (int i = 0; i < n; ++i) {
            TrieNode curr = root;
            int bad = 0;
            for (int j = i; j < n; ++j) {
                if (isGood[str[j] - 'a'] == '0') ++bad;

                if (bad > K) break;

                int index = str[j] - 'a';

                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                    ++goodsubstr;
                }
                curr = curr.children[index];
            }
        }
        System.out.println(goodsubstr);
    }
}