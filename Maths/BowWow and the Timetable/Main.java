public class Main {
    private static void solve(FastScanner sc) {
        char[] s = sc.next().toCharArray();
        int N = s.length;
        int count = 0;
        boolean hasOne = false; // excluding MSB
        for (int i, j = i = N - 1; i >= 0; --i, j -= 2) {
            if (j >= 0)
                count++;
            if (i > 0 && s[i] == '1')
                hasOne = true;
        }
        if (((N - 1) & 1) == 1 || hasOne)
            System.out.println(count);
        else
            System.out.println(count - 1);
    }
}