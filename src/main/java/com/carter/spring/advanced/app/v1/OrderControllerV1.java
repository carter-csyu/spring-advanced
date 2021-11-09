package com.carter.spring.advanced.app.v1;

import com.carter.spring.advanced.trace.TraceStatus;
import com.carter.spring.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/orders")
    public String order(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.start("OrderController.order()");
            orderService.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        return "ok";
    }
}
