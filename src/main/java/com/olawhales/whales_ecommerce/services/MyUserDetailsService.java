package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.UserPrincipal;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUserName(username).
                orElseThrow(()-> new UsernameNotFoundException("user not found"));

        return new UserPrincipal(users);
    }
}
