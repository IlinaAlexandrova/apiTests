package apitests;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({ExtentITestListenerClassAdapter.class})
public class BaseApiTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseApiTest.class);

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        logger.info("BEGIN TEST -> {}", "Report name");

    }
}
