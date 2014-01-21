package com.kurento.kmf.connector.test.base;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.kurento.kmf.connector.BootApplication;
import com.kurento.kmf.spring.KurentoApplicationContextUtils;

public class BootBaseTest {

	protected static ConfigurableApplicationContext context;

	@BeforeClass
	public static void start() throws Exception {

		System.out.println("sssssssssssssssssssssssssssssssssssssssssxx");
		
		Properties properties = new Properties();
		properties.put("server.port", getPort());
		properties.put("debug", "");

		SpringApplication application = new SpringApplication(
				BootApplication.class);

		application.setDefaultProperties(properties);

		System.out.println("s------------------------------------------");
		
		context = application.run();
		
		System.out.println("XxxXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxxx");
	}

	@AfterClass
	public static void stop() {

		KurentoApplicationContextUtils
				.closeAllKurentoApplicationContexts(((WebApplicationContext) context)
						.getServletContext());

		context.close();
	}

	protected static String getPort() {
		String port = System.getProperty("http.port");
		if (port == null) {
			port = "7788";
		}
		return port;
	}
	
	public static void main(String[] args) throws Exception {
		start();
	}

}
