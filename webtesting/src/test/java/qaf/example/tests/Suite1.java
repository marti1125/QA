package qaf.example.tests;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import org.testng.annotations.Test;
import static com.qmetry.qaf.automation.step.CommonStep.*;

public class Suite1 extends WebDriverTestCase {

    @Test(description="Sample Test Scenario", groups={"SMOKE"})
    public void testGoogleSearch() {
        get("/");
        sendKeys("Git reporsitory QAF", "txt.searchbox");
        click("btn.search");
        verifyLinkWithPartialTextPresent("qaf");
    }

}
