// TLE
public class Main {
    private static boolean isValid(long x, long[] currcharge, long[] charge, long[] usage, int k) {
        int n = charge.length;

        // (death time, index)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n; ++i) {
            currcharge[i] = charge[i];
            long initTime = charge[i] / usage[i];
            int deathtime = initTime >= k ? k : (int) initTime;
            if (deathtime < k)
                pq.add(new int[] { deathtime, i });
        }

        for (int t = 0; t < k; ++t) {
            if (pq.isEmpty())
                return true;

            int[] top = pq.poll();
            // already dead
            if (top[0] < t)
                return false;

            // charge laptop
            int index = top[1];
            currcharge[index] += x;

            // update new (death time, index) in queue
            long initTime = currcharge[index] / usage[index];
            int deathtime = initTime >= k ? k : (int) initTime;
            if (deathtime < k)
                pq.add(new int[] { deathtime, index });
        }
        return true;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        long[] currcharge = new long[n];
        long[] charge = new long[n];
        for (int i = 0; i < n; ++i)
            charge[i] = sc.nextLong();

        long[] usage = new long[n];
        for (int i = 0; i < n; ++i)
            usage[i] = sc.nextLong();

        long low = 0;
        long high = 2_000_000_000_000L;
        long ans = -1;

        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (isValid(mid, currcharge, charge, usage, k)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        System.out.println(ans);
    }
}