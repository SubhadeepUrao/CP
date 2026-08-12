public class Main {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    static Deque<Point> stack = new ArrayDeque<>();
    static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static boolean[][] visited;
    static char[][] grid;
    static int n, m;

    private static void dfs(int startR, int startC) {
        stack.push(new Point(startR, startC));

        while (!stack.isEmpty()) {
            Point curr = stack.pop();
            int r = curr.r;
            int c = curr.c;
            visited[r][c] = true;

            for (int[] dir : dirs) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                if (0 <= nextR && nextR < n && 0 <= nextC && nextC < m && grid[nextR][nextC] == '#'
                        && !visited[nextR][nextC]) {
                    stack.push(new Point(nextR, nextC));
                }
            }
        }
    }

    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();

        grid = new char[n][];
        for (int i = 0; i < n; ++i) {
            grid[i] = sc.next().toCharArray();
        }

        boolean[] atleastOneBlackInRow = new boolean[n];
        boolean[] atleastOneBlackInCol = new boolean[m];

        // check row contiguity
        for (int i = 0; i < n; ++i) {
            int segments = 0;
            boolean inBlack = false;
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == '#') {
                    atleastOneBlackInRow[i] = true;
                    if (!inBlack) {
                        inBlack = true;
                        ++segments;
                    }
                } else
                    inBlack = false;
            }
            if (segments > 1) {
                System.out.println(-1);
                return;
            }
        }

        // check column contiguity
        for (int j = 0; j < m; ++j) {
            int segments = 0;
            boolean inBlack = false;
            for (int i = 0; i < n; ++i) {
                if (grid[i][j] == '#') {
                    atleastOneBlackInCol[j] = true;
                    if (!inBlack) {
                        inBlack = true;
                        ++segments;
                    }
                } else
                    inBlack = false;
            }
            if (segments > 1) {
                System.out.println(-1);
                return;
            }
        }

        // check all white row/column consistency
        int allWhiteRows = 0;
        int allWhiteCols = 0;
        for (int i = 0; i < n; ++i)
            if (!atleastOneBlackInRow[i])
                ++allWhiteRows;
        for (int j = 0; j < m; ++j)
            if (!atleastOneBlackInCol[j])
                ++allWhiteCols;
        if ((allWhiteRows > 0 && allWhiteCols == 0) || (allWhiteRows == 0 && allWhiteCols > 0)) {
            System.out.println(-1);
            return;
        }

        visited = new boolean[n][m];

        // count connected components
        int components = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == '#' && !visited[i][j]) {
                    dfs(i, j);
                    ++components;
                }
            }
        }
        System.out.println(components);
    }
}