package com.example.baze2.services;

import com.example.baze2.common.MyUserDetails;
import com.example.baze2.dao.models.User;
import com.example.baze2.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    MyUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found: "+username));
        return user.map(MyUserDetails::new).get();
    }

    public String addUser(String userName, String password) {
        if (userName==""||password.length()<5){
            return "Bad username or password";
        }
        Optional<User> user=userRepository.findByUserName(userName);

        if(user.isEmpty()) {
            this.userRepository.save(new User(userName, bCryptPasswordEncoder.encode(password), true, "ROLE_ADMIN"));
            return "You successfully registered";
        }else{
            return "Username already taken";
        }
    }
}
