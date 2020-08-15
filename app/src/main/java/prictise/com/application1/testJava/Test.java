package prictise.com.application1.testJava;

public class Test {

  static void yiHuo() {
    int a = 6;
    int b = 4;
    System.out.println("a ^ b = " + (a ^ b));
  }

  static void another(F f, int i) {
    f.d = 10;
    another1(f, 2);
    System.out.println("f.d = " + f.d);

    String s1 = new String("ddd");
    another2(s1);
    System.out.println("s1 = " + s1);
  }

  static void another1(F f, int i) {
//    F fa = new F();
//    f = fa;
    f.d = 99;
    i = 5;
  }

  static void another2(String s) {
    s = "as";
  }

  public static void main(String[] args) {
//    F f = new F();
//    f.d = 90;
//    int i = 9;
//    another(f, i);
//
//    System.out.println("f.d = " + f.d + "; i = " + i);

    yiHuo();
  }
}

class Outter {

  private int a = 1;

  class Inner {

    private int a = 2;

    public void print(int c) {
      c = 5;
      int a = 3;
      System.out.println("局部变量：" + a);
      System.out.println("内部类变量：" + this.a);
      System.out.println("外部类变量：" + Outter.this.a);
      int b = Outter.this.a;
      b = 4;
      Outter.this.a = 5;
    }
  }

}