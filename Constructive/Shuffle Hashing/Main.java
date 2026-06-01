public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            char[] target = sc.next().toCharArray(); // p
            int n = target.length;
            char[] str = sc.next().toCharArray(); // h
            int N = str.length;

            Map<Character, Integer> mpp = new HashMap<>();

            for (char ch : target) {
                mpp.put(ch, mpp.getOrDefault(ch, 0) + 1);
            }

            int i = 0;
            for (; i + n <= N; ++i) {
                if (mpp.containsKey(str[i])) {
                    mpp.put(str[i], mpp.get(str[i]) - 1);
                    int j = i + 1;
                    for (; j < i + n; ++j) {
                        int val = mpp.getOrDefault(str[j], 0);
                        if (val > 0) { // valid sequence parse
                            mpp.put(str[j], val - 1);
                        } else { // undo changes
                            for (int k = j - 1; k >= i; --k) {
                                mpp.put(str[k], mpp.get(str[k]) + 1);
                            }
                            break;
                        }
                    }
                    if (j == i + n) {
                        System.out.println("YES");
                    }
                }
            }
            if (i + n > N) {
                System.out.println("NO");
            }
        }
    }
}