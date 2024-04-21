package com.namyang.nyorder.cmmc.service;

import java.util.Map;

import com.namyang.nyorder.cmmc.vo.NttVO;

public interface RqbodService {

	/**
	 * @Method Name : selectRqbodList
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 요청게시판 리스트 조회
	 * @param vO
	 * @return Map<String,Object>
	 */
	public Map<String, Object> selectRqbodList(NttVO vO);

	/**
	 * @Method Name : updateCfmYn
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : GAIN
	 * @Method 설명 : 확인/미확인 업데이트
	 * @param vO
	 * @return String
	 */
	public String updateCfmYn(NttVO vO);

	

	

}
