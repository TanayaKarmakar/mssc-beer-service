package com.app.msscbeerservice;

import com.app.msscbeerservice.web.controller.BeerController;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BeerController.class)
class MsscBeerServiceApplicationTests {
	MockMvc mockMvc;

	@Test
	void getBeerById() {
		//mockMvc.perform(get())
	}

	@Test
	void saveNewBeer() {

	}

	@Test
	void updateBeerById() {

	}

}
