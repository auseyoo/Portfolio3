package com.namyang.nyorder.comm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.namyang.nyorder.comm.vo.AccountVO;


/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : LoginMapper.java
 * 작성자  : JungsuKim
 * 작성일  : 2022. 1. 7.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 1. 7.    JungsuKim     최조 프로그램 작성
 *
 ****************************************************/
@Mapper
public interface LoginMapper {
	
	int insertPassword(AccountVO searchVO) throws Exception;
	
	int updatePassword(AccountVO searchVO) throws Exception;
	
	AccountVO selectAccount(AccountVO param) throws Exception;
	
}
