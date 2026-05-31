public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        int twos = n / 2;
        int ones = n % 2;

        do {
            int steps = twos + ones;
            if (steps % m == 0) {
                System.out.println(steps);
                return;
            }
            --twos;
            ones += 2;
        } while (twos >= 0);

        System.out.println(-1);
    }
}