package com.carter.spring.advanced.trace.tracelocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String> nameStore = new ThreadLocal<>();


    public String store(String name) {
        log.info("start name={} -> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1_000);
        log.info("end name={} -> nameStore={}", name, nameStore.get());

        return nameStore.get();
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
