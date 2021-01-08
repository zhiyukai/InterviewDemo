package prictise.com.application1;

import android.os.SystemClock;

import com.bumptech.glide.Glide;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * @Author zhi
 * @Date 2019/8/5 14:56
 * @Describer
 */
public class Test {

  private static int mCount;


//  public static String method1(List<String> list) {
//    return "1";
//  }

  public static Integer method1(List<Integer> list) {
    return 2;
  }

  public List<List<Integer>> permute(int[] nums) {
    int len = nums.length;
    List<List<Integer>> res = new ArrayList<>();
    if (len == 0) {
      return res;
    }
    Deque<Integer> path = new ArrayDeque<Integer>();
    boolean[] used = new boolean[len];
    dfs(nums, len, 0, path, used, res);

    return res;
  }

  private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
    if (depth == len) {
      res.add(new ArrayList<Integer>(path));
      return;
    }

    for (int i = 0; i < len; i++) {
      if (used[i]) {
        continue;
      }

      path.addLast(nums[i]);
      used[i] = true;
      dfs(nums, len, depth + 1, path, used, res);
      used[i] = false;
      path.removeLast();
    }
  }


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
    long time = SystemClock.currentThreadTimeMillis();
    long time2 = System.currentTimeMillis();
    System.out.println("time = " + time);
    System.out.println("time2 = " + time2);
//    String s = "asdf";
//    getStringPermutations3(s.toCharArray(), 0, 3);

//    String s = "asdfghkdsjjllii";
//    s = s.replace('d', 'q');
//    System.out.println(s);

    /*Thread t1 = new Thread(new Runnable() {
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
    }*/
//    final Car car = Car.create(Car::new);
//    final List<Car> cars = Arrays.asList( car );
//    Car.collide(car);

    System.out.println("Collided ");

  }
//
//  @FunctionalInterface
//  public interface Supplier<T> {
//
//    T get();
//  }
//
//  static class Car {
//
//    //Supplier是jdk1.8的接口，这里和lamda一起使用了
//    public static Car create(final Supplier<Car> supplier) {
//      return supplier.get();
//    }
//
//    public static void collide(final Car car) {
//      System.out.println("Collided " + car.toString());
//    }
//
//    public void follow(final Car another) {
//      System.out.println("Following the " + another.toString());
//    }
//
//    public void repair() {
//      System.out.println("Repaired " + this.toString());
//    }
//  }
}
