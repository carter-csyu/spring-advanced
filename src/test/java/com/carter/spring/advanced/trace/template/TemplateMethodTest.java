package com.carter.spring.advanced.trace.template;

import com.carter.spring.advanced.trace.template.code.AbstractTemplate;
import com.carter.spring.advanced.trace.template.code.SubClassLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void testNonTemplateMethod() {
        logic1();
        logic2();
    }

    private void logic1() {
        long start = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료

        log.info("duration: {}ms", System.currentTimeMillis() - start);
    }

    private void logic2() {
        long start = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        // 비즈니스 로직 종료

        log.info("duration: {}ms", System.currentTimeMillis() - start);
    }

    @Test
    void testTemplateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic1();
        template2.execute();
    }

    @Test
    void testTemplateMethodV2() {
        new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        }.execute();

        new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        }.execute();
    }
}
