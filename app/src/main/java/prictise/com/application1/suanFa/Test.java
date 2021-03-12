package prictise.com.application1.suanFa;

public class Test {
  public static void main(String[] args) {
    int res = mySqrt2(10);
    System.out.println(res);
  }

  public static int mySqrt(int x) {
    int l = 0, r = x, ans = -1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if ((long) mid * mid <= x) {
        ans = mid;
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return ans;
  }

  // 牛顿迭代法是一种可以用来快速求解函数零点的方法。
  // 主要是公式的推导
  public static int mySqrt2(int x) {
    if (x == 0) {
      return 0;
    }

    double C = x, x0 = x;
    while (true) {
      double xi = 0.5 * (x0 + C / x0);
      if (Math.abs(x0 - xi) < 1e-7) {
        break;
      }
      x0 = xi;
      System.out.println("x0 = " + x0);
    }
    return (int) x0;
  }

}
