import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            char[][] grid = new char[R][C];
            for (int i = 0; i < R; i++) {
                grid[i] = br.readLine().toCharArray();
            }

            CrystalCastle solver = new CrystalCastle(R, C, M, N, grid);
            out.append(solver.countPaths()).append('\n');
        }

        System.out.print(out);
    }
}
