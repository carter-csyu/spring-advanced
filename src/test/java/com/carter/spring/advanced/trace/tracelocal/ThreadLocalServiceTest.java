package com.carter.spring.advanced.trace.tracelocal;

import com.carter.spring.advanced.trace.tracelocal.code.FieldService;
import com.carter.spring.advanced.trace.tracelocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void nonIssue() {
        // given
        log.info("main thread start");
        Thread threadA = new Thread(() -> threadLocalService.store("userA"));
        threadA.setName("thread-userA");
        Thread threadB = new Thread(() -> threadLocalService.store("userB"));
        threadB.setName("thread-userB");

        // when
        // then
        threadA.start();
        sleep(2_000);
        threadB.start();
        sleep(3_000);
        log.info("main thread exit");
    }

    @Test
    void concurrencyNonIssue() {
        // given
        log.info("main thread start");
        Thread threadA = new Thread(() -> threadLocalService.store("userA"));
        threadA.setName("thread-userA");
        Thread threadB = new Thread(() -> threadLocalService.store("userB"));
        threadB.setName("thread-userB");

        // when
        // then
        threadA.start();
        sleep(100);
        threadB.start();
        sleep(3_000);
        log.info("main thread exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Interrupted. {}", e.getMessage());
        }
    }
}
