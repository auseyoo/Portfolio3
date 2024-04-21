package com.namyang.nyorder.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : JsonUtils.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 23.
 *
 * 설 명  : Json 유틸
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 23.        kjin        최조 프로그램 작성
 *
 */
@Slf4j
public class JsonUtils {
	
	public static final ObjectMapper MAPPER = new ObjectMapper();
	
	public static <T> T parse(JsonNode json, Class<T> clazz) {
		T result = null;
		result = MAPPER.convertValue(json, clazz);
		return result;
	}
	
	public static String objectToString(Object value) {
		String result = null;
		try {
			result = MAPPER.writeValueAsString(value);
		} catch (final JsonProcessingException e) {
			log.error("JsonProcessingException :: " + e.getMessage().replaceAll("[\r\n]",""));
		}
		return result;
	}
	
	public static JsonNode objectToJsonNode(Object obj) {
		return MAPPER.valueToTree(obj);
	}
	
	public static <T> T fromJson(String json, Class<T> clazz) {
		
		try {
			return MAPPER.readValue(json, clazz);
		} catch (final JsonParseException e) {
			log.error("JsonParseException :: " + e.getMessage().replaceAll("[\r\n]",""));
		} catch (final JsonMappingException e) {
			log.error("JsonMappingException :: " + e.getMessage().replaceAll("[\r\n]",""));
		} catch (final IOException e) {
			log.error("IOException :: " + e.getMessage().replaceAll("[\r\n]",""));
		}
		
		return null;
	}
	
	public static JsonNode getFindValue(String searchKey, JsonNode sourceNode) throws Exception {
		final String findkey = "/" + searchKey.replaceAll("\\.", "\\/");
		final JsonNode soureJsonNodeTemp = sourceNode.at(findkey);
		return soureJsonNodeTemp;
	}
	
	public static boolean compareSourceSearchKey(String searchKey, JsonNode value, JsonNode soureJsonNode) throws Exception {
		boolean rn = false;
		final String findkey = "/" + searchKey.replaceAll("\\.", "\\/");
		final JsonNode soureJsonNodeTemp = soureJsonNode.at(findkey);
		if (soureJsonNodeTemp.asText().equals(value.asText())) {
			rn = true;
		}
		return rn;
	}
	
	public static boolean isValidJson(String jsonInString) {
		boolean isValid = false;
		try {
			MAPPER.readTree(jsonInString);
			isValid = true;
		} catch (final IOException e) {
			isValid = false;
		}
	    return isValid;
	}
	
}
