package com.intuit.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.intuit.demo.IntuitUrlTrackerApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntuitUrlTrackerApplication.class)
@WebAppConfiguration
public class IntuitUrlTrackerApplicationTests {

	@Test
	public void contextLoads() {
		assertEquals(1, 1);
	}

}
