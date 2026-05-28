public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toLowerCase().toCharArray();
        for (char ch : str) {
            if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u' && ch != 'y')
                System.out.print("." + ch);
        }
    }
}