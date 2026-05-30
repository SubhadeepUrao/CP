public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        if (m % n != 0) {
            System.out.println(-1);
            return;
        }

        int target = m / n;
        int len = 0;
        while (target > 1) {
            if(target % 2 == 0) target /= 2;
            else if(target % 3 == 0) target /= 3;
            else {
                System.out.println(-1);
                return;
            } 
            ++len;
        }
        System.out.println(len);
    }
}