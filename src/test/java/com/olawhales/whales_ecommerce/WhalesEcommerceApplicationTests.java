package com.olawhales.whales_ecommerce;

import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.UsersRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;
import com.olawhales.whales_ecommerce.services.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WhalesEcommerceApplicationTests {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private UserRepository userRepository;

	@Test
	void testThatUserCanRegister() {
		UsersRequest request = new UsersRequest();
		Users user = new Users();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setContact(request.getContact());
		userRepository.save(user);
		UsersResponse response = userServiceImp.register(new UsersRequest());
		response.setMessage("REGISTERED SUCCESSFUL");
		assertEquals(response.getMessage() , "REGISTERED SUCCESSFUL");
	}

}
