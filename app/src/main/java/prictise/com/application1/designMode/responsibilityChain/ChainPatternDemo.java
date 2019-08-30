package prictise.com.application1.designMode.responsibilityChain;

/**
 * @Author zhisiyi
 * @Date 2019.08.23 17:07
 * @Comment
 */
public class ChainPatternDemo {

  public static AbstractLogger getChainOfLoggers() {

    AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
    AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
    AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

    errorLogger.setNextLogger(fileLogger);
    fileLogger.setNextLogger(consoleLogger);

    return errorLogger;
  }
}
