public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        HashMap<Integer, Integer> mpp = new HashMap<>();
        // populate the map
        int temp = 1;
        for (int i = 0; i < 30; ++i) {
            mpp.put(temp, i);
            temp <<= 1;
        }

        StringBuilder str = new StringBuilder();
        while (t-- > 0) {
            int[] freq = new int[30];
            int n = sc.nextInt();
            for (int i = 1; i <= n; ++i) {
                int curr = sc.nextInt();
                while (curr > 0) {
                    int rightmost = curr & -curr;
                    ++freq[mpp.get(rightmost)];
                    curr &= curr - 1;
                }
            }

            str.append('1');
            for (int k = 2; k <= n; ++k) {
                boolean possible = true;
                for (int i = 0; i < 30; ++i) {
                    if (freq[i] > 0 && freq[i] % k != 0) {
                        possible = false;
                        break;
                    }
                }
                if (possible)
                    str.append(' ').append(k);
            }
            str.append('\n');
        }
        System.out.println(str);
    }
}