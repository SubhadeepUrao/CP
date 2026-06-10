public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        System.out.print(str);
        for (int i = str.length - 1; i >= 0; --i)
            System.out.print(str[i]);
    }
}