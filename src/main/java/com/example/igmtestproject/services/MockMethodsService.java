package com.example.igmtestproject.services;

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
    private static final long TIME_WINDOW_MILIS = 3000L;

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
    Returns 200 OK if called less than 20 times over the last 60 seconds, else, returns 429 Too Many Requests
     */
    public ResponseEntity<String> getRateLimitedString(){
        long currentTime = System.currentTimeMillis();

        // Check if a minute has passed since the last reset
        if (currentTime - lastResetTime >= TIME_WINDOW_MILIS) {
            lastResetTime = currentTime;
            callCount = 0;
        }

        // Check if the maximum number of calls has been reached
        if (callCount < MAX_CALLS) {
            callCount++;
            return ResponseEntity.ok("OK");
        } else {
            logger.warn("API rate limit reached!");
            return ResponseEntity.status(429).body("Too Many Requests");
        }
    }
}
