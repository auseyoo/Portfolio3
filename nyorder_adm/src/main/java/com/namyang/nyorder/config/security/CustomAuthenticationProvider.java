package com.namyang.nyorder.config.security;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.namyang.nyorder.comm.vo.AccountVO;
import com.namyang.nyorder.config.security.service.CustomUserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // 검쯩을 위한 구현
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        
        //AccountVO acount = (AccountVO)authentication.getPrincipal();
        
        //System.out.println("## authenticate ## acount ::" + acount.getBizNo() + ", " + acount.getEmplCd());

        System.out.println("## authenticate ## username ::" + username + ", " + password);	

        AccountVO account = (AccountVO) customUserDetailsService.loadUserByUsername(username);

        // password 일치하지 않으면!
        if(!passwordEncoder.matches(password, account.getPassword())){
            throw new BadCredentialsException("BadCredentialsException");
        }

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());

        return authenticationToken;
    }

    // 토큰 타입과 일치할 때 인증
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}


