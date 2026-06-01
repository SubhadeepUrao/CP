public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int N = sc.nextInt();
            char[] room = sc.next().toCharArray();

            int maxVisit = N;
            for (int i = 0; i < N; ++i) {
                if (room[i] - '0' > 0) {
                    int maxi = Math.max((N - i) << 1, (i + 1) << 1);
                    maxVisit = Math.max(maxi, maxVisit);
                }
            }
            System.out.println(maxVisit);
        }
    }
}