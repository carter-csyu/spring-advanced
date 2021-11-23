package com.carter.spring.advanced.app.v4;

import com.carter.spring.advanced.trace.TraceStatus;
import com.carter.spring.advanced.trace.logtrace.LogTrace;
import com.carter.spring.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                if ("ex".equals(itemId)) {
                    throw new IllegalStateException("User-defined exception occurred.");
                }

                sleep(1_000);
                return null;
            }
        }.execute("OrderController.request()");
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
