public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int rows = (n << 1) + 1;

        for (int i = 0; i < rows; ++i) {
            // print spaces
            int spaces = Math.abs(n - i);
            for (int j = 0; j < spaces; ++j)
                System.out.print("  ");

            // print digits
            int m = n - spaces;
            int digits = m * 2 + 1;
            System.out.print(0);
            for (int j = 1; j < digits; ++j)
                System.out.print(" " + (m - Math.abs(m - j)));
                // m - | m - j | ==> 2 * min(m, j) - j
                // System.out.print(" " + (2 * Math.min(m, j) - j));

            System.out.println();
        }
    }
}