package com.namyang.nyorder.comm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.comm.dao.BasicDao;
import com.namyang.nyorder.comm.vo.BasicVO;

@Service
public class BasicServiceImpl implements BasicService{
	
	@Autowired
	private BasicDao basicDao;
	
	public <E> List<E> basicSelectList(BasicVO param){
		return basicDao.basicSelectList(param);
	}
	
	public <T> Object basicSelectOne(BasicVO param){		
		return basicDao.basicSelectOne(param);
	}
	
	public int basicInsert(BasicVO param){		
		return basicDao.basicInsert(param);
	}
	
	public int basicUpdate(BasicVO param){		
		return basicDao.basicUpdate(param);
	}


}
