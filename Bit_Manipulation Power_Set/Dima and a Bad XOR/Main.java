public class Main {
    private static void printFirstIndexOfEachRow(int start, int end) {
        for (int i = start; i < end; ++i)
            System.out.print(1 + " ");
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        int xorSum = 0;
        int[][] a = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                a[i][j] = sc.nextInt();
            }
            xorSum ^= a[i][0]; // xor sum of the first column
        }

        if (xorSum > 0) {
            System.out.println("TAK");
            printFirstIndexOfEachRow(0, n);
            return;
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (a[i][j] != a[i][0]) {
                    System.out.println("TAK");
                    printFirstIndexOfEachRow(0, i);
                    System.out.print((j + 1) + " "); // 1-index
                    printFirstIndexOfEachRow(i + 1, n);
                    return;
                }
            }
        }

        System.out.println("NIE");
    }
}