public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            char[] A = sc.next().toCharArray();
            char[] B = sc.next().toCharArray();

            if (A.length < B.length) {
                char[] temp = A;
                A = B;
                B = temp;
            }

            int N = A.length;
            int M = B.length;

            int i = 0, j = 0, k = 0;
            while (true) {
                if (A[i] != B[j]) {
                    System.out.println(-1);
                    break;
                }

                ++i;
                ++j;

                if (i == N && j == M) {
                    do {
                        System.out.print(A);
                    } while (k-- > 0);
                    System.out.println();
                    break;
                }

                if (i == N) {
                    ++k;
                    i = 0;
                }
                if (j == M) {
                    j = 0;
                }
            }
        }
    }
}