public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int N = sc.nextInt();
            boolean odd_presence = false;
            boolean even_presence = false;
            for (int i = 0; i < N; ++i) {
                if ((sc.nextInt() & 1) == 1)
                    odd_presence = true;
                else
                    even_presence = true;
            }

            // if ((N & 1) == 1 && odd_presence) System.out.println("YES");
            // else if ((N & 1) == 0 && odd_presence && even_presence) System.out.println("YES");

            if (((N & 1) == 1 || even_presence) && odd_presence)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}