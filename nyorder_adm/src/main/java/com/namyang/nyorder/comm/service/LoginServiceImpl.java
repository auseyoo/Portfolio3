package com.namyang.nyorder.comm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.comm.dao.LoginMapper;
import com.namyang.nyorder.comm.vo.AccountVO;
import com.namyang.nyorder.comm.vo.CommVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.config.YamlPropertySourceFactory;

@Service
@PropertySource(value = "classpath:config/database.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class LoginServiceImpl  implements LoginService{
	
	
	@Autowired
    private LoginMapper loginMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Value(value = "${salt.string}")
	private String saltString;
	
	
	public int savePassword(AccountVO loginVO) throws Exception{
		
		int cnt = 0;
		String newPass = "";
		
		//TransPassword transPassword = new TransPassword();
		//newPass = transPassword.encrypt(loginVO.getPassword(), saltString);
		
		newPass = passwordEncoder.encode(loginVO.getPassword());
		
		loginVO.setHexPassword(newPass);
		
		cnt = loginMapper.updatePassword(loginVO);
		
		cnt = cnt + loginMapper.insertPassword(loginVO);
		
		return cnt;
	}
	
	public void setSessionInfo(AccountVO account) {
		
		userInfo.setAdmCd(account.getAdmCd());
		userInfo.setAdmSeq(account.getAdmSeq());
		userInfo.setAdmNm(account.getAdmNm());
		
		userInfo.setBhfcCd(account.getBhfcCd());
		userInfo.setBhfcNm(account.getBhfcNm());
		userInfo.setBsnGrpCd(account.getBsnGrpCd());
		userInfo.setGrpCd(account.getGrpCd());
		userInfo.setMenuAduType(account.getMenuAdmType());
		
	}

}
