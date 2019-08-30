package prictise.com.application1.designMode.responsibilityChain;

/**
 * @Author zhisiyi
 * @Date 2019.08.23 17:09
 * @Comment
 */
public class Main {

  public static void main(String[] args) {
    AbstractLogger logger = ChainPatternDemo.getChainOfLoggers();
    logger.logMessage(1, "一级日志记录");
    System.out.println("--------------------------------");
    logger.logMessage(2, "二级日志记录");
    System.out.println("--------------------------------");
    logger.logMessage(3, "三级日志记录");
  }
}
