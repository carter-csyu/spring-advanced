package com.carter.spring.advanced.trace.template;

import com.carter.spring.advanced.trace.template.code.template.Callback;
import com.carter.spring.advanced.trace.template.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TimeLogTemplateTest {

    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    void callbackV2() {
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(() -> log.info("비즈니스 로직1 실행"));

        template.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
