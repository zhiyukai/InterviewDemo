package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.12.19 10:07
 * @Comment
 */
public class TwoSortArray {

    private static void sort(int[] num1, int m, int[] num2, int n) {
        int size = num1.length;
        int tempIndex = size - 1;
        if (m > n) {
            int j = n - 1;
            for (int i = m - 1; i >= 0; i--) {
                for (; j >= 0; j--) {
                    tempIndex--;
                    if (num1[i] > num2[j]) {
                        num1[tempIndex] = num1[i];
                        break;
                    } else {
                        num1[tempIndex] = num2[j];
                    }
                }
            }
        }
    }

    private static void sort2(int[] nums1, int m, int[] nums2, int n) {

        if (m >= n) {
            while (n > 0) {
                if (nums1[m - 1] > nums2[n - 1]) {
                    nums1[m + n - 1] = nums1[m - 1];
                    m--;
                } else {
                    nums1[m + n - 1] = nums2[n - 1];
                    n--;
                }
            }
        } else {
            while (m > 0) {
                if (n > 1) {
                    if (nums1[m - 1] < nums2[n - 1]) {
                        nums1[m + n - 1] = nums2[n - 1];
                        n--;
                    } else {
                        nums1[m + n - 1] = nums1[m - 1];
                        m--;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
//        int[] num1 = {1, 4, 9, 12, 18, 0, 0, 0, 0};
//        int[] num2 = {2, 3, 7, 10};

//        int[] num1 = {1, 2, 3, 0, 0, 0};
//        int[] num2 = {2, 5, 6};

        int[] num1 = {1, 2, 0, 0, 0};
        int[] num2 = {2, 5, 6};


        int m = 2;
        int n = num2.length;
        sort2(num1, m, num2, n);

        for (int i = 0; i < num1.length; i++) {
            System.out.print(num1[i] + " ");
        }
    }

}
