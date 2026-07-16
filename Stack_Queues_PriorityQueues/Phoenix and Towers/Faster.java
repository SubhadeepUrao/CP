public class Main {
    static class Tower {
        int height;
        int id;

        Tower(int height, int id) {
            this.height = height;
            this.id = id;
        }

        // getter method
        public int getHeight() {
            return this.height;
        }
    }

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(); // #blocks
            int m = sc.nextInt(); // #towers
            int x = sc.nextInt(); // maximum acceptable height difference of any two towers

            int[] blocks = new int[n];
            for (int i = 0; i < n; ++i)
                blocks[i] = sc.nextInt();

            // much faster approach
            Queue<Tower> pq = new PriorityQueue<>(Comparator.comparingInt(Tower::getHeight));
            for (int i = 1; i <= m; ++i)
                pq.add(new Tower(0, i));

            int[] assign = new int[n]; // assign[i] --> Block i is assigned to tower j (1<=j<= m)
            for (int i = 0; i < n; ++i) {
                Tower min = pq.poll();
                min.height += blocks[i];
                assign[i] = min.id;
                pq.add(min);
            }

            StringBuilder str = new StringBuilder();
            str.append("YES\n");
            for (int i = 0; i < n; ++i)
                str.append(assign[i]).append(i == n - 1 ? "" : " ");

            System.out.println(str);
        }
    }
}