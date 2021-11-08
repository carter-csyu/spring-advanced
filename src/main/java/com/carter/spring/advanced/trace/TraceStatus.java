package com.carter.spring.advanced.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TraceStatus {

    private final TraceId traceId;
    private final Long startedAt;
    private final String message;

}
