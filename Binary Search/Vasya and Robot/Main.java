public class Main {
    private static boolean isPossible(int window, int x, int y) {
        // add path to the window i.e. path in the window must be excluded
        // beacuse we can form the needed displacement from the window path id possible
        // we undo displacement i.e. reverse addition and subtraction
        for (int i = 0; i < window; ++i) {
            if (path[i] == 'R') --x;
            else if (path[i] == 'L') ++x;
            else if (path[i] == 'U') --y;
            else ++y;
        }

        int dist = Math.abs(target_x - x) + Math.abs(target_y - y);
        if (dist <= window && ((window - dist) & 1) == 0)
            return true;

        int prev = 0;
        for (int curr = window; curr < n; ++curr) {
            // remove path at prev position
            if (path[prev] == 'R') ++x;
            else if (path[prev] == 'L') --x;
            else if (path[prev] == 'U') ++y;
            else --y;

            // add path at curr position
            if (path[curr] == 'R') --x;
            else if (path[curr] == 'L') ++x;
            else if (path[curr] == 'U') --y;
            else ++y;

            dist = Math.abs(target_x - x) + Math.abs(target_y - y);
            if (dist <= window && ((window - dist) & 1) == 0)
                return true;

            ++prev;
        }
        return false;
    }

    private static int n = 0, target_x = 0, target_y = 0;
    private static char[] path;

    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        path = sc.next().toCharArray();
        target_x = sc.nextInt();
        target_y = sc.nextInt();

        int minpath = Math.abs(target_x) + Math.abs(target_y);
        if (minpath > n || ((n - minpath) & 1) == 1) {
            System.out.println(-1);
            return;
        }

        // calculate total displacement
        int x = 0, y = 0;
        for (int i = 0; i < n; ++i) {
            if (path[i] == 'R') ++x;
            else if (path[i] == 'L') --x;
            else if (path[i] == 'U') ++y;
            else --y;
        }

        int low = 0;
        int high = n;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (isPossible(mid, x, y))
                high = mid - 1;
            else
                low = mid + 1;
        }
        System.out.println(low);
    }
}