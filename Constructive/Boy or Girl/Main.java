public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        boolean[] alpha = new boolean[26];
        int c = 0;
        for (int ch : str) {
            ch -= 'a';
            if (!alpha[ch]) {
                alpha[ch] = true;
                ++c;
            }
        }

        if ((c & 1) == 0)
            System.out.println("CHAT WITH HER!");
        else
            System.out.println("IGNORE HIM!");
    }
}