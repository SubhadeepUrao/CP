public class Main {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            String s = sc.next();
            sb.append(solve(s)).append("\n");
        }

        System.out.print(sb);
    }

    private static String solve(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Step 1: Strip matching outer prefix and suffix
        int l = 0, r = n - 1;
        while (l < r && arr[l] == arr[r]) {
            l++;
            r--;
        }

        // If the entire string is already a palindrome
        if (l >= r) {
            return s;
        }

        // Remaining middle portion
        String rem = s.substring(l, r + 1);

        // Step 2: Find longest palindromic prefix of rem
        int prefLen = getLongestPalindromicPrefix(rem);

        // Step 3: Find longest palindromic suffix of rem (prefix of reversed rem)
        String revRem = new StringBuilder(rem).reverse().toString();
        int suffLen = getLongestPalindromicPrefix(revRem);

        // Step 4: Construct the final string
        String outerPref = s.substring(0, l);
        String outerSuff = s.substring(r + 1);

        if (prefLen >= suffLen) {
            return outerPref + rem.substring(0, prefLen) + outerSuff;
        } else {
            return outerPref + rem.substring(rem.length() - suffLen) + outerSuff;
        }
    }

    // Computes the LPS array on (str + '#' + reverse(str)) to find longest
    // palindromic prefix
    private static int getLongestPalindromicPrefix(String str) {
        String revStr = new StringBuilder(str).reverse().toString();
        String concat = str + "#" + revStr;

        char[] c = concat.toCharArray();
        int len = c.length;
        int[] lps = new int[len];

        int matchLen = 0;
        int i = 1;

        while (i < len) {
            if (c[i] == c[matchLen]) {
                matchLen++;
                lps[i] = matchLen;
                i++;
            } else {
                if (matchLen != 0) {
                    // Fallback to next best matching prefix length
                    matchLen = lps[matchLen - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps[len - 1];
    }
}