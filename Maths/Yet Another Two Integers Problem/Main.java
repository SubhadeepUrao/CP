public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            int target = b - a;
            System.out.println(target / 10 + (target % 10 > 0 ? 1 : 0));
        }
    }
}