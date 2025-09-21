package io.mawa.spring.core.aopapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PerformanceTimingAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.nanoTime();
        System.out.println("⏰ [AROUND ADVICE] - Starting timer before method execution...");

        try {
            // Proceed to the target method
            Object result = invocation.proceed();
            System.out.println("✅ [AROUND ADVICE] - Method executed successfully.");
            return result;
        } finally {
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.println("⏰ [AROUND ADVICE] - Method '" + invocation.getMethod().getName() + "' took " + duration + " ms to complete.");
        }
    }
}
