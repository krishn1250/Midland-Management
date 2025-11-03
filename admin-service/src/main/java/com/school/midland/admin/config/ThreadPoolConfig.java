package com.school.midland.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {
//
//    @Bean(name = "taskExecutor")
//    public ThreadPoolTaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10); // Minimum number of threads
//        executor.setMaxPoolSize(50); // Maximum number of threads
//        executor.setQueueCapacity(1000); // Queue size for waiting tasks
//        executor.setThreadNamePrefix("Notification-");
//        executor.initialize();
//        return executor;
//    }
}