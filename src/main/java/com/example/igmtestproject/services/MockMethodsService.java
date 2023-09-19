package com.example.igmtestproject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MockMethodsService {

    private static final Logger logger = LoggerFactory.getLogger(MockMethodsService.class);

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
}
