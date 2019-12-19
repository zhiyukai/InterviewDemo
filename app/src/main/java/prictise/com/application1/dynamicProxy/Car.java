package prictise.com.application1.dynamicProxy;

public class Car implements IVehical {

  @Override
  public void run(String s, int a) {
    System.out.println("Car会跑");
  }
}
