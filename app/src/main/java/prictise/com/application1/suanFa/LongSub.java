package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.10.23 21:43
 * @Comment
 */
public class LongSub {

  public static int lengthOfLongestSubstring(String s) {
    int i = 0;
    int flag = 0;
    int length = 0;
    int result = 0;
    while (i < s.length()) {
      int pos = s.indexOf(s.charAt(i), flag);
      if (pos < i) {
        if (length > result) {
          result = length;
        }
        if (result >= s.length() - pos - 1) {
          return result;
        }
        length = i - pos - 1;
        flag = pos + 1;
      }
      length++;
      i++;
    }
    return length;
  }

  /*
   * abcabcbb
   * @param s
   * @return
   */
  public static int lengthOfLongestSubstring2(String s) {
    int length = 0;
    int fromIndex = 0;
    int len = s.length();
    String resultSub = "";
    for (int i = 0; i < len; i++) {
      int pos = s.indexOf(s.charAt(i), fromIndex);
      if (pos < i) {
        if (length < (i - pos)) {
          length = i - pos;
          resultSub = s.substring(pos, i);
        }
        fromIndex = i;
      }
    }
    System.out.println(resultSub);
    return length;
  }

  public static void main(String[] args) {
    int result = 8 >>> 1;
    String s1 = "abcdabcbabcdbbvvvvvccccc";
    String s = "pwwkew";
    int pos = s.indexOf(s.charAt(2), 0);

//    char c = s.charAt(4);
    int a = lengthOfLongestSubstring2(s1);
//    String resultSub = s.substring(0, 5);
    System.out.println(a);
  }

}
