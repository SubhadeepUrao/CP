public class Main {
    private static void solve(FastScanner sc) {
        char[] num = sc.next().toCharArray();
        int k = sc.nextInt();
        int N = num.length;
        int last = N - 1;
        while (k-- > 0) {
            if (num[last] > '0')
                --num[last];
            else
                --last;
        }
        if (last < 0)
            System.out.println(0);
        else
            System.out.println(new String(num, 0, last + 1));
    }
}