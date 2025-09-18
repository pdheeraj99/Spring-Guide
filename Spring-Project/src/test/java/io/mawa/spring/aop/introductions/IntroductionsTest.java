package io.mawa.spring.aop.introductions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.mawa.spring.aop.introductions.aspect.UsageTrackingAspect;
import io.mawa.spring.aop.introductions.model.DefaultUsageTracked;
import io.mawa.spring.aop.introductions.model.UsageTrackable;
import io.mawa.spring.aop.introductions.service.ReportGeneratorService;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class to demonstrate AOP Introductions.
 */
@SpringBootTest(classes = {ReportGeneratorService.class, DefaultUsageTracked.class, UsageTrackingAspect.class})
@EnableAspectJAutoProxy // Important: Enables AOP proxying for the test context
public class IntroductionsTest {

    @Autowired
    private ReportGeneratorService reportService;

    @Test
    void testIntroduction_SucceedsWithAspect() {
        System.out.println("--- Running test: Verifying Introduction ---");

        // Step 1: Verify the bean exists
        assertNotNull(reportService);

        // Step 2: Call a method on the service. Our aspect should intercept this.
        reportService.generateReport("Test Report");

        // Step 3: Cast the service bean to the introduced interface.
        // This is the core of the test. It will fail with ClassCastException if the aspect is not working.
        UsageTrackable trackable = (UsageTrackable) reportService;
        System.out.println("Successfully cast service to UsageTrackable interface.");

        // Step 4: Verify the introduced functionality.
        // The @Before advice in our aspect should have incremented the counter.
        assertEquals(1, trackable.getUseCount(), "Usage count should be 1 after one method call.");
        System.out.println("Verified that usage count is 1.");

        // Step 5: Call another method and verify the count increments.
        reportService.generateReport("Another Test Report");
        assertEquals(2, trackable.getUseCount(), "Usage count should be 2 after two method calls.");
        System.out.println("Verified that usage count is now 2.");

        System.out.println("--- Test successful: AOP Introduction works as expected! ---");
    }
}
