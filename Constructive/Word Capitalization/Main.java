public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        if(str[0] > 'Z') str[0] -=32;
        System.out.println(str);
    }
}