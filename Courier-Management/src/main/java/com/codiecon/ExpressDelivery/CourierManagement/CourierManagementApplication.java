package com.codiecon.ExpressDelivery.CourierManagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Executor;

@EnableSwagger2
@SpringBootApplication
public class CourierManagementApplication extends SpringBootServletInitializer {

	@Value("${courier_management.async.process.core.pool.size:3}")
	private int corePoolSize;
	@Value("${courier_management.async.process.queue.capacity:500}")
	private int queueCapacity;
	@Value("${courier_management.async.process.max.pool.size:15}")
	private int maxPoolSize;

	public static void main(String[] args) {
		SpringApplication.run(CourierManagementApplication.class, args);
	}

	@Bean(name = "bookCouriers")
	public Executor getExecutorForCrowdIncentiveUpload() {
		return createExecutor();
	}

	private Executor createExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.initialize();
		return executor;
	}

	@Bean(name = "courierAssigned")
	public Executor assignedCourierAsync() {
		return createExecutor();
	}

	@Bean(name = "sendEmail")
	public Executor sendEmailAsync() {
		return createExecutor();
	}

}
