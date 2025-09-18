package io.mawa.spring.core.aop.service;

/**
 * An interface representing a new functionality that can be "introduced"
 * to other beans via AOP.
 * <p>
 * This allows us to track how many times a service is used.
 */
public interface UsageTrackable {

    /**
     * Increments the usage count of the object.
     */
    void trackUsage();

    /**
     * @return The current usage count.
     */
    int getUsageCount();
}
