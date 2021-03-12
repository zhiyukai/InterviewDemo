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

  public int maxProfit(int[] prices) {
    int ans = 0;
    int n = prices.length;
    for (int i = 1; i < n; ++i) {
      ans += Math.max(0, prices[i] - prices[i - 1]);
    }
    return ans;
  }

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

  public static void main(String[] args) throws Exception {
    int k = 1;
    int n = 200; // 时间间隔 ms
    int i = 1; // 0 文本+图片；1：文本；2：图片
    long l = System.currentTimeMillis();
//    while (System.currentTimeMillis() - l < 600000) {
    while (System.currentTimeMillis() - l < 1000) {

      if (i == 0 || i == 1) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", " {\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":100,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wmn3cJEAAAj4TNa7WfUn9cLopEDCZrFg\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 2) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", "{\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":101,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wmn3cJEAAAj4TNa7WfUn9cLopEDCZrFg\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 1) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", "{\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":100,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wm495yDwAAsNOPoXtSswP6LzORw1vZmg\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 2) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", "{\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":101,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wm495yDwAAsNOPoXtSswP6LzORw1vZmg\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 1) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", " {\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":100,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wm495yDwAAhvSee9tqzmjfcyST9qTZnQ\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 2) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", "{\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":101,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wm495yDwAAhvSee9tqzmjfcyST9qTZnQ\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 1) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", "{\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":100,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wm495yDwAAiKxYffBVuArdbMwz9az9pw\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 2) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", "{\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":101,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wm495yDwAAiKxYffBVuArdbMwz9az9pw\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 1) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", "{\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":100,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wm495yDwAA5aYf_eOYdFj77Ex5MXmmBA\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
      if (i == 0 || i == 2) {
        OkHttpClientUtils.getInstance().doPostWithJsonResult("http://172.16.117.151:9111/enterprise/api/raw/push/batchPush", "{\n" +
                "  \"paramList\": [{\n" +
                " \"operationType\":101,\n" +
                "      \"weworkAccount\": \"ZhiYuKai\",\n" +
                "      \"firstPriority\": true,\n" +
                "\"pictureUrl\":\"http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/%E9%9C%80%E6%B1%823.gif\",\n" +
                "      \"messageList\": [\n" +
                "        {\n" +
                "          \"messageContent\": \"推送一条测试文本\",\n" +
                "          \"messageId\": 0\n" +
                "        }],\n" +
                "      \"notEnterCommonQueue\": true,\n" +
                "      \"recordId\": \"testfatupian" + k++ + "\",\n" +
                "      \"externalUserId\": \"wm495yDwAA5aYf_eOYdFj77Ex5MXmmBA\"\n" +
                "    }]}");
        Thread.sleep(n);
      }
    }
  }

}
