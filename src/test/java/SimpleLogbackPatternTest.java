import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogbackPatternTest {

  private final static Logger LOG = LoggerFactory.getLogger(SimpleLogbackPatternTest.class);

  @Test
  public void testLogLevelPrintToConsole() {
    LOG.trace("trace");
    LOG.debug("debug");
    LOG.info("info");
    LOG.warn("warn");
    LOG.error("error");
  }
}
