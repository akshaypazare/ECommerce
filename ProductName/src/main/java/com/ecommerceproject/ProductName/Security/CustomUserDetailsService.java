package com.ecommerceproject.ProductName.Security;

import com.ecommerceproject.ProductName.Entity.Role;
import com.ecommerceproject.ProductName.Entity.User;
import com.ecommerceproject.ProductName.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService { //14-1-23

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail)
        );
        //when there are two classes with the same name in the same project then do not perform an import using import keyword
        //instead perform the import using the package name as given below

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles())//we dont have this method so we will learn about this in class dated on 16-1-23
                //In mapRolesToAuthorities() method, we will convert the roles into set and supply it to the User class and we need not remember the code we can take that from internet
        );   //now after supplying three parameters to this class , we will get the userDetails object

    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).collect(Collectors.toList ());
    }
}