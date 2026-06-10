public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            long a = sc.nextLong();
            long b = sc.nextLong();

            int a_steps = 0, b_steps = 0;
            if ((x >= 0 && y >= 0) || (x <= 0 && y <= 0)) {
                int mini = Math.min(x, y);
                a_steps = x - mini + y - mini;
                b_steps = Math.abs(mini);
                System.out.println(Math.min(a * a_steps + b * b_steps, a * (a_steps + (b_steps << 1))));
            } else {
                a_steps = Math.abs(x) + Math.abs(y);
                System.out.println(a * a_steps + b * b_steps);
            }
        }
    }
}