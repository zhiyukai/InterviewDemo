package prictise.com.application1.suanFa;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhisiyi
 * @Date 2019.09.25 17:27
 * @Comment
 */
public class TwoNum {

  public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int value = nums[i];

      if (map.containsKey(value)) {
        return new int[]{map.get(value), i};
      }

      int complement = target - value;
      map.put(complement, i);
    }

    return new int[]{};
  }

  public static void main(String[] args) {
    int[] nums = {2, 7, 11, 15};
    int[] result = twoSum(nums, 22);
    for (int i = 0; i < 2; i++) {
      System.out.print(result[i] + " ");
    }
  }
}
