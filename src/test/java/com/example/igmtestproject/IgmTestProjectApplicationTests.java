package com.example.igmtestproject;

import com.example.igmtestproject.services.HTMLGeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class IgmTestProjectApplicationTests {

	@Autowired
	HTMLGeneratorService htmlGeneratorService;

	@Test
	void FirstTaskTest() throws ExecutionException, InterruptedException {

		long start = System.currentTimeMillis();
		String htmlFile = htmlGeneratorService.generateHTML();
		long time = System.currentTimeMillis() - start;

		// Added 0.5 secs for leeway, but should not take more than 2 secs
		Assertions.assertTrue(time < 2500L);

		// Also check the string is not empty
		Assertions.assertFalse(htmlFile.isBlank());
	}

}
