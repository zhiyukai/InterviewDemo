package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.10.30 13:50
 * @Comment
 */
public class ElementsSum {

  public static int result(int[][] arr, int m, int n) {
    for (int i = 1; i < m; i++) {
      arr[0][i] += arr[0][i - 1];
    }

    for (int j = 1; j < n; j++) {
      arr[j][0] += arr[j - 1][0];
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        arr[i][j] += Math.max(arr[i][j - 1], arr[i - 1][j]);
      }
    }
    return arr[n - 1][m - 1];
  }

  public static void main(String[] args) {
    int[][] arr = {{1, 2, 3}, {1, 0, 2}};
    int r = result(arr, 3, 2);
    System.out.println(r);
  }
}
