public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int half = n >> 1;

            if ((half & 1) == 0) {
                System.out.println("YES");

                System.out.print(2);
                for (int even = 4, i = 2; i <= half; ++i, even += 2)
                    System.out.print(" " + even);

                for (int odd = 1, i = 1; i < half; ++i, odd += 2)
                    System.out.print(" " + odd);
                System.out.println(" " + (3 * half - 1));

            } else
                System.out.println("NO");
        }
    }
}