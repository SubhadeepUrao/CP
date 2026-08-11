public class Main {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object ob) {
            if (this == ob)
                return true;
            if (ob == null || getClass() != ob.getClass())
                return false;
            Point point = (Point) ob;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

    }

    private static void solve(FastScanner sc) {
        int startR = sc.nextInt();
        int startC = sc.nextInt();
        int destR = sc.nextInt();
        int destC = sc.nextInt();

        int n = sc.nextInt();

        Set<Point> allowed = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            int r = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            for (int c = a; c <= b; ++c)
                allowed.add(new Point(r, c));
        }

        Deque<Point> queue = new ArrayDeque<>();
        int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

        Map<Point, Integer> dist = new HashMap<>();

        Point start = new Point(startR, startC);
        Point dest = new Point(destR, destC);

        queue.add(start);
        dist.put(start, 0);

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            int r = curr.r;
            int c = curr.c;
            int d = dist.get(curr);

            if (curr.equals(dest)) {
                System.out.println(d);
                return;
            }

            for (int[] dir : dirs) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                Point next = new Point(nextR, nextC);

                if (allowed.contains(next) && !dist.containsKey(next)) {
                    dist.put(next, d + 1);
                    queue.add(next);
                }
            }

        }
        System.out.println(-1);
    }
}