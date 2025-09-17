package io.mawa.spring.core.aop.aspectj.declaring;

import org.springframework.stereotype.Service;

/**
 * This is our target bean. In the future, our LoggingAspect will
 * add "wrapping paper" (advice) to the methods of this class.
 */
@Service
public class MyService {

    public MyService() {
        System.out.println("MyService (the 'gift') has been created! 🎁");
    }

    public void doSomething() {
        System.out.println("MyService is doing its core work.");
    }
}
