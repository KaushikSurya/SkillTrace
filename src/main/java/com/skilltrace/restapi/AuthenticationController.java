package com.skilltrace.restapi;


import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilltrace.config.JwtTokenUtil;
import com.skilltrace.model.AuthToken;
import com.skilltrace.model.LoginCredentials;
import com.skilltrace.service.LoginService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody HashMap<String,String> map) throws AuthenticationException {
    	logger.info("User : " + map.get("userName") + map.get("password"));
    	if(map.get("userName") == null) {
    		map.put("userName", "guest");
    		map.put("password", "guest");
    		logger.info("User : " + map.get("userName") + map.get("password"));
    	}
    	LoginCredentials login = loginService.findByUserName(map.get("userName"));
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(),
                        login.getPassword()
                )
        );
        
        logger.info("User1: " + map.get("userName"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final LoginCredentials login1 = loginService.findByUserName(map.get("userName"));
        logger.info("USers:" + map.get("email") + "Pwd:" + map.get("password"));
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token,login1.getRole().toString(),login1.getUserId()));
    }

}
