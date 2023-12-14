package com.guissisoftware.starter;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import com.guissisoftware.starter.utils.AppProperties;
import com.guissisoftware.starter.utils.AppService;
import com.guissisoftware.starter.utils.DbConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class StarterApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(StarterApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(StarterApplication.class, args);
		log.info("CourseTrackerApplication started successfully with " +
				"Log4j2 configuration");
		DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);
		Environment env = applicationContext.getBean(Environment.class);
		log.info("Configured application time out timeout {} ", env.getProperty("server.port"));
		log.info(dbConfiguration.toString());
		AppService appService = applicationContext.getBean(AppService.class);
		log.info(appService.getAppProperties().toString());
	}

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
		System.out.println("Application ready event generated at " + new Date(applicationReadyEvent.getTimestamp()));
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("CommandLineRunner has executed");
	}
}
