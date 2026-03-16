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
        // DP buffers:
        // cur[c][j][k]   -> estados da linha r
        // next1[c][j][k] -> estados da linha r+1
        // next2[c][j][k] -> estados da linha r+2
        int[][][] cur = new int[C][N + 1][M + 1];
        int[][][] next1 = new int[C][N + 1][M + 1];
        int[][][] next2 = new int[C][N + 1][M + 1];

        // Caso base: DP(0,0,0,0) = 1
        if (grid[0][0] != '#') {
            cur[0][0][0] = 1;
        }

        for (int r = 0; r < R; r++) {

            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '#') continue;

                for (int j = 0; j <= N; j++) {
                    for (int k = 0; k <= M; k++) {
                        int ways = cur[c][j][k];
                        if (ways == 0) continue;

                        // -------------------------------------------------
                        // R: (r,c) -> (r,c+1), j -> j, k -> 0
                        // Condição: (r,c+1) não ser '#'
                        // -------------------------------------------------
                        if (c + 1 < C && grid[r][c + 1] != '#') {
                            cur[c + 1][j][0] = add(cur[c + 1][j][0], ways);
                        }

                        // -------------------------------------------------
                        // D: (r,c) -> (r+1,c), j -> j, k -> 0
                        // Condição: (r+1,c) não ser '#'
                        // -------------------------------------------------
                        if (r + 1 < R && grid[r + 1][c] != '#') {
                            next1[c][j][0] = add(next1[c][j][0], ways);
                        }

                        // -------------------------------------------------
                        // Saltos:
                        // restrições: j < N e k < M
                        // quando há salto: j -> j+1, k -> k+1
                        // -------------------------------------------------
                        if (j < N && k < M) {
                            char cell = grid[r][c];

                            // J proíbe todos os saltos
                            if (cell != 'J') {

                                // -----------------------------------------
                                // LD: (r,c) -> (r+1,c-1)
                                // Condições: cell != J/X e destino != '#'
                                // -----------------------------------------
                                if (cell != 'X' && r + 1 < R && c - 1 >= 0 && grid[r + 1][c - 1] != '#') {
                                    next1[c - 1][j + 1][k + 1] =
                                            add(next1[c - 1][j + 1][k + 1], ways);
                                }

                                // -----------------------------------------
                                // RD: (r,c) -> (r+1,c+1)
                                // Condições: cell != J/X e destino != '#'
                                // -----------------------------------------
                                if (cell != 'X' && r + 1 < R && c + 1 < C && grid[r + 1][c + 1] != '#') {
                                    next1[c + 1][j + 1][k + 1] =
                                            add(next1[c + 1][j + 1][k + 1], ways);
                                }

                                // -----------------------------------------
                                // DD: (r,c) -> (r+2,c)
                                // Condições: cell != J e destino != '#'
                                // -----------------------------------------
                                if (r + 2 < R && grid[r + 2][c] != '#') {
                                    next2[c][j + 1][k + 1] =
                                            add(next2[c][j + 1][k + 1], ways);
                                }
                            }
                        }
                    }
                }
            }

            if (r == R - 1) break;

            // Avança uma linha:
            // a antiga next1 passa a ser a linha atual
            // a antiga next2 passa a ser a próxima
            // cria-se um novo buffer vazio para r+2
            cur = next1;
            next1 = next2;
            next2 = new int[C][N + 1][M + 1];
        }

        int ans = 0;
        for (int j = 0; j <= N; j++) {
            for (int k = 0; k <= M; k++) {
                ans = add(ans, cur[C - 1][j][k]);
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