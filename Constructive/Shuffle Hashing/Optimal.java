public class Optimal {
    private static void solve(Scanner sc, FileWriter output) throws IOException {
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
                        if (!mpp.containsKey(str[j])) { // undo changes
                            for (int k = j - 1; k >= i; --k) {
                                mpp.put(str[k], mpp.get(str[k]) + 1);
                            }
                            i = j;
                            break;
                        } else {
                            int val = mpp.get(str[j]);
                            if (val > 0)
                                mpp.put(str[j], val - 1);
                            else { // increment i and undo values until hash[str[j]] > 0
                                while (mpp.get(str[j]) == 0) {
                                    mpp.put(str[i], mpp.get(str[i]) + 1);
                                    ++i;
                                }
                                mpp.put(str[j], mpp.get(str[j]) - 1);
                            }
                            if (i + n > N) {
                                break;
                            }
                        }
                    }
                    if (j == i + n) {
                        // System.out.println("YES");
                        output.write("YES : ");
                        output.write(new String(str, i, n));
                        output.write('\n');
                        break;
                    }
                }
            }
            if (i + n > N) {
                // System.out.println("NO");
                output.write("NO\n");
            }
        }
    }
}