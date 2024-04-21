package com.namyang.nyorder.config;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
        basePackages = "com.namyang.nyorder",
        useDefaultFilters = false,
//        excludeFilters = {
//                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = Controller.class),
//                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = RestController.class)
//        },
        includeFilters = {
                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = Configuration.class),
                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = Service.class),
                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = Repository.class),
                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = Mapper.class),
                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = Component.class),
                @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = Bean.class)
                
        }
)

public class ApplicationConfig {

}
