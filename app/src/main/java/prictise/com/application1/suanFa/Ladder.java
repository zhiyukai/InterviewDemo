package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.10.25 22:44
 * @Comment
 */
public class Ladder {

  /*
   * 假设一个楼梯有 N 阶台阶，人每次最多可以跨 2 阶，
   * 求总共的爬楼梯方案数，要求不用递归实现
   * 自己计算当楼梯数为1、2、3、4、5时，对应的爬法有：1、2、3、5、8、13、21种。
   */
  public static int calculateCount(int ladder) {
    if (ladder == 1 || ladder == 2) {
      return ladder;
    }
    int n1 = 1;
    int n2 = 2;
    for (int i = 3; i <= ladder; i++) {
      int temp = n2;
      n2 = n1 + n2;
      n1 = temp;
    }
    return n2;
  }

  public static void main(String[] args) {
    int ladder = calculateCount(6);
    System.out.println(ladder);
  }
}
