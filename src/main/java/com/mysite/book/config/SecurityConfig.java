package com.mysite.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.formLogin((formLogin) -> formLogin
				.loginPage("/member/login")
				
				// Spring Security 의 기본 설정 : ID 의 name = "usernanme"
				//                             password 필드의 name = "password" 
				.usernameParameter("email")
				//.passwordParameter("password")
				// MemberService에서 인증 처리를 하고 있음. 
				.failureUrl("/member/login/error")
				.defaultSuccessUrl("/")
				)
				.logout((logout) -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true));
			//.csrf().disable()
		
		
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return  new BCryptPasswordEncoder();
	}

}
