import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    static class Edge {
        int from;
        int to;
        int wt;
        int id;

        Edge(int from, int to, int wt, int id) {
            this.from = from;
            this.to = to;
            this.wt = wt; // 1 : working, 0 : damaged
            this.id = id;
        }
    }

    static class Parent {
        int u;
        int id;

        Parent(int u, int id) {
            this.u = u;
            this.id = id;
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt(); // #cities
        int m = sc.nextInt(); // #roads

        // create adjacency list
        List<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i)
            adj[i] = new ArrayList<>();

        Edge[] edges = new Edge[m + 1];

        for (int i = 1; i <= m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int z = sc.nextInt();

            adj[u].add(new Edge(u, v, z, i));
            adj[v].add(new Edge(v, u, z, i));
            edges[i] = new Edge(u, v, z, i);
        }

        Parent[] parent = new Parent[n + 1];
        int[] dist = new int[n + 1];
        int[] workingRoad = new int[n + 1];

        int INF = 1_000_000_000;
        for (int i = 1; i <= n; ++i) {
            dist[i] = INF;
            workingRoad[i] = -1;
        }

        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        dist[1] = 0;
        workingRoad[1] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (Edge neighbour : adj[u]) {
                int v = neighbour.to;
                int wt = neighbour.wt;
                int id = neighbour.id;

                if (dist[u] + 1 < dist[v]) { // relaxation
                    // total path length from 1 to v
                    dist[v] = dist[u] + 1;

                    // #working edges in the path 1 to v
                    workingRoad[v] = workingRoad[u] + wt;
                    
                    parent[v] = new Parent(u, id); // parent of v = u
                    q.add(v); // for future explorations
                } else if (dist[u] + 1 == dist[v]) { // same length path
                    if (workingRoad[u] + wt > workingRoad[v]) {
                        workingRoad[v] = workingRoad[u] + wt;
                        parent[v] = new Parent(u, id);
                    }
                }
            }
        }

        boolean[] pathEdges = new boolean[m + 1];
        int curr = n; // current city
        while (curr != 1) {
            Parent par = parent[curr];
            pathEdges[par.id] = true;
            curr = par.u;
        }

        StringBuilder str = new StringBuilder();
        int modified = 0;
        for (int i = 1; i <= m; ++i) {
            boolean isWorking = edges[i].wt > 0 ? true : false;

            // part of the shortest path 1 to n, damaged edges that are to be repaired
            if (pathEdges[i] && !isWorking) {
                ++modified;
                str.append(edges[i].from).append(" ").append(edges[i].to).append(" 1\n");
            }
            // outside the shortest path, working edges that are to be damaged
            else if (!pathEdges[i] && isWorking) {
                ++modified;
                str.append(edges[i].from).append(" ").append(edges[i].to).append(" 0\n");
            }
        }

        System.out.println(modified);
        System.out.print(str);
    }
}