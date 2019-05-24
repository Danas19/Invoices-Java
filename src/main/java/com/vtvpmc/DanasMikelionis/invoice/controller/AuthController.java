package com.vtvpmc.DanasMikelionis.invoice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.vtvpmc.DanasMikelionis.invoice.model.LoginRequest;

/*
 * <http entry-point-ref="restAuthenticationEntryPoint">
    <intercept-url pattern="/api/admin/**" access="ROLE_ADMIN"/>
 
    <form-login
      authentication-success-handler-ref="mySuccessHandler"
      authentication-failure-handler-ref="myFailureHandler" />
 
    <logout />
</http>
 
<beans:bean id="mySuccessHandler"
  class="org.rest.security.MySavedRequestAwareAuthenticationSuccessHandler"/>
<beans:bean id="myFailureHandler" class=
  "org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler"/>
 
<authentication-manager alias="authenticationManager">
    <authentication-provider>
        <user-service>
            <user name="admin" password="adminPass" authorities="ROLE_ADMIN"/>
            <user name="user" password="userPass" authorities="ROLE_USER"/>
        </user-service>
    </authentication-provider>
</authentication-manager>
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(null);
    }

}