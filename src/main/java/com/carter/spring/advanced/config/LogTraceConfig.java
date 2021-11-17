package com.carter.spring.advanced.config;

import com.carter.spring.advanced.trace.logtrace.FieldLogTrace;
import com.carter.spring.advanced.trace.logtrace.LogTrace;
import com.carter.spring.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
