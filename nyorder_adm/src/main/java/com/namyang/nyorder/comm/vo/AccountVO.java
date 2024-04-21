package com.namyang.nyorder.comm.vo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountVO implements UserDetails{

	private String id;	
	private String admCd;
	private String password;
	private String rolecode;
	private int failCnt;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean enabled;
	
	private String admSeq;
	private String bsnGrpCd;
	private String bhfcCd;
	private String bhfcNm;	
	private String menuAdmType;
	private String grpCd;
	private String admNm;

	private String hexPassword;
	private String tmpPwdYn;
	private String dtmDiff;
	
	private List<MenuVO> menuVOList;
	
	private List<MenuVO> bkmkMenuList;
	
	private Collection <? extends GrantedAuthority> authorities;
	
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

}
