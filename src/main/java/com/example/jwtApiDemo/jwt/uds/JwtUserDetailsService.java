package com.example.jwtApiDemo.jwt.uds;

import com.example.jwtApiDemo.jwt.JwtUser;
import com.example.jwtApiDemo.jwt.JwtUserFactory;
import com.example.jwtApiDemo.models.User;
import com.example.jwtApiDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    public final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + "username" + "not found");
        }

        JwtUser jwtUser = JwtUserFactory.creatuser(user);
        return jwtUser;
    }
}
