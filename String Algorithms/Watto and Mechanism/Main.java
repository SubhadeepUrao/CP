public class Main {
    private static void insert(char[] str) {
        int curr = 0;
        for (char ch : str) {
            int index = ch - 'a';
            if (trie[curr][index] == 0) {
                trie[curr][index] = nodeCount++;
            }
            curr = trie[curr][index];
        }
        isEnd[curr] = true;
    }

    private static int[][] trie = new int[6_00_005][3];
    private static int nodeCount = 1;
    private static boolean[] isEnd = new boolean[6_00_005];

    private static boolean search(int i, int currNode, boolean substituted, char[] str) {
        if (i == str.length) {
            // Must end on a valid word AND have exactly 1 substitution done
            return isEnd[currNode] && substituted;
        }

        int nextIdx = str[i] - 'a';
        for (int idx = 0; idx < 3; ++idx) {
            int nextNode = trie[currNode][idx];
            if (nextNode == 0)
                continue;

            if (idx == nextIdx) {
                // no need for substitute
                if (search(i + 1, nextNode, substituted, str))
                    return true;
            } else if (!substituted) {
                // using substitute exactly once
                if (search(i + 1, nextNode, true, str))
                    return true;
            }
        }

        return false;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        while (n-- > 0) {
            char[] str = sc.next().toCharArray();
            insert(str);
        }

        StringBuilder res = new StringBuilder();
        while (m-- > 0) {
            char[] str = sc.next().toCharArray();
            if (search(0, 0, false, str))
                res.append("YES\n");
            else
                res.append("NO\n");
        }

        System.out.println(res);
    }
}