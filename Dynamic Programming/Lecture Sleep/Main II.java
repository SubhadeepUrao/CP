public class Main {

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] a = new int[n];

        long base = 0;
        for (int i = 0; i < n; ++i)
            a[i] = sc.nextInt();
        int[] type = new int[n];
        for (int i = 0; i < n; ++i) {
            type[i] = sc.nextInt();
            if (type[i] == 1)
                base += a[i];
        }

        long currGain = 0;
        for (int i = 0; i < k; ++i)
            if (type[i] == 0)
                currGain += a[i];

        long maxGain = currGain;
        for (int i = k; i < n; ++i) {
            if (type[i - k] == 0)
                currGain -= a[i - k];

            if (type[i] == 0) {
                currGain += a[i];
                maxGain = Math.max(maxGain, currGain);
            }
        }
        System.out.println(base + maxGain);
    }
}