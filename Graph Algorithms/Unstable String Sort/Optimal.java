public class Main {

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] P = new int[n];
        int[] Q = new int[n];

        for (int i = 0; i < n; ++i) P[i] = sc.nextInt();
        for (int i = 0; i < n; ++i) Q[i] = sc.nextInt();

        Set<Integer> box = new HashSet<>();
        int[] groupId = new int[n + 1];
        int groupCnt = 0;

        for (int i = 0; i < n; ++i) {
            if (box.contains(P[i])) box.remove(P[i]);
            else box.add(P[i]);

            if (box.contains(Q[i])) box.remove(Q[i]);
            else box.add(Q[i]);

            groupId[P[i]] = groupCnt;

            if (box.isEmpty()) ++groupCnt;
        }

        if (groupCnt < k) {
            System.out.println("NO");
            return;
        }

        StringBuilder res = new StringBuilder();
        res.append("YES\n");
        for (int i = 1; i <= n; ++i) {
            int index = Math.min(25, groupId[i]);
            res.append((char) ('a' + index));
        }
        System.out.println(res);
    }
}