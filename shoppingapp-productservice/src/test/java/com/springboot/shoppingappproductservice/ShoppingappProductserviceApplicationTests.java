package com.springboot.shoppingappproductservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.shoppingapp.productservice.ShoppingappProductserviceApplication;
import com.springboot.shoppingapp.productservice.dto.ProductRequest;
import com.springboot.shoppingapp.productservice.repository.ProductRepository;

@SpringBootTest(classes = ShoppingappProductserviceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
class ShoppingappProductserviceApplicationTests 
{

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
    @Autowired
    private ProductRepository productRepository;
	
	@DynamicPropertySource
	static void SetProperties(DynamicPropertyRegistry dynamicPropertyRegistry)
	{
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	
	
	@Test
    void shouldCreateProduct() throws Exception 
	{
        ProductRequest productRequest = getProductRequest();
        
        String productRequestString = objectMapper.writeValueAsString(productRequest);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/productservice/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());
        
        Assertions.assertEquals(1, productRepository.findAll().size());
    }
	
	
	private ProductRequest getProductRequest() 
	{
        return ProductRequest.builder()
                .name("iPhone 12")
                .description("iPhone 12")
                .price(BigDecimal.valueOf(1200))
                .build();
    }


}
