package com.guissisoftware.starter;

import java.util.Date;
import java.util.Set;

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

import com.guissisoftware.starter.model.Course;
import com.guissisoftware.starter.model.User;
import com.guissisoftware.starter.utils.AppProperties;
import com.guissisoftware.starter.utils.AppService;
import com.guissisoftware.starter.utils.DbConfiguration;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

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
		Course course = new Course();
		course.setId(1);
		course.setRating(0);
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		violations.forEach(courseConstraintViolation -> log.error("A constraint violation has occurred. Violation details: [{}].", courseConstraintViolation));

		User user0 = new User("glenimal", "woloso");
		Validator validator1 = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<User>> violations1 = validator1.validate(user0);

		log.error("Password for user1 do not adhere to the password policy");
		violations1.forEach(constraintViolation -> log.error("Violation details: [{}].", constraintViolation.getMessage()));

		User user2 = new User("sbip02", "Sbip01$4UDfg");
		Validator validator3 = Validation.buildDefaultValidatorFactory().getValidator();
		violations1 = validator3.validate(user2);
		if(violations1.isEmpty()) {
			log.info("Password for user2 adhere to the password policy");
		}

		User user3 = new User("sbip03", "Sbip01$4UDfgggg");
		violations1 = validator.validate(user3);
		log.error("Password for user3 violates maximum repetitive rule");
		violations1.forEach(constraintViolation -> log.error("Violation details: [{}].", constraintViolation.getMessage()));

		User user4 = new User("sbip04", "Sbip014UDfgggg");
		violations1 = validator.validate(user4);
		log.error("Password for user4 violates special character rule");
		violations1.forEach(constraintViolation -> log.error("Violation details: [{}].", constraintViolation.getMessage()));

	}
}
