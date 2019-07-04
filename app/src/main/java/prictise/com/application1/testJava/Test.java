package prictise.com.application1.testJava;

public class Test {

  public static void main(String[] args) {
    Outter outter = new Outter();
    outter.new Inner().print(3);
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