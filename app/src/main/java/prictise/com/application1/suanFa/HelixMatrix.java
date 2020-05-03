package prictise.com.application1.suanFa;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhisiyi
 * @Date 2019.12.26 11:36
 * @Comment 螺旋矩阵
 */
public class HelixMatrix {

    private static List<Integer> traverse(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) {
            return ans;
        }
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }


    public static List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) {
            return ans;
        }
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) {
                ans.add(matrix[r1][c]);
            }
            for (int r = r1 + 1; r <= r2; r++) {
                ans.add(matrix[r][c2]);
            }
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) {
                    ans.add(matrix[r2][c]);
                }
                for (int r = r2; r > r1; r--) {
                    ans.add(matrix[r][c1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int c1 = 0;
        int c2 = n - 1;

        int r1 = 0;
        int r2 = n - 1;

        while (r1 < r2 && c1 < c2) {
            
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] traArray = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };

        List<Integer> res = spiralOrder(traArray);
        int size = res.size();
        for (int i = 0; i < size; i++) {
            System.out.print(res.get(i) + " ");
        }
    }
}
