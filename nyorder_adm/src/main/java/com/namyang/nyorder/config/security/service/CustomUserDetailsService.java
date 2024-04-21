package com.namyang.nyorder.config.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService{
	
	UserDetails loadUserByUsername(String bizNo, String emplCd) throws UsernameNotFoundException;
	

}
