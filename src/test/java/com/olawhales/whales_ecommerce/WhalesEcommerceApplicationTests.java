package com.olawhales.whales_ecommerce;

import com.olawhales.whales_ecommerce.data.model.UserRole;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UserReg;
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
		SignUpRequest request = new SignUpRequest();
		Users user = new Users();
		user.setUserName(request.getUserName());
		user.setEmail(request.getEmail());
		user.setContact(request.getContact());
		user.setPassword(request.getPassword());
		user.setUserRole(UserRole.SELLER);
		userRepository.save(user);
		UserReg response = userServiceImp.register(new SignUpRequest());
		response.setMessage("REGISTERED SUCCESSFUL");
		assertEquals(response.getMessage() , "REGISTERED SUCCESSFUL");
	}

}
