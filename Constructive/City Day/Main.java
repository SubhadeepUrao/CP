public class Main {
    private static void keepMinAtFront(Queue<Integer> q, int curr) {
        while (q.element() > curr)
            q.remove();
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        int[] rain = new int[n + 1];
        for (int i = 0; i < n; ++i)
            rain[i] = sc.nextInt();
        rain[n] = 1_000_000_001;

        Queue<Integer> prev = new ArrayDeque<>();
        Queue<Integer> next = new ArrayDeque<>();

        // populating prev queue
        prev.add(1_000_000_001);

        // populating next queue
        next.add(1_000_000_001);
        int windowL = Math.min(n, y);
        for (int i = 1; i <= windowL; ++i) {
            next.add(rain[i]);
            while (next.element() > rain[i])
                next.remove();
        }

        for (int i = 0; i < n; ++i) {
            int curr = rain[i];
            if (prev.element() > curr && curr < next.element()) {
                System.out.println(i + 1);
                return;
            }

            prev.add(curr);
            if (x <= i + 1 && prev.element() == rain[i + 1 - x])
                prev.remove();
            keepMinAtFront(prev, curr);

            if (next.element() == rain[i + 1])
                next.remove();
            if (i + y + 1 <= n) {
                int temp = rain[i + y + 1];
                next.add(temp);
                keepMinAtFront(next, temp);
            }
        }
    }
}