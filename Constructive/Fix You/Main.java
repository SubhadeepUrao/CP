public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            char[][] str = new char[n][];
            for (int i = 0; i < n; ++i)
                str[i] = sc.next().toCharArray();

            int lastRow = n - 1;
            int lastCol = m - 1;
            int changes = 0;
            for (int j = 0; j < m; ++j) if (str[lastRow][j] == 'D') ++changes;
            for (int i = 0; i < n; ++i) if (str[i][lastCol] == 'R') ++changes;

            System.out.println(changes);
        }
    }
}