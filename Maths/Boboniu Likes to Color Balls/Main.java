public class Main {
    private static int countOdd(int r, int g, int b, int w) {
        int count = 0;
        if ((r & 1) == 1) ++count;
        if ((g & 1) == 1) ++count;
        if ((b & 1) == 1) ++count;
        if ((w & 1) == 1) ++count;

        return count;
    }

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();

            int odds = countOdd(r, g, b, w);
            // if (odds < 2 || (r > 0 && g > 0 && b > 0 && odds == 3) || odds == 4)
            if (odds < 2 || (r > 0 && g > 0 && b > 0 && odds >= 3))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}