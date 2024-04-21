package com.namyang.nyorder.comm.service;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.comm.vo.BasicVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : BasicService.java
 * 작성자  : JungsuKim
 * 작성일  : 2022. 2. 9.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 9.    JungsuKim     최조 프로그램 작성
 *
 ****************************************************/
public interface BasicService  {
	
	<E> List<E> basicSelectList(BasicVO param);
	
	<T> Object basicSelectOne(BasicVO param);		
		
	int basicInsert(BasicVO param);
	
	int basicUpdate(BasicVO param);		

}
