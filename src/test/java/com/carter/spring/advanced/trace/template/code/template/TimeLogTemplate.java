package com.carter.spring.advanced.trace.template.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback) {
        long start = System.currentTimeMillis();

        // 비즈니스 로직 실행
        callback.call();
        // 비즈니스 로직 종료

        log.info("duration: {}ms", System.currentTimeMillis() - start);
    }
}
