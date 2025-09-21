package io.mawa.spring.core.aopapi.proxyfactorybean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleDebugInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("SimpleDebugInterceptor: Before method '" + invocation.getMethod().getName() + "'");
        Object result = invocation.proceed();
        System.out.println("SimpleDebugInterceptor: After method '" + invocation.getMethod().getName() + "', returned '" + result + "'");
        return result;
    }
}
