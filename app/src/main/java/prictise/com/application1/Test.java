package prictise.com.application1;

/**
 * @Author zhi
 * @Date 2019/8/5 14:56
 * @Describer
 */
public class Test {

  private static int mCount;

  public static int getValue(int x, int y) {
    if (y <= x && y >= 0) {
      if (y == 0 || x - 1 == y) {   // 递归终止条件
        return 1;
      } else {
        // 递归调用，缩小问题的规模
        return getValue(x - 1, y - 1) + getValue(x - 1, y);
      }
    }
    return -1;
  }

  public static void getStringPermutations3(char[] s, int from, int to) {
    System.out.println("getStringPermutations3 s = " + String.valueOf(s) + "; from = "
        + from + "; to = " + to);
    if (s != null && to >= from && to < s.length && from >= 0) { // 边界条件检查
      if (from == to) { // 递归终止条件
        System.out.println(s); // 打印结果
        mCount++;
      } else {
        for (int i = from; i <= to; i++) {
          swap(s, i, from); // 交换前缀,作为结果中的第一个元素，然后对剩余的元素全排列
          getStringPermutations3(s, from + 1, to); // 递归调用，缩小问题的规模
          swap(s, from, i); // 换回前缀，复原字符数组
        }
      }
    }
    System.out.println("mCount = " + mCount);
  }

  public static void swap(char[] s, int from, int to) {
    System.out.println("swap from = " + from + "; to = " + to);
    char temp = s[from];
    s[from] = s[to];
    s[to] = temp;
  }

  public static void main(String[] args) {
//    String s = "asdf";
//    getStringPermutations3(s.toCharArray(), 0, 3);

//    String s = "asdfghkdsjjllii";
//    s = s.replace('d', 'q');
//    System.out.println(s);

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("t1");
      }
    }, "t1");
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("t2");
      }
    }, "t2");
    Thread t3 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("t3");
      }
    }, "t3");

    t3.start();
    try {
      t3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t2.start();
    try {
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    t1.start();
    try {
      t1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
