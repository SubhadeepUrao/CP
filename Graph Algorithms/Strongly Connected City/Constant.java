public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[] horizontal = sc.next().toCharArray();
        char[] vertical = sc.next().toCharArray();

        int startR = 0, startC = 0;
        int lastR = n - 1, lastC = m - 1;

        boolean clockwise = horizontal[startR] == '>' && horizontal[lastR] == '<' && vertical[startC] == '^'
                && vertical[lastC] == 'v';
        boolean anticlockwise = horizontal[startR] == '<' && horizontal[lastR] == '>' && vertical[startC] == 'v'
                && vertical[lastC] == '^';

        if (clockwise || anticlockwise)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}