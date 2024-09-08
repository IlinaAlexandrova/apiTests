package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.service.ExtentTestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(TestWrapper.class);

    public static ExtentTest test() {
        return ExtentTestManager.getTest();
    }

    public static void info(String message) {
        if(test() != null) {
            ExtentTestManager.getTest().info(message);
        }
        logger.info(message);
    }

    public static void pass(String message) {
        if(test() != null) {
            ExtentTestManager.getTest().pass(message);
        }
        logger.info(message);
    }

    public static void debug(String message) {
        if(test() != null) {
            ExtentTestManager.getTest().info(message);
        }
        logger.debug(message);
    }

    public static void warning(String message) {
        if(test() != null) {
            ExtentTestManager.getTest().warning(message);
        }
        logger.warn(message);
    }

    public static void fail(String message) {
        if(test() != null) {
            ExtentTestManager.getTest().fail(message);
        }
        logger.error(message);
    }

    public static void info(Markup markup) {
        ExtentTestManager.getTest().info(markup);
        logger.info(markup.getMarkup());
    }

    public static void info(Integer responseStatusCode) {
    }
}