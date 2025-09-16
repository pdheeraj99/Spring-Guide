package io.mawa.spring.core.ltw;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    // This "advice" will be woven into the MyEntity.doWork() method at load time.
    @Before("execution(* io.mawa.spring.core.ltw.MyEntity.doWork(..))")
    public void beforeWork() {
        System.out.println("MAGIC TAILOR ✂️: I am adding this line before doWork() executes!");
    }
}
