public class Main {
    private static void solve(FastScanner sc) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int s = sc.nextInt();

        int steps = Math.abs(a) + Math.abs(b);

        if (s >= steps && ((s - steps) & 1) == 0)
            System.out.println("YES");
        else
            System.out.println("NO");

        // if (steps > s) System.out.println("NO");
        // else if (steps == s || ((steps & 1) == 0 && (s & 1) == 0) || ((steps & 1) == 1 && (s & 1) == 1)) System.out.println("YES");
        // else System.out.println("NO");
    }
}