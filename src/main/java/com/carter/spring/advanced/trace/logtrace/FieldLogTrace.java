package com.carter.spring.advanced.trace.logtrace;

import com.carter.spring.advanced.trace.TraceId;
import com.carter.spring.advanced.trace.TraceStatus;
import java.util.Objects;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EXCEPTION_PREFIX = "<X-";

    private TraceId traceIdHolder;

    @Override
    public TraceStatus start(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder;
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        return new TraceStatus(traceId, System.currentTimeMillis(), message);
    }

    @Override
    public void end(TraceStatus traceStatus) {
        complete(traceStatus);
    }

    @Override
    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
    }

    private void syncTraceId() {
        traceIdHolder = Objects.isNull(traceIdHolder) ? new TraceId() : traceIdHolder.createNextId();
    }

    private void releaseTraceId() {
        if (traceIdHolder.isFirstLevel()) {
            traceIdHolder = null;
        } else {
            traceIdHolder = traceIdHolder.createPreviousId();
        }
    }

    private void complete(TraceStatus traceStatus) {
        TraceId traceId = traceStatus.getTraceId();
        log.info("[{}] {}{} duration={}ms",
            traceId.getId(),
            addSpace(END_PREFIX, traceId.getLevel()),
            traceStatus.getMessage(),
            System.currentTimeMillis() - traceStatus.getStartedAt());

        releaseTraceId();
    }

    private void complete(TraceStatus traceStatus, Exception e) {
        TraceId traceId = traceStatus.getTraceId();
        log.info("[{}] {}{} duration={}ms ex={}",
            traceId.getId(),
            addSpace(EXCEPTION_PREFIX, traceId.getLevel()),
            traceStatus.getMessage(),
            System.currentTimeMillis() - traceStatus.getStartedAt(),
            e.toString());

        releaseTraceId();
    }


    private String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, level).forEach(lv -> sb.append((lv == level -1) ? "|" + prefix : "|   "));

        return sb.toString();
    }
}
