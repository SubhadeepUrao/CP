public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        char[] str = sc.next().toCharArray();

        int p = 1;
        for (int i = 1; i < n; ++i) {
            if (str[i % p] > str[i])
                p = i + 1;
            else if (str[i % p] < str[i])
                break;
        }
        
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < k; ++i) {
            res.append(str[i % p]);
        }
        System.out.println(res);
    }
}