package com.example.bevarage_service.Service;

import com.example.bevarage_service.Model.Users;
import com.example.bevarage_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= userRepository.findUserByUserName(username);
        if(user!=null){
            System.out.println("here comes second");
            return user;
        }
        else
            throw new UsernameNotFoundException("username not found in db");
    }

}
