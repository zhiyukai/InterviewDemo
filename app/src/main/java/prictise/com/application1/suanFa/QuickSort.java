package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.10.29 22:52
 * @Comment 它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort {

  public static int part(int[] arr, int low, int high) {
    if (arr == null) {
      return -1;
    }
    int base = arr[low];

    while (low < high) {
      while (low < high && base < arr[high]) {
        high--;
      }
      swap(arr, low, high);
      while (low < high && base > arr[low]) {
        low++;
      }
      swap(arr, low, high);
    }

    return low;
  }

  public static void swap(int[] arr, int low, int high) {
    int temp = arr[low];
    arr[low] = arr[high];
    arr[high] = temp;
  }

  public static void swap2(int[] arr, int a, int b) {
    if (a == b) {
      return;
    }
    arr[a] = arr[a] ^ arr[b];
    arr[b] = arr[b] ^ arr[a];
    arr[a] = arr[a] ^ arr[b];
  }


  public static void quick(int[] arr, int low, int high) {
    if (arr == null) {
      return;
    }
    if (low == -1 || high == -1) {
      return;
    }
    if (low < high) {
      int base = part(arr, low, high);
      System.out.println("base = " + base);
      quick(arr, low, base - 1);
      quick(arr, base + 1, high);
    }
  }

  public static void main(String[] args) {
    int[] arr = {5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8};

    quick(arr, 0, arr.length - 1);

    int size = arr.length;
    for (int i = 0; i < size; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}
