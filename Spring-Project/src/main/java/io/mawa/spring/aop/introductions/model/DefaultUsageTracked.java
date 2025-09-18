package io.mawa.spring.aop.introductions.model;

import org.springframework.stereotype.Component;

/**
 * A default implementation of the UsageTrackable interface.
 * This class holds the state and logic for tracking usage.
 *
 * Note: This class is NOT explicitly used by the target bean.
 * The Aspect will weave this implementation into the target bean.
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
