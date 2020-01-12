package com.example.jwtApiDemo.jwt;

import com.example.jwtApiDemo.models.Role;
import com.example.jwtApiDemo.models.Status;
import com.example.jwtApiDemo.models.User;
import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser creatuser(User user){
        return  new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getLastName(),
                user.getFirstName(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthority(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> userRole){
        return userRole.stream().map(role->new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }
}
