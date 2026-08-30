public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        char[] str = sc.next().toCharArray();

        int max0 = 0; // white
        int max1 = 0; // black
        int[] color = new int[n];

        for (int i = 0; i < n; ++i) {
            int ch = str[i];
            if (ch >= max0) {
                max0 = ch;
                color[i] = 0;
            } else if (ch >= max1) {
                max1 = ch;
                color[i] = 1;
            } else {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        for (int i = 0; i < n; ++i)
            System.out.print(color[i]);
    }
}