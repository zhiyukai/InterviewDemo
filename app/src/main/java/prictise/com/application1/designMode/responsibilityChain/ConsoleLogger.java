package prictise.com.application1.designMode.responsibilityChain;

/**
 * @Author zhisiyi
 * @Date 2019.08.23 17:07
 * @Comment
 */
public class ConsoleLogger extends AbstractLogger {

  public ConsoleLogger(int level) {
    this.level = level;
  }

  @Override
  protected void write(String message) {
    System.out.println("Standard Console::Logger :" + message);
  }

}
