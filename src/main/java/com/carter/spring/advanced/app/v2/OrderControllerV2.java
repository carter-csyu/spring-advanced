package com.carter.spring.advanced.app.v2;

import com.carter.spring.advanced.trace.TraceStatus;
import com.carter.spring.advanced.trace.hellotrace.HelloTraceV1;
import com.carter.spring.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/orders")
    public String order(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.start("OrderController.order()");
            orderService.orderItem(itemId, status.getTraceId());
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        return "ok";
    }
}
