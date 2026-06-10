public class Main {
    private static void solve(FastScanner sc) {
        int N = sc.nextInt();

        int net = 0, prev = 0, spent = 0;
        for (int i = 0; i < N; ++i) {
            int curr = sc.nextInt();
            net += prev - curr;
            if (net < 0) {
                spent -= net;
                net = 0;
            }
            prev = curr;
        }
        System.out.println(spent);
    }
}