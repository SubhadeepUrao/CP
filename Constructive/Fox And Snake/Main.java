public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < n; ++i) {
            if ((i & 1) == 0)
                for (int j = 0; j < m; ++j)
                    System.out.print('#');
            else {
                if (i % 4 == 3) System.out.print('#');
                for (int j = 1; j < m; ++j)
                    System.out.print('.');
                if (i % 4 == 1) System.out.print('#');
            }
            System.out.println();
        }
    }
}