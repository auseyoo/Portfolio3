package com.namyang.nyorder.config.security.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.comm.dao.LoginMapper;
import com.namyang.nyorder.comm.vo.AccountVO;

@Service
public class AccountService implements CustomUserDetailsService{
	
	@Autowired
	LoginMapper loginMapper;
	
	private Logger logger = LoggerFactory.getLogger(AccountService.class);

	//@Override
	public UserDetails loadUserByUsername(String bizNo, String emplCd) throws UsernameNotFoundException {
		
		logger.info("## loadUserByUsername ## bizNo ::" + bizNo);
		AccountVO accountVO = new AccountVO();
		AccountVO resultVO = new AccountVO();
		
		//accountVO.setBizNo(bizNo);
		//accountVO.setEmplCd(emplCd);
		
		try {
			resultVO = loginMapper.selectAccount(accountVO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( resultVO == null ) {
			logger.debug("## ");
			throw new UsernameNotFoundException(bizNo + emplCd);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();            
		authorities.add(new SimpleGrantedAuthority(resultVO.getRolecode()));

		System.out.println("id : " + resultVO.getUsername());
		
		resultVO.setAccountNonExpired(true);
		resultVO.setAccountNonLocked(true);
		resultVO.setCredentialsNonExpired(true);
		
		System.out.println("enabled : " + resultVO.isEnabled());
		
		return resultVO;
	}

	@Override
	public UserDetails loadUserByUsername(String admCD) throws UsernameNotFoundException {
		logger.info("## loadUserByUsername ## admCD ::" + admCD);		
		AccountVO resultVO = new AccountVO();
		AccountVO accountVO = new AccountVO();
		
		accountVO.setAdmCd(admCD);
		
		try {
			resultVO = loginMapper.selectAccount(accountVO);
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();            
			authorities.add(new SimpleGrantedAuthority(resultVO.getMenuAdmType()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( resultVO == null ) {			
			throw new UsernameNotFoundException(admCD);
		}
		
		System.out.println("**************Found user***************");
		System.out.println("id : " + resultVO.getAdmCd());
		
		resultVO.setAccountNonExpired(true);
		resultVO.setAccountNonLocked(true);
		resultVO.setCredentialsNonExpired(true);
		
		return resultVO;
	}

}
