package com.carter.spring.advanced.app.v4;

import com.carter.spring.advanced.trace.logtrace.LogTrace;
import com.carter.spring.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/orders")
    public String order(String itemId) {
        return new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);

                return "ok";
            }
        }.execute("OrderController.request()");
    }
}
