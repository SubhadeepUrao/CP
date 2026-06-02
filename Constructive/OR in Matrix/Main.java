public class Main {
    private static boolean hasOne(int[] A) {
        for (int i = 0; i < A.length; ++i)
            if (A[i] == 1)
                return true;
        return false;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt(); // rows
        int m = sc.nextInt(); // cols
        int[][] B = new int[n][m];

        int[] row = new int[n];
        int[] col = new int[m];
        Arrays.fill(row, 1);
        Arrays.fill(col, 1);

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                B[i][j] = sc.nextInt();
                if (B[i][j] == 0)
                    row[i] = col[j] = 0;
            }

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (B[i][j] == 1 && row[i] == 0 && col[j] == 0) {
                    System.out.println("NO");
                    return;
                }

        if (hasOne(row) != hasOne(col)) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");
        for (int i = 0; i < n; ++i) {
            if (row[i] == 1 && col[0] == 1) System.out.print(1);
            else System.out.print(0);

            for (int j = 1; j < m; ++j)
                if (row[i] == 1 && col[j] == 1) System.out.print(" " + 1);
                else System.out.print(" " + 0);

            System.out.println();
        }
    }
}