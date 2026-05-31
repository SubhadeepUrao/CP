public class Main {
    private static boolean allCaps(int i, char[] str) {
        int N = str.length;
        while (i < N && str[i] < 'a') ++i;
        return i == N;
    }

    private static void changeCase(char[] str) {
        int N = str.length;
        for (int i = 0; i < N; ++i)
            if (str[i] < 'a')
                str[i] += 32;
            else
                str[i] -= 32;
    }

    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();

        if (str[0] > 'Z' && allCaps(1, str))
            changeCase(str);
        else if (allCaps(0, str))
            changeCase(str);

        System.out.println(str);
    }
}