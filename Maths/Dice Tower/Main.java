public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            long target = sc.nextLong();

            // every covered dice contributes 14 points
            long rem = target % 14;

            System.out.println(target > 14 && rem >= 1 && rem <= 6 ? "YES" : "NO");
        }
    }
}