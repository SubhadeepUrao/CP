public class Main {

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        String[] phoneNumbers = new String[n];

        Map<String, Integer> freq = new HashMap<>();
        Set<String>[] unique = new HashSet[n];
        for (int i = 0; i < n; ++i) {
            phoneNumbers[i] = sc.next();
            unique[i] = new HashSet<>();
            for (int l = 0; l < 9; ++l) {
                for (int r = l; r < 9; ++r) {
                    unique[i].add(phoneNumbers[i].substring(l, r + 1));
                }
            }

            for (String substring : unique[i])
                freq.put(substring, freq.getOrDefault(substring, 0) + 1);
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            String best = phoneNumbers[i];
            int bestLen = 9;
            for (String substring : unique[i]) {
                int len = substring.length();
                if (freq.get(substring) == 1 && len < bestLen) {
                    best = substring;
                    bestLen = len;
                }
            }
            res.append(best).append('\n');
        }
        System.out.println(res);
    }
}