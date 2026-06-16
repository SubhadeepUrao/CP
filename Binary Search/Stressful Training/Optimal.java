public class Main {
    private static boolean isValid(long x, long[] charge, long[] decay, int k,
            long[] currcharge, int[] head, int[] next) {
        int n = charge.length;

        // Reset arrays to empty state (-1 signifies end of list)
        Arrays.fill(head, 0, k, -1);

        // Distribute laptops into initial death-time buckets
        for (int i = 0; i < n; ++i) {
            currcharge[i] = charge[i];
            long initialDeath = charge[i] / decay[i];
            int deathtime = (initialDeath >= k) ? k : (int) initialDeath;

            if (deathtime < k) {
                // Insert laptop 'i' into the front of the linked list for 'deathtime'
                next[i] = head[deathtime];
                head[deathtime] = i;
            }
        }

        int urgentBucket = 0;
        for (int t = 0; t < k; ++t) {
            // Find the earliest bucket that contains a laptop
            while (urgentBucket < k && head[urgentBucket] == -1) {
                ++urgentBucket;
            }

            // No laptops need charging anywhere before minute k
            if (urgentBucket == k) {
                continue;
            }

            // A laptop died before we could charge it
            if (urgentBucket < t) {
                return false;
            }

            // Pop exactly ONE laptop out of the urgent bucket list (O(1))
            int index = head[urgentBucket];
            head[urgentBucket] = next[index]; // Move head pointer to the next element

            // Charge the laptop
            currcharge[index] += x;

            // Calculate next death time safely
            long nextDeathLong = currcharge[index] / decay[index];
            int deathtime = (nextDeathLong >= k) ? k : (int) nextDeathLong;

            if (deathtime < k) {
                // Push back into its new bucket
                next[index] = head[deathtime];
                head[deathtime] = index;

                // If it needs immediate/earlier attention, pull back our bucket pointer
                if (deathtime < urgentBucket) {
                    urgentBucket = deathtime;
                }
            }
        }
        return true;
    }

    private static void solve(FastScanner sc) {
        if (!sc.hasNext())
            return;
        int n = sc.nextInt();
        int k = sc.nextInt();

        long[] charge = new long[n];
        for (int i = 0; i < n; ++i)
            charge[i] = sc.nextLong();

        long[] decay = new long[n];
        for (int i = 0; i < n; ++i)
            decay[i] = sc.nextLong();

        // ALLOCATE MEMORY ONCE: Reuse these arrays across all binary search steps
        long[] currcharge = new long[n];
        int[] head = new int[k]; // Stores the index of the first laptop that dies at time t
        int[] next = new int[n]; // Stores the index of the next laptop that dies at the same time as laptop i

        long low = 0;
        long high = 2_000_000_000_000L;
        long ans = -1;

        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (isValid(mid, charge, decay, k, currcharge, head, next)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(ans);
    }
}