package com.namyang.nyorder.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class MyRoutingDataSource extends AbstractRoutingDataSource { 
	
	@Override 
	protected Object determineCurrentLookupKey() { 
		
		String serverType = System.getProperty("server.type");
		Object dbKey = serverType;
		
		//dbKey = "dev";
		
		return dbKey; 
	
	}
}
