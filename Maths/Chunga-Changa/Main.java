public class Main {
    private static void solve(FastScanner sc) {
        long x = sc.nextLong();
        long y = sc.nextLong();
        long z = sc.nextLong();

        long rem_x = x % z;
        long rem_y = y % z;

        System.out.println((x + y) / z + " " + (rem_x + rem_y >= z ? z - Math.max(rem_x, rem_y) : 0));
    }
}