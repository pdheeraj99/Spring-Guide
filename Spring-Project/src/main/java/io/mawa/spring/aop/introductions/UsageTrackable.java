package io.mawa.spring.aop.introductions;

/**
 * The new functionality (interface) that we want to introduce to other beans.
 * It defines a contract for tracking usage.
 */
public interface UsageTrackable {

    void incrementUseCount();

    int getUseCount();
}
