public class Main {
    private static void solve(FastScanner sc) {
        int pages = sc.nextInt();

        int[] days = new int[7];
        days[0] = sc.nextInt();
        for (int i = 1; i < 7; ++i)
            days[i] = sc.nextInt() + days[i - 1];

        while (pages > days[6])
            pages -= days[6];

        int lb = 0, ub = 6;
        while (lb <= ub) {
            int mid = lb + ub >> 1;
            if (days[mid] < pages)
                ++lb;
            else
                ub = --mid;
        }

        System.out.println(lb + 1);
    }
}