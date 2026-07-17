// TLE

public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; ++i)
            q.add(sc.nextInt());

        int q_len = 0, newQ_len = 0;
        int step = 0;
        do {
            q_len = q.size();
            Queue<Integer> newQ = new LinkedList<>();

            int prev = q.poll();
            newQ.add(prev);

            while (!q.isEmpty()) {
                int curr = q.poll();
                if (prev <= curr)
                    newQ.add(curr);
                prev = curr;
            }

            newQ_len = newQ.size();
            q.clear();
            q = newQ;

            if (q_len != newQ_len)
                ++step;
        } while (q_len != newQ_len);

        System.out.println(step);
    }
}