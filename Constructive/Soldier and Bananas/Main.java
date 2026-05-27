public class Main {
    private static void solve(FastScanner sc) {
        int k = sc.nextInt(); // cost of first banana
        int n = sc.nextInt(); // dollars left
        int w = sc.nextInt(); // bananas needed
        int res = ((w * (w + 1)) >> 1) * k - n;

        if (res > 0)
            System.out.println(res);
        else
            System.out.println(0);
    }
}