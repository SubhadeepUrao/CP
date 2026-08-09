public class Main {

    static char[] horizontal, vertical;
    static boolean[][] visited;
    static Deque<int[]> queue;

    private static void initialize(int n, int m) {
        visited = new boolean[n][m];
        queue = new ArrayDeque<>();
    }

    private static int countReachable(int startR, int startC, int n, int m) {
        for (int i = 0; i < n; ++i)
            Arrays.fill(visited[i], false);

        queue.add(new int[] { startR, startC });
        visited[startR][startC] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            ++count;

            int nextC = horizontal[r] == '>' ? c + 1 : c - 1;
            if (0 <= nextC && nextC < m && !visited[r][nextC]) {
                visited[r][nextC] = true;
                queue.add(new int[] { r, nextC });
            }

            int nextR = vertical[c] == 'v' ? r + 1 : r - 1;
            if (0 <= nextR && nextR < n && !visited[nextR][c]) {
                visited[nextR][c] = true;
                queue.add(new int[] { nextR, c });
            }
        }

        return count;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        initialize(n, m);

        horizontal = sc.next().toCharArray();
        vertical = sc.next().toCharArray();

        int total = n * m;
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < m; ++c) {
                if (countReachable(r, c, n, m) != total) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}