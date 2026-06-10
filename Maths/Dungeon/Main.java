public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            int sum = a + b + c;
            int atleast = sum / 9;

            if (sum % 9 != 0 || Math.min(a, Math.min(b, c)) < atleast)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }
}