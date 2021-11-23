package com.carter.spring.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long start = System.currentTimeMillis();

        // 비즈니스 로직 실행
        call();
        // 비즈니스 로직 종료

        log.info("duration: {}ms", System.currentTimeMillis() - start);
    }

    protected abstract void call();
}
