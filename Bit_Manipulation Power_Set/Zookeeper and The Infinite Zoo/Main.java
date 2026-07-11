public class Main {
    private static void solve(FastScanner sc) {
        int q = sc.nextInt();
        StringBuilder str = new StringBuilder();
        while (q-- > 0) {
            long u = sc.nextLong();
            long v = sc.nextLong();

            if (u > v)
                str.append("NO").append('\n');
            else {
                int countU = 0, countV = 0;
                boolean possible = true;
                while (v > 0) {
                    if ((u & 1) == 1)
                        ++countU;
                    if ((v & 1) == 1)
                        ++countV;
                    if (countU < countV) {
                        possible = false;
                        break;
                    }
                    u >>>= 1;
                    v >>>= 1;
                }
                if (possible)
                    str.append("YES").append('\n');
                else
                    str.append("NO").append('\n');
            }
        }
        System.out.println(str);
    }
}