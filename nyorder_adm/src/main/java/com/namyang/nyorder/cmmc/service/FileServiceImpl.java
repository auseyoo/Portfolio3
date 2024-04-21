package com.namyang.nyorder.cmmc.service;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.namyang.nyorder.cmmc.dao.FileMapper;
import com.namyang.nyorder.cmmc.vo.FileVO;
import com.namyang.nyorder.config.YamlPropertySourceFactory;
import com.namyang.nyorder.config.error.exception.BusinessException;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : File Service Implement
 * 파일명  : FileServiceImpl.java
 * 작성자  : YESOL
 * 작성일  : 2022. 3. 8.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 8.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Service
@PropertySource(value = "classpath:config/file-config.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class FileServiceImpl implements FileService {

	@Value(value = "${file.upload.maxSize}")
	private String maxSize;

	@Value(value = "${file.upload.path}")
	private String path;
	
	@Autowired
	private FileMapper fileMapper;
	
	/**
	 * 파일 업로드 
	 */
	@Transactional
	public int upload(MultipartFile file, FileVO fileVO) throws Exception {
		Calendar	nowCalendar = Calendar.getInstance();

		String yearMonth = Integer.toString(nowCalendar.get(Calendar.YEAR)) 
				+ StringUtils.leftPad(Integer.toString(nowCalendar.get(Calendar.MONTH)+1), 2, "0"); // 190012 
		
			
		// 2. 용량 체크
		if (file.getSize() > Long.parseLong(maxSize)) {
			throw new BusinessException("alert.notibod03");
		}
			
		// 3. 파일 업로드 DB 설정
		UUID uuid = UUID.randomUUID();
		String fileExt = StringUtils.lowerCase(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length()));
		String srvFileNm = uuid.toString()+"."+fileExt;
		fileVO.setFileNm(srvFileNm);
		fileVO.setFileOriNm(file.getOriginalFilename());
		fileVO.setFileExt(fileExt);
		fileVO.setFileSize(file.getSize());
		fileVO.setFileLc("/"+yearMonth+"/");
		fileVO.setUseYn("Y");
		
		File f = new File(path +fileVO.getFileLc()+ fileVO.getFileNm());
		if(!f.exists()) {
			f.mkdir();	// 디렉토리 생성
		}
		file.transferTo(f); // 파일 생성
		
		return fileMapper.addNttAtclDtl(fileVO);
	}

	/**
	 * 파일 목록 조회
	 */
	public List<FileVO> selectAtclList(FileVO param) {
		return fileMapper.selectAtclList(param);
	}
	
	/**
	 * 파일 단건 조회
	 */
	public FileVO selectAtclDtlList(FileVO param) {
		return fileMapper.selectAtclDtlList(param);
	}
	
	/**
	 * 파일 그룹 등록
	 */
	@Transactional
	public Integer addNttAtclMst ( FileVO param) {
		return fileMapper.addNttAtclMst(param);
	}

	/**
	 * 파일 단건 삭제
	 */
	@Transactional
	public String rmvAtclDtl(FileVO param) {
		 fileMapper.rmvAtclDtl(param);
		return "삭제되었습니다.";
	}

}
