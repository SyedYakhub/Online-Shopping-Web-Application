package com.springboot.shoppingapporderservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.shoppingapporderservice.dto.OrderRequest;
import com.springboot.shoppingapporderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orderservice")
@RequiredArgsConstructor
public class OrderController 
{
   
   private final OrderService orderService;
	
   @PostMapping("/")
   @ResponseStatus(HttpStatus.CREATED)
   @CircuitBreaker(name ="inventory", fallbackMethod = "fallbackMethod")
   @TimeLimiter(name ="inventory")
   @Retry(name ="inventory")
   public CompletableFuture<String> placeorder(@RequestBody OrderRequest orderRequest)
   {
	   return CompletableFuture.supplyAsync(()-> orderService.placeorder(orderRequest));
	   
   }
   
   
   public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException)
   {
	   return CompletableFuture.supplyAsync(()-> "Oops! something went wrong, please order after sometime. Thank You!");
   }
   
}
