package com.shreesh.fakestore;

import com.shreesh.fakestore.models.Product;
import com.shreesh.fakestore.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FakeStoreApplicationTests {

	private ProductRepository productRepository;
	@Autowired
	public FakeStoreApplicationTests(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void getProduct(){
		Product p = productRepository.doSomething(1L);
		System.out.println(p.getDescription() +" " + p.getTitle());
	}
}
