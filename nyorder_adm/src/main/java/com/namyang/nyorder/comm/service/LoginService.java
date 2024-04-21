package com.namyang.nyorder.comm.service;

import java.util.List;

import com.namyang.nyorder.comm.vo.AccountVO;

public interface LoginService {
	
	int savePassword(AccountVO searchVO) throws Exception;
	void setSessionInfo(AccountVO account);
	
}
