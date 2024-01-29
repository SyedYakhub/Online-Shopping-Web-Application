package com.springboot.shoppingappinventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.shoppingappinventoryservice.model.Inventory;
import com.springboot.shoppingappinventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class ShoppingappInventoryserviceApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(ShoppingappInventoryserviceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository)
	{
		return args -> {
		      
			Inventory inventory = new Inventory();
			
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);
			
			
			
			Inventory inventory1 = new Inventory();
			
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);
			
			
			
			inventoryRepository.save(inventory);
			
			inventoryRepository.save(inventory1);
			
		};
	}

}
