package com.namyang.nyorder.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/* Spring Security를 사용하려면 AbstractSecurityWebApplicationInitializer를 
 * 상속받는 클래스를 반드시 작성해야 한다. 이 클래스가 있을 경우 
 * Spring Security가 제공하는 필터들을 사용할 수 있도록 활성화 해준다.
 */

// AbstractSecurityWebApplicationInitializer를 상속받는 클래스를 작성해야 스프링 시큐리티 필터들이 활성화된다.
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
