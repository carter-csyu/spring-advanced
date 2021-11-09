package com.carter.spring.advanced.app.v2;

import com.carter.spring.advanced.trace.TraceId;
import com.carter.spring.advanced.trace.TraceStatus;
import com.carter.spring.advanced.trace.hellotrace.HelloTraceV1;
import com.carter.spring.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(String itemId, TraceId traceId) {

        TraceStatus status = null;
        try {
            status = trace.startSync(traceId, "OrderRepositoryV1.save()");
            if ("ex".equals(itemId)) {
                throw new IllegalStateException("User-defined exception occurred.");
            }

            sleep(1_000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }


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