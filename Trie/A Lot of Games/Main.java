public class Main {

    static int MAX_NODES = 1_00_000 + 5;
    static int[][] trie = new int[MAX_NODES][26];
    static int nodeCnt = 1;
    static boolean[] canWin = new boolean[MAX_NODES];
    static boolean[] canLoose = new boolean[MAX_NODES];

    private static void insert(char[] str) {
        int curr = 1;
        for (char ch : str) {
            int index = ch - 'a';
            if (trie[curr][index] == 0) {
                trie[curr][index] = ++nodeCnt;
            }
            curr = trie[curr][index];
        }
    }

    private static void dfs(int u) {
        boolean isLeaf = true;

        for (int idx = 0; idx < 26; ++idx) {
            int v = trie[u][idx];
            if (v > 0) { // explore node
                dfs(v);
                isLeaf = false;

                // atleast one child is canWin[v] = false
                if (!canWin[v]) canWin[u] = true;
                // atleast one child is canLoose[v] = false
                if (!canLoose[v]) canLoose[u] = true;
            }
        }

        if (isLeaf) {
            canWin[u] = false;
            canLoose[u] = true;
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        for (int i = 0; i < n; ++i) {
            char[] str = sc.next().toCharArray();
            insert(str);
        }

        dfs(1);

        if (canWin[1] && canLoose[1]) System.out.println("First");
        else if (canWin[1]) System.out.println(k % 2 == 0 ? "Second" : "First");
        else System.out.println("Second");

    }
}