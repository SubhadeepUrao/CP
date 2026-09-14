public class Main {

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        String[] phoneNumbers = new String[n];

        Map<String, Integer> freq = new HashMap<>();
        Set<String> unique = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            phoneNumbers[i] = sc.next();

            for (int l = 0; l < 9; ++l) {
                for (int r = l; r < 9; ++r) {
                    unique.add(phoneNumbers[i].substring(l, r + 1));
                }
            }

            for (String substring : unique)
                freq.put(substring, freq.getOrDefault(substring, 0) + 1);

            unique.clear();
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            String best = phoneNumbers[i];
            boolean foundBest = false;
            for (int len = 1; len <= 9; ++len) {
                for (int start = 0; start <= 9 - len; ++start) {
                    String subString = phoneNumbers[i].substring(start, start + len);
                    if (freq.get(subString) == 1) {
                        best = subString;
                        foundBest = true;
                        break;
                    }
                }
                if (foundBest)
                    break;
            }
            res.append(best).append('\n');
        }
        System.out.println(res);
    }
}