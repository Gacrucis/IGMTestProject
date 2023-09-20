package com.example.igmtestproject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExponentialBackoff {
    // Maximum number of retries
    int maxRetries() default 3;
    // Initial delay in milliseconds
    long initialDelayMillis() default 1000;
    // Delay time growth rate, goes like (currentDelay)^(growthRate)
    double growthRate() default 1.1;

}