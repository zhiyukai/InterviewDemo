package prictise.com.application1.suanFa;

import java.util.Arrays;

/**
 * @Author zhi
 * @Date 2019/8/5 18:40
 * @Describer
 */
public class SuanFaUtil {

  /**
   * 全排列
   */
  public static void fullPermutation() {

  }

  public static void getStringPermutations4(char[] s, int from, int to) {

    Arrays.sort(s, from, to + 1);  // 对字符数组的所有元素进行升序排列，即得到最小排列
    System.out.println(s);

    char[] descendArr = getMaxPermutation(s, from, to); // 得到最大排列,即最小排列的逆序列

    while (!Arrays.equals(s, descendArr)) {  // 循环终止条件：迭代至最大排列
      if (s != null && to >= from && to < s.length && from >= 0) { // 边界条件检查
        int top = getExtremum(s, from, to); // 找到序列的极值
        int minMax = getMinMax(s, top, to);  // 从top处(包括top)查找比s[top-1]大的最小值所在的位置
        swap(s, top - 1, minMax);  // 交换minMax处和top-1处的字符
        s = reverse(s, top, to);   // 翻转top之后的字符
        System.out.println(s);
      }
    }
  }

  /**
   * @description 对字符数组中的制定字符进行交换
   * @author rico
   */
  public static void swap(char[] s, int from, int to) {
    char temp = s[from];
    s[from] = s[to];
    s[to] = temp;
  }

  /**
   * @param s 序列
   * @param from 起始下标
   * @param to 终止下标
   * @description 获取序列的极值
   * @author rico
   */
  public static int getExtremum(char[] s, int from, int to) {
    int index = 0;
    for (int i = to; i > from; i--) {
      if (s[i] > s[i - 1]) {
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * @param top 极大值所在位置
   * @description 从top处查找比s[top-1]大的最小值所在的位置
   * @author rico
   * @created 2017年5月10日 上午9:21:13
   */
  public static int getMinMax(char[] s, int top, int to) {
    int index = top;
    char base = s[top - 1];
    char temp = s[top];
    for (int i = top + 1; i <= to; i++) {
      if (s[i] > base && s[i] < temp) {
        temp = s[i];
        index = i;
      }
      continue;
    }
    return index;
  }

  /**
   * @description 翻转top(包括top)后的序列
   * @author rico
   */
  public static char[] reverse(char[] s, int top, int to) {
    char temp;
    while (top < to) {
      temp = s[top];
      s[top] = s[to];
      s[to] = temp;
      top++;
      to--;
    }
    return s;
  }

  /**
   * @param s 最小排列
   * @param from 起始下标
   * @param to 终止下标
   * @description 根据最小排列得到最大排列
   * @author rico
   */
  public static char[] getMaxPermutation(char[] s, int from, int to) {
    //将最小排列复制到一个新的数组中
    char[] dsc = Arrays.copyOfRange(s, 0, s.length);
    int first = from;
    int end = to;
    while (end > first) {  // 循环终止条件
      char temp = dsc[first];
      dsc[first] = dsc[end];
      dsc[end] = temp;
      first++;
      end--;
    }
    return dsc;
  }

  private static void swapArray(int[] arr, int a, int b) {
    if (arr[a] == arr[b]) {
      return;
    }
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  public static void Perm(int list[], int k, int m) {
    //list 数组存放排列的数，K表示层 代表第几个数，m表示数组的长度
    if (k == m) {
      //K==m 表示到达最后一个数，不能再交换，最终的排列的数需要输出；
      for (int i = 0; i <= m; i++) {
        System.out.print(list[i]);
      }
      System.out.println();
      return;
    } else {
      for (int i = k; i <= m; i++) {
        if (i != k) {
          swapArray(list, i, k);
        }
        Perm(list, k + 1, m);
        if (i != k) {
          swapArray(list, i, k);
        }
      }
    }

  }

  public static void main(String[] args) {
//    String r = "122";
//    getStringPermutations4(r.toCharArray(), 0, 2);
    int a[] = {1, 2, 3};
    Perm(a, 0, 2);
  }
}
