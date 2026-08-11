public class Main {

    // Bit-shift helper to pack (r, c) into a single 64-bit long
    private static long pack(long r, long c) {
        return (r << 32) | (c & 0xFFFFFFFFL);
    }

    public static void solve(FastScanner sc) {
        int startR = sc.nextInt();
        int startC = sc.nextInt();
        int destR = sc.nextInt();
        int destC = sc.nextInt();

        int n = sc.nextInt();

        // dist map serves double duty: track allowed cells and store shortest distance
        Map<Long, Integer> dist = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            int r = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            for (int c = a; c <= b; ++c) {
                // -1 indicates the cell is allowed but unvisited
                dist.put(pack(r, c), -1);
            }
        }

        long startKey = pack(startR, startC);
        long destKey = pack(destR, destC);

        Queue<Long> queue = new ArrayDeque<>();
        queue.add(startKey);
        dist.put(startKey, 0);

        int[][] dirs = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        boolean found = false;

        while (!queue.isEmpty()) {
            long currKey = queue.poll();
            int d = dist.get(currKey);

            if (currKey == destKey) {
                System.out.println(d);
                found = true;
                break;
            }

            // Unpack coordinates
            int r = (int) (currKey >> 32);
            int c = (int) currKey;

            for (int[] dir : dirs) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                long nextKey = pack(nextR, nextC);

                // Check if the cell is allowed and not yet visited
                Integer nextDist = dist.get(nextKey);
                if (nextDist != null && nextDist == -1) {
                    dist.put(nextKey, d + 1);
                    queue.add(nextKey);
                }
            }
        }

        if (!found) {
            System.out.println(-1);
        }
    }
}