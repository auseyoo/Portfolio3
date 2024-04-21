package com.namyang.nyorder.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.namyang.nyorder.config.YamlPropertySourceFactory;
import com.namyang.nyorder.config.error.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : ExcelUtil.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 18.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 18.        kjin        최조 프로그램 작성
 *
 */
@Slf4j
@Component
@PropertySource(value = "classpath:config/file-config.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class ExcelReadUtil {
	
	@Value(value = "${file.upload.maxSize}")
	private String maxSize;
	
	/**
	 * @Method Name : uploadExcel
	 * @작성일 : 2022. 3. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 파일을 읽어 원하는 headerInfo에 따른 값만 추출하여 List 반환
	 * @param excelFile
	 * @param headerInfo	map 에 담을 key 값 셋팅
	 * @param startRow		엑셀의 데이터 읽기 시작할 로우 번호 (1 부터 시작)
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> uploadExcel(MultipartFile excelFile, String[] headerInfo, int startRow) throws Exception {
		
		// null 체크
		if (excelFile.isEmpty())
		{
			throw new BusinessException("alert.prmtExcUpld01");
		}
		
		// 확장자 체크
		String extension = FilenameUtils.getExtension(excelFile.getOriginalFilename());
		if (!"XLSX".equals(extension.toUpperCase()) && !"XLS".equals(extension.toUpperCase())) {
			throw new BusinessException("alert.prmtExcUpld02");
		}
		
		log.debug("file Size :: " + excelFile.getSize() + "byte");
		// 용량 체크
		if (excelFile.getSize() > Long.parseLong(maxSize)) {
			throw new BusinessException("alert.prmtExcUpld03");
		}
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> data = null;
		
		// 워크북
		Workbook wb = null;
		
		// 행
		Row row = null;
		
		// 셀
		Cell cell = null;
		
		try {
			// 워크북 생성
			if("XLSX".equals(extension.toUpperCase()))
			{
				wb = new XSSFWorkbook(OPCPackage.open(excelFile.getInputStream()));
			}
			else if("XLS".equals(extension.toUpperCase()))
			{
				wb = new HSSFWorkbook(excelFile.getInputStream());
			}
			
			// 첫 번째 시트 불러오기
			Sheet sheet = wb.getSheetAt(0);
			
			// sheet에서 유효한(데이터가 있는) 행의 개수를 가져온다.
			int numOfRows = sheet.getPhysicalNumberOfRows();
			
			for(int rowIndex = startRow-1; rowIndex < numOfRows; rowIndex++)
			{
				row = sheet.getRow(rowIndex);
				if(row != null)
				{
					data = new HashMap<String, Object>();
					// 셀의 수
					int cells = row.getPhysicalNumberOfCells();
					for(int columnIndex = 0; columnIndex < cells; columnIndex++) {
						cell = row.getCell(columnIndex);
						if(cell == null){
							// 셀이 존재하지 않는 경우
							continue;
						} else {
							String value = getCellValueToString(cell);
							data.put(headerInfo[columnIndex], value);
						}
					}
					
					int valNullCnt = 0;
					if (StringUtil.isNotEmpty(data))
					{
						for (String key : data.keySet()) {
							if ("".equals(StringUtil.nvl(data.get(key))))
							{
								//log.debug( String.format("키 : %s, 값 : %s", key, data.get(key)) );
								valNullCnt++;
							}
						}
					}
					
					// row 의 실제값 null 인 갯수랑 headerInfo 랑 같지 않으면 list 에 담는다.
					if (valNullCnt != headerInfo.length)
					{
						// 한 행의 값을 list에 추가
						list.add(data);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				if(wb != null) { wb.close(); }
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * @Method Name : getCellValueToString
	 * @작성일 : 2022. 3. 18.
	 * @작성자 : kjin
	 * @Method 설명 : Cell의 값을 String 형태로 반환
	 * @param cell
	 * @return
	 */
	private String getCellValueToString(Cell cell){
		String value = "";
		switch(cell.getCellType()){
			case FORMULA:
				value = cell.getCellFormula();
				break;
			case NUMERIC:
				//value = cell.getNumericCellValue() + "";
				value = NumberToTextConverter.toText(cell.getNumericCellValue());
				break;
			case STRING:
				value = cell.getStringCellValue() + "";
				break;
			case BLANK:
				//value = cell.getBooleanCellValue() + "";
				value = "";
				break;
			case ERROR:
				value = cell.getErrorCellValue() + "";
				break;
			default:
				break;
		}
		
		return value;
	}
	
	
}
