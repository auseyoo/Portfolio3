package com.namyang.nyorder.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.namyang.nyorder.cmmc.vo.FileVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 파일 다운로드 
 * 파일명  : DownloadFileView.java
 * 작성자  : YESOL
 * 작성일  : 2022. 3. 10.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일			 변경자		   변경내역
 * --------------------------------------------------
 * 2022. 3. 10.	YESOL	 최조 프로그램 작성
 *
 ****************************************************/
public class DownloadFileView extends AbstractView {
	
	private String path;
	
	public DownloadFileView(String path) {
		this.path = path;
		setContentType("application/download; charset=utf-8");
	}

	/**
	 *	file download
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/**
		 * 1. Files 객체 read
		 */
		FileVO fileVO = (FileVO) model.get("file");

		if(fileVO == null) {
			throw new Exception("파일경로를 찾을 수 없습니다.");
		}
		
		/**
		 * 2. File 경로를 이용하여 다운로드 파일생성
		 */
		logger.debug("====>>"+ path + fileVO.getFileLc()+fileVO.getFileNm());
		File file = new File(path + fileVO.getFileLc()+fileVO.getFileNm());
		
		/**
		 * 3. mimeType 처리 
		 * - 열리지 말아야 할 타입이 브라우저에서 열리는 것을 방지
		 */
		Tika tika = new Tika();
		String mimeType = tika.detect(file);
		
		/**
		 * 4. header 설정
		 */
		OutputStream out = response.getOutputStream();
		FileInputStream in = null;
		String fileName = null;
		if(request.getHeader("User-Agent").indexOf("MSIE") > -1){
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		}else {
			fileName = new String(file.getName().getBytes("utf-8"));
		}// end if;
		
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition",	"attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentType(mimeType);

		/**
		 * 5. file download
		 */
		try {
			in = new FileInputStream(file);
			FileCopyUtils.copy(in, out);
			logger.debug("====>> downLoad");
		} catch (Exception e) {
			logger.debug("====>> downLoad err");
			throw e;
		} finally {
			if (in != null) out.close();
		}
		out.flush();
		logger.debug("====>> downLoad 내보내기");
	}
	


}
