package com.carter.spring.advanced.trace.logtrace;

import com.carter.spring.advanced.trace.TraceId;
import com.carter.spring.advanced.trace.TraceStatus;
import java.util.Objects;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EXCEPTION_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus start(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder.get();
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
        TraceId traceId = traceIdHolder.get();
        traceIdHolder.set(Objects.isNull(traceId) ? new TraceId() : traceId.createNextId());
    }

    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove();
        } else {
            traceIdHolder.set(traceId.createPreviousId());
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
