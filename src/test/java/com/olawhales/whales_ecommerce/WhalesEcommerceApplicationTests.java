//package com.olawhales.whales_ecommerce;
//
//import com.olawhales.whales_ecommerce.data.model.UserRole;
//import com.olawhales.whales_ecommerce.data.model.Users;
//import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
//import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
//import com.olawhales.whales_ecommerce.dto.response.usersResponse.UserReg;
//import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;
//import com.olawhales.whales_ecommerce.services.UserRegisterImp;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
////@Transactional
//class WhalesEcommerceApplicationTests {
//
//    @Autowired
//    private UserRegisterImp userServiceImp;
//    @Autowired
//    private UserRepository userRepository;
//	@Autowired
//	private BCryptPasswordEncoder bcryptPasswordEncoder;;
//
//	@Test
//	void testThatUserCanRegister() {
//		SignUpRequest request = new SignUpRequest();
//		Users user = new Users();
//		user.setUserName("Olawale");
//		user.setEmail("olawale101111000@gmail.com");
//		user.setContact("08102790000");
//		user.setPassword(bcryptPasswordEncoder.encode("123456"));
//		System.out.println("This is the password " +request.getPassword());
//		user.setUserRole(UserRole.SELLER);
//		user.setDateCreated(LocalDateTime.now());
//		userRepository.save(user);
//		UserReg response = userServiceImp.register(new SignUpRequest());
//		response.setMessage("REGISTERED SUCCESSFUL");
//		assertEquals(response.getMessage() , "REGISTERED SUCCESSFUL");
//	}
//
//}
