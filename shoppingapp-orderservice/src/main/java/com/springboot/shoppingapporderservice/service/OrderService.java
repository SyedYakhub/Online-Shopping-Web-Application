package com.springboot.shoppingapporderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.shoppingapporderservice.dto.InventoryResponse;
import com.springboot.shoppingapporderservice.dto.OrderLineItemsDto;
import com.springboot.shoppingapporderservice.dto.OrderRequest;
import com.springboot.shoppingapporderservice.model.Order;
import com.springboot.shoppingapporderservice.model.OrderLineItems;
import com.springboot.shoppingapporderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService 
{
    
	private final OrderRepository orderRepository;
	
	private final WebClient.Builder webClientBuilder;
	
	public String placeorder(OrderRequest orderRequest)
    {
    	Order order = new Order();
    	order.setOrderNumber(UUID.randomUUID().toString());
    	
    	List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
    	
    	order.setOrderLineItemsList(orderLineItems);
    	
    	
    	List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // Call Inventory Service, and place order if product is in
        // stock
    	
        
            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventoryservice/",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponse::isInStock);

            if (allProductsInStock) 
            {
                orderRepository.save(order);
                
                return "Order place succesfully!";
            } 
            
            else 
            {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
            
    }
    
    
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) 
    {
        OrderLineItems orderLineItems = new OrderLineItems();
        
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
