package com.FirstProject.StudentManagement.Service;

import com.FirstProject.StudentManagement.Entity.Users;
import com.FirstProject.StudentManagement.Repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Users> users = usersRepo.findByUserName(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Users user = users.get(0);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
        if (user.getAuthorities() != null && !user.getAuthorities().isBlank()) {

            Arrays.stream(user.getAuthorities().split(",")).map(String::trim).map(String::toUpperCase).map(SimpleGrantedAuthority::new).forEach(authorities::add);
        }
        return org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).password(user.getUserPassword()).roles(user.getRole()).authorities(authorities).build();
    }
}
