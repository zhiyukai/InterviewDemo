package prictise.com.application1.designMode.responsibilityChain;

/**
 * @Author zhisiyi
 * @Date 2019.08.23 17:08
 * @Comment
 */
public class ErrorLogger extends AbstractLogger {

  public ErrorLogger(int level) {
    this.level = level;
  }

  @Override
  protected void write(String message) {
    System.out.println("Error Console::Logger: " + message);
  }
}
