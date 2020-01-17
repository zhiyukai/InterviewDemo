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
        if (nums1 == null || nums2 == null) {
            return;
        }
        if (nums2.length < 1) {
            return;
        }

        if (m >= n) {
            boolean flag = false;
            for (int i = 1; i < m; i++) {
                if (nums1[i - 1] == nums1[i]) {
                    continue;
                }
                if (nums1[i - 1] > nums1[i]) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            if (m == 1) {
                if (nums1[0] > nums2[0]) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            flag = false;
            if (flag) {
//                int[] num1 = {20, 9, 1, 0, 0, 0};
//                int[] num2 = {10, 5, 3};

                while (n > 0) {
                    if (nums1[m - 1] < nums2[n - 1]) {
                        nums1[m + n - 1] = nums1[m - 1];
                        m--;
                    } else {
                        nums1[m + n - 1] = nums2[n - 1];
                        n--;
                    }
                }


            } else {
                if (m == 1) {
                    if (nums1[0] > nums2[0]) {
                        nums1[1] = nums1[0];
                        nums1[0] = nums2[0];
                    } else {
                        nums1[1] = nums2[0];
                    }
                } else {
                    while (n > 0) {
                        if (nums1[m - 1] > nums2[n - 1]) {
                            nums1[m + n - 1] = nums1[m - 1];
                            m--;
                        } else {
                            nums1[m + n - 1] = nums2[n - 1];
                            n--;
                        }
                    }
                }
            }
        } else {
            boolean flag = false;
            for (int i = 1; i < n; i++) {
                if (nums1[i - 1] == nums1[i]) {
                    continue;
                }
                if (nums1[i - 1] > nums1[i]) {
                    flag = true;
                } else {
                    flag = false;
                }
            }

            flag = false;
            if (flag) {
                while (n >= 1) {
                    if (m == 0) {
                        nums1[n - 1] = nums2[n - 1];
                        n--;
                    } else {
                        if (nums1[m - 1] >= nums2[n - 1]) {
                            nums1[m + n - 1] = nums2[n - 1];
                            n--;
                        } else {
                            nums1[m + n - 1] = nums1[m - 1];
                            m--;
                        }
                    }
                }
            } else {
                while (n >= 1) {
                    if (m == 0) {
                        nums1[n - 1] = nums2[n - 1];
                        n--;
                    } else {
                        if (nums1[m - 1] <= nums2[n - 1]) {
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
    }

    private static void sort3(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) {
            return;
        }
        if (nums2.length < 1) {
            return;
        }

        boolean flag = false;
        for (int i = 1; i < m; i++) {
            if (nums1[i - 1] == nums1[i]) {
                continue;
            }
            if (nums1[i - 1] > nums1[i]) {
                flag = true;
            } else {
                flag = false;
            }
        }
        if (flag) {
            if (m % 2 != 0) {
                for (int i = 0; i < m / 2; i++) {
                    int temp = nums1[i];
                    nums1[i] = nums1[m - 1 - i];
                    nums1[m - 1 - i] = temp;
                }
            } else {
                for (int i = 0; i < m / 2; i++) {
                    int temp = nums1[i];
                    nums1[i] = nums1[m - i];
                    nums1[m - i] = temp;
                }
            }
        }

        if (m >= n) {
            if (m == 1) {
                if (nums1[0] > nums2[0]) {
                    nums1[1] = nums1[0];
                    nums1[0] = nums2[0];
                } else {
                    nums1[1] = nums2[0];
                }
            } else {
                while (n > 0) {
                    if (nums1[m - 1] > nums2[n - 1]) {
                        nums1[m + n - 1] = nums1[m - 1];
                        m--;
                    } else {
                        nums1[m + n - 1] = nums2[n - 1];
                        n--;
                    }
                }
            }
        } else {
            while (n >= 1) {
                if (m == 0) {
                    nums1[n - 1] = nums2[n - 1];
                    n--;
                } else {
                    if (nums1[m - 1] <= nums2[n - 1]) {
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

    private static void sort4(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int len = m + n - 1;//从后面sum1空的的往前存储
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[len--] = nums1[i--];
            } else {
                nums1[len--] = nums2[j--];
            }
        }

        //如果先存储完sum2那么sum1剩下的不用比较 本来就在sum1里面了

        while (j >= 0) {//先存储完sum1剩下sum2的继续存储sum1里面
            nums1[len--] = nums2[j--];
        }
    }


    public static void main(String[] args) {
//        int[] num1 = {1, 9, 20, 0, 0, 0};
//        int[] num2 = {3, 5, 10};
//        int[] num1 = {1, 4, 9, 12, 18, 0, 0, 0, 0};
//        int[] num2 = {2, 3, 7, 10};

//        int[] num1 = {1, 2, 3, 0, 0, 0};
//        int[] num2 = {2, 5, 6};

//        int[] num1 = {1, 2, 0, 0, 0};
//        int[] num2 = {2, 5, 6};

//        int[] num1 = {2, 0};
//        int[] num2 = {1};
//        int[] num1 = {1, 0};
//        int[] num2 = {2};
//        int[] num1 = {20, 9, 0, 0, 0};
//        int[] num2 = {10, 5, 3};

//        int[] num1 = {1, 2, 3, 0, 0, 0};
//        int[] num2 = {2, 5, 6};
//        int[] num1 = {2, 0};
//        int[] num2 = {1};

        int[] num1 = {4, 5, 6, 0, 0, 0};
        int[] num2 = {1, 2, 3};

        int m = 3;
        int n = num2.length;
        sort4(num1, m, num2, n);

        for (int i = 0; i < num1.length; i++) {
            System.out.print(num1[i] + " ");
        }
    }

}
