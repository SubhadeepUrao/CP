public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        int t = 5;
        while (t-- > 0) {
            String hand = sc.next();
            if (str[0] == hand.charAt(0) || str[1] == hand.charAt(1)) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}