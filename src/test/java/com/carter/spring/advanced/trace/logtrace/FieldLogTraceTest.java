package com.carter.spring.advanced.trace.logtrace;

import com.carter.spring.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

    @Test
    void start_end_trace_print() {
        // given
        FieldLogTrace trace = new FieldLogTrace();

        // when
        TraceStatus status1 = trace.start("first");
        TraceStatus status2 = trace.start("second");
        TraceStatus status3 = trace.start("third");
        trace.end(status3);
        trace.end(status2);
        trace.end(status1);

        //then
    }

    @Test
    void start_exception_trace_print() {
        // given
        FieldLogTrace trace = new FieldLogTrace();

        // when
        TraceStatus status1 = trace.start("first");
        TraceStatus status2 = trace.start("second");
        TraceStatus status3 = trace.start("third");
        trace.exception(status3, new IllegalStateException());
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());

        //then
    }
}