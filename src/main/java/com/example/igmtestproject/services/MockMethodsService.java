package com.example.igmtestproject.services;

import com.example.igmtestproject.annotations.ExponentialBackoff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MockMethodsService {

    private static final Logger logger = LoggerFactory.getLogger(MockMethodsService.class);

    // These are for mocking a rate limited function
    private long lastResetTime;
    private int callCount;
    private static final int MAX_CALLS = 3;
    private static final long TIME_WINDOW_MILLIS = 3000L;

    @Async
    public CompletableFuture<String> getHTMLComponentHead() throws InterruptedException {
        String htmlHeader = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Simple HTML Page</title>
                </head>
                """;

        Thread.sleep(2000L);
        logger.info("Got header!");

        return CompletableFuture.completedFuture(htmlHeader);
    }

    @Async
    public CompletableFuture<String> getHTMLComponentBody() throws InterruptedException {
        String htmlBody = """
                <main>
                        <h2>Asynchronously generated HTML page</h2>
                        <p>Lorem ipsum dolor sit amet . . .</p>
                    </main>""";

        Thread.sleep(1000L);
        logger.info("Got body!");

        return CompletableFuture.completedFuture(htmlBody);
    }

    @Async
    public CompletableFuture<String> getHTMLComponentFooter() throws InterruptedException {
        String htmlFooter = """
                <footer>
                        <p>2023</p>
                    </footer>
                                
                </body>
                </html>""";

        Thread.sleep(1500L);
        logger.info("Got footer!");

        return CompletableFuture.completedFuture(htmlFooter);
    }

    /*
    Returns 200 OK if called less than 3 times over the last 3 seconds, else, Throws something
     */
    @ExponentialBackoff(maxRetries = 5, initialDelayMillis = 1000, growthRate = 1.05)
    public ResponseEntity<String> getRateLimitedString() throws Throwable {
        // Check if a minute has passed since the last reset
        if (System.currentTimeMillis() - lastResetTime >= TIME_WINDOW_MILLIS) {
            lastResetTime = System.currentTimeMillis();
            callCount = 0;
        }

        // Check if the maximum number of calls has been reached
        if (callCount < MAX_CALLS) {
            callCount++;
            return ResponseEntity.ok("OK");
        } else {
            logger.warn("API rate limit reached!");
            // Generic throwable just for example purposes
            throw new Throwable("Too many requests!");
        }
    }
}
