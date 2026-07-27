class Main {
    public static int expand(char[] str, int l, int r) {
        int count = 0;
        int n = str.length;
        while (0 <= l && r < n && str[l] == str[r]) {
            ++count;
            --l;
            ++r;
        }
        return count;
    }

    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        int n = str.length;

        int count = 0;
        for (int i = 0; i < n; ++i) {
            count += expand(str, i, i);
            count += expand(str, i, i + 1);
        }

        System.out.println(count);
    }
}