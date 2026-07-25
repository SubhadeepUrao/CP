public class Main {
    // modified KMP
    private static void computeLPS(String s, int[] LPS) {
        char[] str = s.toCharArray();
        int n = LPS.length;
        LPS[0] = 0; // base case
        int i = 1;
        int len = 0;

        while (i < n) {
            if (str[len] == str[i]) {
                LPS[i++] = ++len;
            } else {
                if (len > 0)
                    len = LPS[len - 1];
                else
                    ++i;
            }
        }
    }

    private static void solve(FastScanner sc) {
        String str = sc.next();

        int n = str.length();
        int[] LPS = new int[n];

        computeLPS(str, LPS);

        int longest = LPS[n - 1];

        if (longest == 0) {
            System.out.println("Just a legend");
            return;
        }

        boolean foundInMiddle = false;
        for (int i = 1; i < n - 1; ++i) {
            if (LPS[i] == longest) {
                foundInMiddle = true;
                break;
            }
        }

        if (foundInMiddle) {
            System.out.println(str.substring(0, longest));
        } else {
            // fallback to next longest
            int nextLongest = LPS[longest - 1];
            if (nextLongest > 0)
                System.out.println(str.substring(0, nextLongest));
            else
                System.out.println("Just a legend");
        }
    }
}