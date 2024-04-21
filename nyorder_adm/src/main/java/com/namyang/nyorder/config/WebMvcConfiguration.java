package com.namyang.nyorder.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.namyang.nyorder.config.web.LoginInfoArgumentResolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@ComponentScan(basePackages = {"com.namyang.nyorder.*.controller"})
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
	
	private final LoginInfoArgumentResolver loginInfoArgumentResolver;
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {		
		return new CommonsMultipartResolver();
	}
	
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {    	
    	argumentResolvers.add(loginInfoArgumentResolver);    	
    }
    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SessionInfoInterceptor());
//        
//        /*
//        // 가로채는 경로 설정 가능
//        registry.addInterceptor(new SampleInterceptor())
//                .addPathPatterns("/*") // 모든 Path에 대해서 가로챌것이다.
//                // .addPathPatterns("/sample") // /sample경로에 대해서만 가로챌것이다.
//                .excludePathPatterns("/sample"); // /sample 경로에 대해서는 Interceptor 가로채지 않을것이다.
//        */
//    }
	
 
//    @Override
//    protected RequestMappingHandlerAdapter createRequestMappingHandlerAdapter() {
//        return new CustomRequestMappingHandlerAdapter();
//    }
    
	// default servlet 핸들러를 설정한다.
	// 원래 서블릿은 / (모든 요청)을 처리하는 default servlet을 제공한다. 
	// 스프링에서 설정한 path는 스프링이 처리하고, 스프링이 처리하지 못한 경로에 대한 처리는
	// default servlet에게 전달하여 처리하게 된다.
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();		
	}
	
	/**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

	// Spring MVC에서 jsp view 가 위치하는 경로를 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		registry.tiles();        
	}

    @Bean
    public ViewResolver tilesViewResolver() {
        return new TilesViewResolver();
    }
	

    //    '/' 로 요청이 오면 '/main'으로 리다이렉트 하도록 합니다.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/main");
	}

    //  /resources 경로에 있는 자료들을 /resources/**로 접근하게 합니다.
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
