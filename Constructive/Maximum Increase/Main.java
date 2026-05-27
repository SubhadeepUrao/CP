public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        int prev = 1_000_000_001, count = 1;
        int maxi = 1;
        while (t-- > 0) {
            int num = sc.nextInt();
            if (prev < num) ++count;
            else {
                maxi = Math.max(maxi, count);
                count = 1;
            }
            prev = num;
        }
        maxi = Math.max(maxi, count);
        System.out.println(maxi);
    }
}