package prictise.com.application1.suanFa;

/**
 * @author zhisiyi
 * @date 2017年3月21日 上午11:56:48
 * @comment 它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort {

  public static void main(String[] args) {

    int[] arr = {19, 0, 67, 34, 10, 888, 19, 23, 56};
    int low = 0;
    int high = arr.length - 1;

    quickSort(arr, low, high);

    System.out.println("最终的顺序：");
    print(arr);
  }

  public static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
      int baseIndex = part(arr, low, high);
      quickSort(arr, low, baseIndex - 1);
      quickSort(arr, baseIndex + 1, high);
    }
  }

  public static int part(int[] arr, int low, int high) {

    int base = arr[low];

    while (low < high) {
      while (low < high && base <= arr[high]) {
        high--;
      }
      swap2(arr, low, high);
      while (low < high && base >= arr[low]) {
        low++;
      }
      swap2(arr, low, high);
    }
    print(arr);
    return low;
  }

  public static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  public static void swap2(int[] arr, int a, int b) {
    if (a == b) {
      return;
    }
    arr[a] = arr[a] ^ arr[b];
    arr[b] = arr[b] ^ arr[a];
    arr[a] = arr[a] ^ arr[b];
  }

  private static void print(int[] arr) {
    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }
}
