public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt(); // origin
        int m = sc.nextInt(); // dest

        int steps = 0;
        while (m > n) {
            if ((m & 1) == 0) // even
                m >>>= 1; // div by 2 (inverse of x2)
            else
                m += 1; // add 1 (inverse of -1)
            ++steps;
        }

        steps += n - m;
        System.out.println(steps);
    }
}