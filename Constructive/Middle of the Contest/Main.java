public class Main {
    private static void solve(FastScanner sc) {
        char[] start = sc.next().toCharArray();
        char[] end = sc.next().toCharArray();

        int start_hr = (start[0] - '0') * 10 + (start[1] - '0');
        int start_min = (start[3] - '0') * 10 + (start[4] - '0');
        int end_hr = (end[0] - '0') * 10 + (end[1] - '0');
        int end_min = (end[3] - '0') * 10 + (end[4] - '0');

        int mean_mins = ((end_hr - start_hr) * 60 + start_min + end_min) >> 1;

        int middle_hr = mean_mins / 60 + start_hr;
        int middle_min = mean_mins % 60;

        System.out.print((middle_hr / 10) + "" + (middle_hr % 10) + ":" + (middle_min / 10) + "" + (middle_min % 10));
    }
}