package com.guissisoftware.starter;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import com.guissisoftware.starter.utils.DbConfiguration;

@SpringBootApplication
public class StarterApplication {

	private static final Logger log = LoggerFactory.getLogger(StarterApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(StarterApplication.class, args);
		DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);
		Environment env = applicationContext.getBean(Environment.class);
		log.info("Configured application time out "+ env.getProperty("app.timeout"));
		log.info(dbConfiguration.toString());
	}

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
		System.out.println("Application ready event generated at " + new Date(applicationReadyEvent.getTimestamp()));
	}

}
