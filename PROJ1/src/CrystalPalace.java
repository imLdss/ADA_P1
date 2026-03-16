class CrystalCastle {
    private static final int MOD = 1_000_000_007;

    private final int R, C, M, N;
    private final char[][] grid;

    public CrystalCastle(int R, int C, int M, int N, char[][] grid) {
        this.R = R;
        this.C = C;
        this.M = M;
        this.N = N;
        this.grid = grid;
    }

    public int countPaths() {
        // Linha atual, próxima linha e linha r+2
        int[][][] cur = new int[C][N + 1][M + 1];
        int[][][] next1 = new int[C][N + 1][M + 1];
        int[][][] next2 = new int[C][N + 1][M + 1];

        cur[0][0][0] = 1;

        for (int r = 0; r < R; r++) {

            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '#') continue;

                for (int used = 0; used <= N; used++) {
                    for (int cons = 0; cons <= M; cons++) {
                        int ways = cur[c][used][cons];
                        if (ways == 0) continue;

                        // R aka passo normal para a direita
                        if (c + 1 < C && grid[r][c + 1] != '#') {
                            cur[c + 1][used][0] = add(cur[c + 1][used][0], ways);
                        }

                        // D aka passo normal para baixo (1 linha)
                        if (r + 1 < R && grid[r + 1][c] != '#') {
                            next1[c][used][0] = add(next1[c][used][0], ways);
                        }

                        // Saltos
                        if (used < N && cons < M) {
                            char cell = grid[r][c];

                            // J proibe todos os saltos
                            if (cell != 'J') {

                                // LD e RD vão para a linha seguinte
                                if (cell != 'X' && r + 1 < R) {
                                    // LD
                                    if (c - 1 >= 0 && grid[r + 1][c - 1] != '#') {
                                        next1[c - 1][used + 1][cons + 1] =
                                                add(next1[c - 1][used + 1][cons + 1], ways);
                                    }

                                    // RD
                                    if (c + 1 < C && grid[r + 1][c + 1] != '#') {
                                        next1[c + 1][used + 1][cons + 1] =
                                                add(next1[c + 1][used + 1][cons + 1], ways);
                                    }
                                }

                                // DD vai para a linha r+2
                                if (r + 2 < R && grid[r + 2][c] != '#') {
                                    next2[c][used + 1][cons + 1] =
                                            add(next2[c][used + 1][cons + 1], ways);
                                }
                            }
                        }
                    }
                }
            }

            if (r == R - 1) break;

            // avança uma linha:
            // a antiga next1 fica cur
            // a antiga next2 fica next1
            // criamos uma nova next2 vazia
            cur = next1;
            next1 = next2;
            next2 = new int[C][N + 1][M + 1];
        }

        int ans = 0;
        for (int used = 0; used <= N; used++) {
            for (int cons = 0; cons <= M; cons++) {
                ans = add(ans, cur[C - 1][used][cons]);
            }
        }
        return ans;
    }

    private int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }
}