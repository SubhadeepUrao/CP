public class Main {
    static class Psycho {
        int id;
        int deathtime;

        Psycho(int id, int deathtime) {
            this.id = id;
            this.deathtime = deathtime;
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int[] psychos = new int[n];
        for (int i = 0; i < n; ++i)
            psychos[i] = sc.nextInt();

        Deque<Psycho> stack = new ArrayDeque<>();

        int steps = 0;
        for (int i = 0; i < n; ++i) {
            int curr = psychos[i];
            int max_shield_deathtime = 0;

            while (!stack.isEmpty() && stack.peek().id < curr) {
                max_shield_deathtime = Math.max(max_shield_deathtime, stack.poll().deathtime);
            }

            int deathtime = stack.isEmpty() ? 0 : max_shield_deathtime + 1;
            stack.push(new Psycho(curr, deathtime));

            steps = Math.max(steps, deathtime);
        }

        System.out.println(steps);
    }
}