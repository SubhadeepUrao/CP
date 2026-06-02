public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt(); // rows
        int m = sc.nextInt(); // cols
        int[][] B = new int[n][m];

        int[] rowB = new int[n];
        int[] colB = new int[m];
        Arrays.fill(rowB, 1);
        Arrays.fill(colB, 1);

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                B[i][j] = sc.nextInt();
                if (B[i][j] == 0)
                    rowB[i] = colB[j] = 0;
            }

        int[][] A = new int[n][m];
        int[] rowA = new int[n];
        int[] colA = new int[m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (rowB[i] == 1 && colB[j] == 1) // A[i][j] = 1
                    A[i][j] = rowA[i] = colA[j] = 1;

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (((rowA[i] != 1 && colA[j] != 1)) && B[i][j] != 0) {
                    System.out.println("NO");
                    return;
                }

        System.out.println("YES");
        for (int i = 0; i < n; ++i) {
            System.out.print(A[i][0]);
            for (int j = 1; j < m; ++j)
                System.out.print(" " + A[i][j]);
            System.out.println();
        }
    }
}