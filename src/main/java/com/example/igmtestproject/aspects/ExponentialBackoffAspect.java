package com.example.igmtestproject.aspects;

import com.example.igmtestproject.annotations.ExponentialBackoff;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExponentialBackoffAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExponentialBackoffAspect.class);

    @Around("@annotation(exponentialBackoff)")
    public Object applyExponentialBackoff(ProceedingJoinPoint joinPoint, ExponentialBackoff exponentialBackoff) throws Throwable {
        int maxRetries = exponentialBackoff.maxRetries();
        double currentDelayMillis = exponentialBackoff.initialDelayMillis();
        double growthRate = exponentialBackoff.growthRate();
        Throwable lastException = new Throwable("This should never happen");
        int retryCount = 0;

        while (retryCount < maxRetries) {

            // Try to run the function
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                // If i get some Throwable, log it, sleep and increase the delay
                lastException = e;
                retryCount++;
                logger.info(String.format("Sleeping %d milliseconds", (long) currentDelayMillis));
                Thread.sleep((long) (currentDelayMillis));
                currentDelayMillis = Math.pow(currentDelayMillis, growthRate);
            }

        }

        // If all retries fail, rethrow the last returned value
        throw lastException;
    }
}
