package io.mawa.spring.core.aop.service;

/**
 * The default implementation for the {@link UsageTrackable} interface.
 * An instance of this class will be provided by the AOP proxy for any bean
 * that is targeted by the Introduction aspect.
 */
public class DefaultUsageTrackable implements UsageTrackable {

    private int count = 0;

    @Override
    public void trackUsage() {
        System.out.println("--> [Usage Tracking]: Incrementing usage count.");
        count++;
    }

    @Override
    public int getUsageCount() {
        return count;
    }
}
