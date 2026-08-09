// KOSARAJU'S ALGORITHM

public class Main {

    static char[] horizontal, vertical;
    static boolean[][] visited;
    static Deque<int[]> finishStack, stack;
    static int n, m;

    private static void initialize(int n, int m) {
        visited = new boolean[n][m];
        finishStack = new ArrayDeque<>();
        stack = new ArrayDeque<>();
    }

    private static void runIterativeDfs(int startR, int startC) {
        stack.push(new int[] { startR, startC, 0 });

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int r = curr[0];
            int c = curr[1];
            int processed = curr[2];

            if (processed == 1) {
                finishStack.push(new int[] { r, c });
                continue;
            }

            if (visited[r][c])
                continue;
            visited[r][c] = true;

            stack.push(new int[] { r, c, 1 });

            int nextC = horizontal[r] == '>' ? c + 1 : c - 1;
            if (0 <= nextC && nextC < m && !visited[r][nextC]) {
                stack.push(new int[] { r, nextC, 0 });
            }
            int nextR = vertical[c] == 'v' ? r + 1 : r - 1;
            if (0 <= nextR && nextR < n && !visited[nextR][c]) {
                stack.push(new int[] { nextR, c, 0 });
            }
        }
    }

    private static void genFinishStack() {
        // Loop over all cells so no unvisited nodes are missed
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < m; ++c) {
                if (!visited[r][c]) {
                    runIterativeDfs(r, c);
                }
            }
        }
    }

    private static void reverseEdges() {
        for (int i = 0; i < n; ++i) {
            horizontal[i] = horizontal[i] == '>' ? '<' : '>';
        }
        for (int i = 0; i < m; ++i) {
            vertical[i] = vertical[i] == 'v' ? '^' : 'v';
        }
    }

    private static void dfsOnReversed(int startR, int startC) {
        stack.push(new int[] { startR, startC });

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int r = curr[0];
            int c = curr[1];

            // Handle visited state consistently upon popping
            if (visited[r][c])
                continue;
            visited[r][c] = true;

            int nextC = horizontal[r] == '>' ? c + 1 : c - 1;
            if (0 <= nextC && nextC < m && !visited[r][nextC]) {
                stack.push(new int[] { r, nextC });
            }
            int nextR = vertical[c] == 'v' ? r + 1 : r - 1;
            if (0 <= nextR && nextR < n && !visited[nextR][c]) {
                stack.push(new int[] { nextR, c });
            }
        }
    }

    private static int countStronglyConnected() {
        for (int i = 0; i < n; ++i)
            Arrays.fill(visited[i], false);

        int count = 0;

        while (!finishStack.isEmpty()) {
            int[] curr = finishStack.pop();
            int r = curr[0];
            int c = curr[1];
            if (!visited[r][c]) {
                dfsOnReversed(r, c);
                ++count;
            }
        }
        return count;
    }

    private static void solve(FastScanner sc) {
        if (!sc.hasNext())
            return;
        n = sc.nextInt();
        m = sc.nextInt();
        horizontal = sc.next().toCharArray();
        vertical = sc.next().toCharArray();

        initialize(n, m);

        // Kosaraju's Algorithm Execution
        genFinishStack();
        reverseEdges();

        if (countStronglyConnected() == 1)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}