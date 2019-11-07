package prictise.com.application1.suanFa;

/**
 * @author zhisiyi
 * @date 2017年3月21日 上午11:56:48
 * @comment 它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 *          然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort2 {

	public static void main(String[] args) {

		int[] arr = { 19, 0, 67, 34, 10, 888, 19, 23, 56 };
		// int[] arr = { 888, 67, 56 };
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
		if (high == 0) {
			return 0;
		}
		System.out.println("high = " + high);
		int base = arr[low];
		int baseIndex = low;
		while (low < high) {
			if ((high == low + 1) && arr[low] > arr[high]) {
				swap(arr, low, high);
				return 1;
			}

			while (low < high && base >= arr[low]) {
				low++;
			}
			System.out.println("low = " + low);
			System.out.println("high = " + high);
			// if (low < high)
			// swap(arr, low, high);
			while (low < high && base <= arr[high]) {
				high--;
			}
			if (low < high)
				swap(arr, low, high);
		}
		// 当越界的不满足的时候，说明是找到下一个目标，并不是要找的目标，
		// 此时已经遍历了剩余的所有数据，此时也就是说剩余的所有数据，都不符合要求；
		// 也就是说已经没有这样的数据啦，所以说前一个数据就是此次遍历的最后一个数据；
		// 但是下标却停在了下一个目标处，所以，要把下表回退到原来的地方
		swap(arr, baseIndex, high);
		high--;
		System.out.print("---");
		print(arr);

		return high;
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
}
