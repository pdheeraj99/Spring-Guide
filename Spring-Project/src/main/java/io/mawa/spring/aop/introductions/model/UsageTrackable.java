package io.mawa.spring.aop.introductions.model;

/**
 * Interface to be "introduced" to other beans.
 * It provides a simple mechanism to track usage counts.
 */
public interface UsageTrackable {

    /**
     * Increments the usage counter.
     */
    void incrementUseCount();

    /**
     * Returns the current usage count.
     * @return the number of times the bean has been used.
     */
    int getUseCount();
}
