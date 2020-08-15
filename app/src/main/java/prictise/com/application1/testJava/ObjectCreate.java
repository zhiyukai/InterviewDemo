package prictise.com.application1.testJava;

/**
 * @Author zhisiyi
 * @Date 2020.07.24 09:32
 * @Comment
 */
public class ObjectCreate {

  private int i = 1;
  private int j = i + 1;

  public ObjectCreate() {
    this(1);
  }

  public ObjectCreate(int var) {
    this(var, 2);
    System.out.println(i);
    System.out.println(j);
    this.i = var;
    System.out.println(i);
    System.out.println(j);
  }

  public ObjectCreate(int var1, int var2) {

  }


  {               // 实例代码块
    j += 3;

  }

  public static void main(String[] args) {
//    new ObjectCreate(8);
    Bar bar = new Bar();
    System.out.println(bar.getValue());             // -----------(3)
  }

}

//父类
class Foo {

  int i = 1;

  Foo() {
    System.out.println(i);             // -----------(1)
    int x = getValue();
    System.out.println(x);             // -----------(2)
  }

  {
    i = 2;
  }

  protected int getValue() {
    return i;
  }
}

//子类
class Bar extends Foo {

  int j = 1;

  Bar() {
    j = 2;
  }

  {
    j = 3;
  }

  @Override
  protected int getValue() {
    return j;
  }
}

  /* Output:
            2
            0
            2
 *///:~
