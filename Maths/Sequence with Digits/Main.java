public class Main {
    private static int prodMinMaxDigit(long n) {
        int mini = 9, maxi = 0;
        while (n > 0) {
            int d = (int) (n % 10);
            mini = Math.min(mini, d);
            maxi = Math.max(maxi, d);
            n /= 10;
        }
        return mini * maxi;
    }

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            long a = sc.nextLong();
            long K = sc.nextLong();

            while (--K > 0) {
                int prod = prodMinMaxDigit(a);
                // probability for tens and units place to hit ZERO is 19%
                // with each steps the probability to doze zero shrinks exponentially
                if (prod == 0) break;
                a += prod;
            }
            System.out.println(a);
        }
    }
}