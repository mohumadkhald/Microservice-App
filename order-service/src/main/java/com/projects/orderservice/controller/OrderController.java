package com.projects.orderservice.controller;

import com.projects.orderservice.dto.OrderRequest;
import com.projects.orderservice.service.OrderServiceImpl;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@AllArgsConstructor
@RequestMapping("api/order")
public class OrderController {

    private final Environment environment;
    private final OrderServiceImpl orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallback")
    @TimeLimiter(name = "inventory")
    @RateLimiter(name = "inventory")
    @Bulkhead(name = "inventory")
    public CompletionStage<String> createOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println(environment.getProperty("local.server.port"));

        // Create a Mono that represents the asynchronous processing
        Mono<String> resultMono = Mono.create(monoSink -> {
            try {
                orderService.placeOrder(orderRequest);
                monoSink.success("order success");
            } catch (Exception e) {
                monoSink.error(e);
            }
        });

        // Convert Mono to CompletionStage
        return resultMono.toFuture();
    }

    public CompletionStage<String> fallback(OrderRequest orderRequest, Throwable throwable) {
        // Handle the fallback logic here
        Mono<String> resultMono = Mono.create(monoSink -> {
            try {
                monoSink.success("order not success");
            } catch (Exception e) {
                monoSink.error(e);
            }
        });

        // Convert Mono to CompletionStage
        return resultMono.toFuture();
    }
}
