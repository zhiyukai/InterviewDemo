package prictise.com.application1.suanFa;

/**
 * @author zhisiyi
 * @date 2017年3月21日 上午11:56:48
 * @comment 它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort3 {

  public static void main(String[] args) {

//		int[] arr = { 19, 0, 67, 34, 10, 888, 19, 23, 56 };
    int[] arr = {888, 67, 56};
    int low = 0;
    int high = arr.length - 1;

    // quickSort(arr);
    // quickSort(arr, low, high);
    sort1(arr, low, high);

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

  public static void sort(int a[], int low, int hight) {
    int i, j, index;
    if (low > hight) {
      return;
    }
    i = low;
    j = hight;
    index = a[i]; // 用子表的第一个记录做基准
    while (i < j) { // 从表的两端交替向中间扫描
      while (i < j && a[j] >= index) {
        j--;
      }
      if (i < j) {
        a[i++] = a[j];// 用比基准小的记录替换低位记录
      }
      while (i < j && a[i] < index) {
        i++;
      }
      if (i < j) // 用比基准大的记录替换高位记录{
      {
        a[j--] = a[i];
      }
    }
    a[i] = index;// 将基准数值替换回 a[i]
    sort(a, low, i - 1); // 对低子表进行递归排序
    sort(a, i + 1, hight); // 对高子表进行递归排序

  }

  public static void quickSort(int a[]) {
    sort(a, 0, a.length - 1);
  }

  public static int part(int[] arr, int low, int high) {
    int base = arr[low];
    int baseIndex = low;
    int pre = 0;
    while (low < high) {
      while ((low < high) && (arr[low] <= base)) {
        low++;
      }
      while ((low < high) && (arr[high] >= base)) {
        high--;
      }
      if (low < high) {
        swap(arr, low, high);
      }
    }

    print(arr);
    print(low);
    return low;
  }

  private static void sort1(int[] a, int lo, int hi) {
    if (lo >= hi)// 递归退出判断条件
    {
      return;
    }
    int p = partition(a, lo, hi);// 对于某一元素，其本身不必参与递归了，因为其所在的位置已经满足前面的不大于，后面的不小于
    sort1(a, lo, p - 1);
    sort1(a, p + 1, hi);
  }

  private static int partition(int[] a, int lo, int hi) {
    int left = lo;// 左pointer，供扫描用
    int right = hi + 1;// 右pointer，供扫描用，加1是为了方便扫描的推进，
    int pivot = a[lo];
    int count = 0;
    while (true) {
      System.out.println("-while (true): count" + count);
      System.out.println("-while (true) hi:" + hi);
      System.out.println("-while (true) pivot:" + pivot);
      System.out.println("-while (true) left:" + left);
      System.out.println("-while (true) right:" + right);
      count++;
      while (a[++left] <= pivot) {// 从lo开始，找到大于pivot的元素，在访问数组时使用前++更安全，后++可能会发生越界
        if (left == hi)// 防止越界
        {
          break;
        }
      }
      System.out.println("a[++left] <= pivot left=" + left);
      while (a[--right] >= pivot) {// 从hi开始，找到小于pivot的元素
        if (right == lo)// 防止越界
        {
          break;
        }
      }
      System.out.println("a[--right] >= pivot lo=" + lo);
      System.out.println("a[--right] >= pivot right=" + right);
      if (left >= right)// 左右扫描相交，迭代结束判断条件，相等的时候说明就是和pivot相等的元素
      {
        break;
      }
      System.out.println("---left=" + left);
      System.out.println("---right=" + right);
      swap(a, left, right);// 交换pivot前面大于pivot的元素和pivot后面小于pivot的元素，
      // 从这里可以看出快速排序不稳定，因为两者之间存在和此时的left或者right相等的元素时，原有的顺序就被破坏了

      System.out.print("---");
      print(a);
    }
    System.out.println("right = " + right);
    System.out.println("lo = " + lo);
    swap(a, lo, right);// 将枢轴元素放到合适的位置
    // pivot未交换到合适的位置之前，其他位置的元素都满足扫描条件了(两个while里面为真)，然后再进行一次扫描，扫描条件均为假了，right<=left，right所在位置的元素是不大于pivot的
    print(a);
    return right;// 返回切分元素的位置
  }

  public static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  private static void print(int[] arr) {
    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static void print(int index) {
    System.out.println(index);
  }
}
