public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int rem = a % b;
            System.out.println(rem > 0 ? b - rem : 0);
        }
    }
}