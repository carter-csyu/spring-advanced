package com.carter.spring.advanced.app.v3;

import com.carter.spring.advanced.trace.TraceStatus;
import com.carter.spring.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/orders")
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
