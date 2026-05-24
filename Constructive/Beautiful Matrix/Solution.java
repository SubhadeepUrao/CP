public class Solution {
    private static void solve(FastScanner sc) {
        for (int i = 0; i < 5; ++i)
            for (int j = 0; j < 5; ++j)
                if (sc.nextInt() == 1) {
                    System.out.println(Math.abs(i - 2) + Math.abs(j - 2));
                    return;
                }
    }
}
