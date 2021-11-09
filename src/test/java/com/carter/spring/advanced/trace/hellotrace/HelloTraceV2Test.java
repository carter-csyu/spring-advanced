package com.carter.spring.advanced.trace.hellotrace;

import static org.junit.jupiter.api.Assertions.*;

import com.carter.spring.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void start_end_trace_print() {
        // given
        HelloTraceV2 trace = new HelloTraceV2();

        // when
        TraceStatus status1 = trace.start("first");
        TraceStatus status2 = trace.startSync(status1.getTraceId(), "second");
        TraceStatus status3 = trace.startSync(status2.getTraceId(), "third");
        trace.end(status3);
        trace.end(status2);
        trace.end(status1);

        //then
    }

    @Test
    void start_exception_trace_print() {
        // given
        HelloTraceV2 trace = new HelloTraceV2();

        // when
        TraceStatus status1 = trace.start("first");
        TraceStatus status2 = trace.startSync(status1.getTraceId(), "second");
        TraceStatus status3 = trace.startSync(status2.getTraceId(), "third");
        trace.exception(status3, new IllegalStateException());
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());

        //then
    }

}