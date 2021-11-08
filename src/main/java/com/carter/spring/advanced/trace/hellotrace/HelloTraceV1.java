package com.carter.spring.advanced.trace.hellotrace;

import com.carter.spring.advanced.trace.TraceId;
import com.carter.spring.advanced.trace.TraceStatus;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EXCEPTION_PREFIX = "<X-";

    public TraceStatus start(String message) {
        TraceId traceId = new TraceId();

        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        return new TraceStatus(traceId, System.currentTimeMillis(), message);
    }

    public void end(TraceStatus traceStatus) {
        complete(traceStatus);
    }

    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e );
    }

    private void complete(TraceStatus traceStatus) {
        TraceId traceId = traceStatus.getTraceId();
        log.info("[{}] {}{} duration={}ms",
            traceId.getId(),
            addSpace(END_PREFIX, traceId.getLevel()),
            traceStatus.getMessage(),
            System.currentTimeMillis() - traceStatus.getStartedAt());
    }

    private void complete(TraceStatus traceStatus, Exception e) {
        TraceId traceId = traceStatus.getTraceId();
        log.info("[{}] {}{} duration={}ms ex={}",
            traceId.getId(),
            addSpace(EXCEPTION_PREFIX, traceId.getLevel()),
            traceStatus.getMessage(),
            System.currentTimeMillis() - traceStatus.getStartedAt(),
            e.toString());
    }

    private String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, level).forEach(lv -> sb.append((lv == level -1) ? "|" + prefix : "|   "));

        return sb.toString();
    }
}
