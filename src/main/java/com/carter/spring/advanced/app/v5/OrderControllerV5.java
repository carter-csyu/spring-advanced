package com.carter.spring.advanced.app.v5;

import com.carter.spring.advanced.trace.callback.TraceTemplate;
import com.carter.spring.advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/orders")
    public String order(String itemId) {
        return template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);

            return "ok";
        });
    }
}
