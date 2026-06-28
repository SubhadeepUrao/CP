public class Main {
    private static void solve(FastScanner sc) {

        int MAX = 1_00_000;
        int[] lastseen = new int[MAX + 1];

        StringBuilder str = new StringBuilder();

        int n = sc.nextInt();
        for (int i = 1; i <= n; ++i) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int count = 0;

            int window_start = i - y; // starting index of the window to consider for 'x'
            for (int factor = 1; factor * factor <= x; ++factor) {
                if (x % factor == 0) {
                    if (lastseen[factor] < window_start)
                        ++count;
                    lastseen[factor] = i; // store the latest sighting i.e. index

                    int otherfactor = x / factor;
                    if (otherfactor != factor) {
                        if (lastseen[otherfactor] < window_start)
                            ++count;
                        lastseen[otherfactor] = i;
                    }
                }
            }
            str.append(count).append('\n');
        }
        System.out.println(str);
    }
}