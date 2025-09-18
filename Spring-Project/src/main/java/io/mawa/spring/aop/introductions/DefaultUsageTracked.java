package io.mawa.spring.aop.introductions;

/**
 * The default implementation of the UsageTrackable interface.
 * An instance of this class will be attached to each target bean by the aspect.
 * This holds the state (useCount) for the introduced functionality.
 */
public class DefaultUsageTracked implements UsageTrackable {

    private int useCount;

    @Override
    public void incrementUseCount() {
        useCount++;
    }

    @Override
    public int getUseCount() {
        return useCount;
    }
}
