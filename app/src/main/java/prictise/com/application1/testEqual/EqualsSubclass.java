package prictise.com.application1.testEqual;

/**
 * @Author zhisiyi
 * @Date 2019.12.16 10:50
 * @Comment
 */
public class EqualsSubclass extends EqualsSuper {

    @Override
    protected void testEquals() {
//        super.testEquals();
        System.out.println("subclass test Equals.");
    }
}
