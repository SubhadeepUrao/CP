public class Solution {
    private static void solve(FastScanner sc) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int x = Math.abs(a - b);
        int y = Math.abs(a - c);
        int z = Math.abs(b - c);

        int res = Math.min(x + y, Math.min(x + z, y + z));

        System.out.print(Integer.valueOf(res));
    }
}