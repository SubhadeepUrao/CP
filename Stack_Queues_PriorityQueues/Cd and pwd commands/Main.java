public class Main {
    private static void printAbsPath(StringBuilder str) {
        str.append('/');
        if (top < 0) {
            str.append('\n');
            return;
        }
        for (int i = 0; i <= top; ++i)
            str.append(stack.get(i)).append('/');
        str.append('\n');
    }

    private static List<String> stack = new ArrayList<>();
    private static int top = -1;

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        StringBuilder str = new StringBuilder();

        while (n-- > 0) {
            String cmd = sc.next();

            if (cmd.equals("pwd")) {
                printAbsPath(str);
                continue;
            }

            String path = sc.next();

            if (path.startsWith("/")) {
                stack.clear();
                top = -1;
            }

            String[] tokens = path.split("/");
            for (String dir : tokens) {
                if (dir.isEmpty())
                    continue;
                if (dir.equals("..")) {
                    stack.remove(top--);
                    continue;
                }
                stack.add(dir);
                ++top;
            }
        }
        System.out.println(str);
    }
}