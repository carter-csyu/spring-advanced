package com.carter.spring.advanced.app.v5;

import com.carter.spring.advanced.trace.callback.TraceTemplate;
import com.carter.spring.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId) {

        template.execute("OrderController.request()", () -> {
            if ("ex".equals(itemId)) {
                throw new IllegalStateException("User-defined exception occurred.");
            }

            sleep(1_000);
            return null;
        });
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
