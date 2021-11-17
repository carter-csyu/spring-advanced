package com.carter.spring.advanced.trace.tracelocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {

    private String nameStore;

    public String store(String name) {
        log.info("start name={} -> nameStore={}", name, nameStore);
        nameStore = name;
        sleep(1_000);
        log.info("end name={} -> nameStore={}", name, nameStore);

        return nameStore;
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
