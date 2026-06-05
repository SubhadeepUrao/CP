public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int maxset = (n + 1) / 2;
        int set = 0;
        int[] drink = new int[k + 1];
        for (int i = 0; i < n; ++i) {
            int drink_type = sc.nextInt();
            if (++drink[drink_type] == 2) {
                ++set;
                drink[drink_type] = 0;
            }
        }
        System.out.println(set == maxset ? set << 1 : (set << 1) + (maxset - set));
    }
}