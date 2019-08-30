package prictise.com.application1.designMode.Build;

/**
 * @Author zhisiyi
 * @Date 2019.08.23 01:56
 * @Comment 需要创建一个不可变的Person对象，这个Person可以拥有以下几个属性：
 * 名字、性别、年龄、职业、车、鞋子、衣服、钱、房子。其中名字和性别是必须有的。
 * @Url https://www.jianshu.com/p/afe090b2e19c
 */
public class PersonNormal {

  /*名字（必须）*/
  private final String name;
  /*性别（必须）*/
  private final String gender;
  /*年龄（非必须）*/
  private final String age;
  /*鞋子（非必须）*/
  private final String shoes;
  /*衣服（非必须）*/
  private final String clothes;
  /*钱（非必须）*/
  private final String money;
  /*房子（非必须）*/
  private final String house;
  /*汽车（非必须）*/
  private final String car;
  /*职业（非必须）*/
  private final String career;

  public PersonNormal(String name, String gender, String age, String shoes, String clothes, String money, String house,
      String car, String career) {
    this.name = name;
    this.gender = gender;
    this.age = age;
    this.shoes = shoes;
    this.clothes = clothes;
    this.money = money;
    this.house = house;
    this.car = car;
    this.career = career;
  }

  public PersonNormal(String name, String gender) {
    this(name, gender, null, null, null, null, null, null, null);
  }
}
