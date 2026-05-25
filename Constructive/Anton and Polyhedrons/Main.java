public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        int face = 0;
        while (t-- > 0) {
            char ch = sc.next().charAt(0);
            if (ch == 'T') face += 4;
            else if (ch == 'C') face += 6;
            else if (ch == 'O') face += 8;
            else if(ch == 'D') face += 12;
            else face += 20;
        }
        System.out.println(face);
    }
}