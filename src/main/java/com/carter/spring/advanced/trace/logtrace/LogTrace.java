package com.carter.spring.advanced.trace.logtrace;

import com.carter.spring.advanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus start(String message);

    void end(TraceStatus traceStatus);

    void exception(TraceStatus traceStatus, Exception e);

}
