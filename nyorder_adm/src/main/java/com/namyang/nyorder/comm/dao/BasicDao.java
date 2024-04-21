package com.namyang.nyorder.comm.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namyang.nyorder.comm.vo.BasicVO;

@Repository
public class BasicDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public <E> List<E> basicSelectList(BasicVO param){		
		return sqlSessionTemplate.selectList(param.getQueryId(), param.getParamClass());
	}
	
	public <T> Object basicSelectOne(BasicVO param){		
		return sqlSessionTemplate.selectOne(param.getQueryId(), param.getParamClass());
	}
	
	public int basicInsert(BasicVO param){		
		return sqlSessionTemplate.insert(param.getQueryId(), param.getParamClass());
	}
	
	public int basicUpdate(BasicVO param){		
		return sqlSessionTemplate.update(param.getQueryId(), param.getParamClass());
	}

}
