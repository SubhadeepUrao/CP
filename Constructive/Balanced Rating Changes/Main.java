public class Main {
    private static void solve(FastScanner sc) {
        int N = sc.nextInt();
        int[] res = new int[N];

        int pairAvail = -1; // odd position
        for (int i = 0; i < N; ++i) {
            int num = sc.nextInt();
            if ((num & 1) == 0)
                res[i] = num >> 1;
            else if (pairAvail >= 0) {
                res[pairAvail] >>= 1;
                res[i] = (num >> 1) + 1;
                pairAvail = -1;
            } else {
                pairAvail = i;
                res[i] = num;
            }
        }
        for (int i = 0; i < N; ++i)
            System.out.println(res[i]);
    }
}