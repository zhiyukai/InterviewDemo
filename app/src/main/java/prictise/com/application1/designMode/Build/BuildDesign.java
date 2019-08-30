package prictise.com.application1.designMode.Build;

/**
 * @Author zhisiyi
 * @Date 2019.08.23 01:54
 * @Comment
 */
public class BuildDesign {

  public static void main(String[] args) {
    PersonNormal2 person = new PersonNormal2("张三", "男");
    person.setAge("22");
    person.setCareer("程序员");

    /**
     * 是不是看上去逼格瞬间提高了，
     * 非必须的属性可以根据需要任意设置，非常灵活，而且这样先设置属性再创建对象，
     * 最终获取的对象一定是你预期的完整对象，不会像用之前set的方法创建的对象可能还没有设置完全。
     * 好了，写完之后，你迫不及待的把这个Person类提交给了Boss，果然Boss对这种对象创建方式非常满意。
     */
    PersonBuilder personBuilder = new PersonBuilder.Builder("张三", "男")
        .age(12)
        .money("1000000")
        .car("宝马")
        .build();
  }
}
