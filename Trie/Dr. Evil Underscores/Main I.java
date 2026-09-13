public class Main {

    public static int solve(List<Integer> list, int bit) {
        if (bit < 0 || list.isEmpty()) {
            return 0;
        }

        List<Integer> v0 = new ArrayList<>();
        List<Integer> v1 = new ArrayList<>();

        for (int x : list) {
            if (((x >> bit) & 1) == 1) {
                v1.add(x);
            } else {
                v0.add(x);
            }
        }

        if (v0.isEmpty()) return solve(v1, bit - 1);
        if (v1.isEmpty()) return solve(v0, bit - 1);

        return (1 << bit) + Math.min(solve(v0, bit - 1), solve(v1, bit - 1));
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        List<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        System.out.println(solve(a, 29));
    }
}