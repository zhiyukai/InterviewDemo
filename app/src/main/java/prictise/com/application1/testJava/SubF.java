package prictise.com.application1.testJava;

import android.widget.TextView;

/**
 * @Author zhisiyi
 * @Date 2019.10.30 18:03
 * @Comment
 */
public class SubF extends F {

  String a;

  @Override
  protected void testOverride() {
    super.testOverride();
    System.out.println(a + " " + d);
  }

  public static void main(String[] args) {
    new SubF().testOverride();
  }
}
