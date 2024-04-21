package com.namyang.nyorder.cmmc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.cmmc.dao.NotibodMapper;
import com.namyang.nyorder.cmmc.vo.NttVO;
import com.namyang.nyorder.config.YamlPropertySourceFactory;

import lombok.extern.slf4j.Slf4j;



/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 커뮤니케이션관리 - 공지사항
 * 파일명  : Notice_boardServiceImpl.java
 * 작성자  : GAIN
 * 작성일  : 2022. 2. 10.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 10.    GAIN     최조 프로그램 작성
 *
 ****************************************************/
@Slf4j
@Service
@PropertySource(value = "classpath:config/file-config.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class NotibodServiceImpl implements NotibodService{

	@Autowired
	private NotibodMapper notibodMapper;
	
	@Value(value = "${file.upload.maxSize}")
	private String maxSize;
	
	@Value(value = "${file.upload.path}")
	private String path;
	
	/* 공지사항 게시판 리스트 조회 */
	public List<NttVO> selectNotiList(NttVO param) {
		return notibodMapper.selectNotiList(param);
	};
	/**
	 * 글 논리삭제
	 */
	@Transactional
	public String updateNotiYn(NttVO vo) throws Exception{
		notibodMapper.updateNotiYn(vo);
		return "수정되었습니다.";
	}
	/**
	 * 공지사항 글 입력
	 */
	@Transactional
	public Map<String, Object> insertNotibod(NttVO vo) throws Exception{
		Map<String, Object> result 	= new HashMap<String, Object>();
		result.put("res", true);
		result.put("updateCnt", notibodMapper.insertNotibod(vo));
		return result;
	}
	/**
	 * 공지사항 글 수정
	 */
	@Transactional
	public Map<String, Object> updateNotibod(NttVO vo) throws Exception{
		Map<String, Object> result 	= new HashMap<String, Object>();
		result.put("res", true);
		result.put("updateCnt", notibodMapper.updateNotibod(vo));
		return result;
	}
	@Override
	public ModelAndView downloadAttachment(NttVO param) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean fileDownLoad(HttpServletRequest request, HttpServletResponse response) {
        
        //(1) 기본 ajax요청 시 응답
        String filenameReal = request.getParameter("FileName");
        if (filenameReal == null || filenameReal.equals("")) {
            return false;
        }
        
        //(2) 요청파일 정보 불러오기
        //File fileInfo = new File();
        //fileInfo.setFilenameReal(filenameReal);
       // fileInfo = dao.selectFileInfo(fileInfo);
        
        //(3) ContentType설정
        //response.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       // response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filenameReal,"UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        //(4) 파일읽어 응답하기
        //byte[] fileByte = FileUtils.readFileToByteArray(new File(uploadPath + "/" +fileInfo.getGroupId() + "/" +  fileInfo.getFilenameServer()));
      // response.getOutputStream().write(fileByte);
        //response.getOutputStream().flush();
        //response.getOutputStream().close();
        
        return true;
    }
	
}
