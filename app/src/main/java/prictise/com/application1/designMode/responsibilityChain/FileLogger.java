package prictise.com.application1.designMode.responsibilityChain;

/**
 * @Author zhisiyi
 * @Date 2019.08.23 17:09
 * @Comment
 */
public class FileLogger extends AbstractLogger {

  public FileLogger(int level) {
    this.level = level;
  }

  @Override
  protected void write(String message) {
    System.out.println("File Console::Logger" + message);
  }
}
