package com.namyang.nyorder.cmmc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.namyang.nyorder.cmmc.vo.NttVO;



@Mapper
public interface RqbodMapper {

	/**
	 * @Method Name : selectRqbodList
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 요청 게시판 리스트 조회
	 * @param vO
	 * @return List<NttVO>
	 */
	List<NttVO> selectRqbodList(NttVO vO);

	/**
	 * @Method Name : updateCfmYn
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : GAIN
	 * @Method 설명 : 확인/미확인 업데이트
	 * @param vO
	 * @return int
	 */
	int updateCfmYn(NttVO vO);

	
	
}


