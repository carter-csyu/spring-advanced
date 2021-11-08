package com.carter.spring.advanced.trace.hellotrace;


import com.carter.spring.advanced.trace.TraceId;
import com.carter.spring.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

    @Test
    void begin_end_trace_print() {
        // given
        HelloTraceV1 trace = new HelloTraceV1();

        // when
        TraceStatus traceStatus = trace.start("method name");

        trace.end(traceStatus);

        // then
    }

    @Test
    void begin_exception_trace_print() {
        // given
        HelloTraceV1 trace = new HelloTraceV1();

        // when
        TraceStatus traceStatus = trace.start("method name");
        trace.exception(traceStatus, new IllegalStateException());

        // then
    }
}