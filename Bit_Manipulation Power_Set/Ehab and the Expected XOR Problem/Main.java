public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int x = sc.nextInt();

        int LIMIT = 1 << n;

        boolean[] visited = new boolean[LIMIT];

        visited[0] = true;
        if (x < LIMIT)
            visited[x] = true;

        // if x >= LIMIT then we could have just printed all elements i.e. [1, LIMIT);

        List<Integer> prefix = new ArrayList<>(); // valid prefix sequence

        for (int i = 1; i < LIMIT; ++i) {
            if (!visited[i]) {
                prefix.add(i);
                visited[i] = true;
                if ((i ^ x) < LIMIT)
                    visited[i ^ x] = true;
            }
        }

        System.out.println(prefix.size());

        StringBuilder str = new StringBuilder();
        int prev = 0;
        for (int i : prefix) {
            str.append(prev ^ i).append(' ');
            prev = i;
        }
        System.out.println(str);
    }
}