package com.ecommerceproject.ProductName.Controller;

import com.ecommerceproject.ProductName.Entity.Role;
import com.ecommerceproject.ProductName.Entity.User;
import com.ecommerceproject.ProductName.Payload.JWTAuthResponse;
import com.ecommerceproject.ProductName.Payload.LoginDto;
import com.ecommerceproject.ProductName.Payload.SignupDto;
import com.ecommerceproject.ProductName.Repository.RoleRepository;
import com.ecommerceproject.ProductName.Repository.UserRepository;
import com.ecommerceproject.ProductName.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class AuthController {  //18-1-23, this controller will help us in registering a user and login

    @Autowired
    private AuthenticationManager authenticationManager; //this is a built in class in spring security and this will help us to validate the username and password
    //now if we run the project the console will says that we should have to create a bean of this class only @Autowired will not do the work
    //Now go to the SecurityConfig file and create the bean of AuthenticationManager by overriding the method authenticationManager() which is presnet in WebSecurityConfigurerAdapter

    @Autowired
    private UserRepository userRepository;   //20-1-23, created to implement the signup feature

    @Autowired
    private PasswordEncoder passwordEncoder; //20-1-23, to encode the password which we are using for signup

    @Autowired
    private RoleRepository roleRepository;  //20-1-23, to set the role for a new user

    @Autowired
    private JwtTokenProvider tokenProvider;//20-1-23

    @PostMapping("/signin")   ////18-1-23, for login
    //20-1-23, change the generic of ResponseEntity and set to JWTAuthResponse to send the token and create this Response class
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOremail(),
                        loginDto.getPassword()
                ) //we are creating the token and supplying the username and password in the form of token
        );
        //if the credentials are correct it will move further otherwise it will stop here and return the message Bad credentials to postman

        SecurityContextHolder.getContext ().setAuthentication(authentication);
        //it will set Authentication token



        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }



    @PostMapping("/signup")   //20-1-23
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signUpDto) {
// add check for username exists in a DB
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
// add check for email exists in DB
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
// create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        //we need to set the role for new user, whether the user will be USER or ADMIN etc.
        Role roles = roleRepository.findByname("ROLE_ADMIN").get();   //we assigning this user as ADMIN, it will find that record and put that into and object
        user.setRoles(Collections.singleton(roles)); //now convert that object into set
        //Collections class has the singleton() method which will return the immutable set containing specified object

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

}
