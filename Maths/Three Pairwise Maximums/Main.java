public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int[] num = new int[3];
            for (int i = 0; i < 3; ++i)
                num[i] = sc.nextInt();

            Arrays.sort(num);

            if (num[1] != num[2])
                System.out.println("NO");
            else {
                // a = x;
                // b = x;
                // c = y or z
                System.out.println("YES");
                System.out.println(num[0] + " " + num[0] + " " + num[2]);
            }
        }
    }
}