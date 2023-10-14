package com.ssafy.newjibs.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http
		// 	.authorizeRequests()
		// 	.antMatchers("/admin/**").hasRole("ADMIN") // ADMIN 권한이 필요한 페이지
		// 	.antMatchers("/user/**").hasRole("USER")   // USER 권한이 필요한 페이지
		// 	.anyRequest().authenticated()
		// 	.and()
		// 	.formLogin()
		// 	.loginPage("/login")  // 로그인 페이지 경로
		// 	.defaultSuccessUrl("/") // 로그인 성공 후 이동할 경로
		// 	.permitAll()
		// 	.and()
		// 	.logout()
		// 	.logoutUrl("/logout") // 로그아웃 URL
		// 	.permitAll();
		http
			.authorizeRequests()
			.anyRequest().permitAll() // 모든 요청을 허용
			.and()
			.csrf().disable() // CSRF 비활성화
			.httpBasic().disable(); // HTTP Basic Authentication 비활성화
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("{noop}admin123").roles("ADMIN")
			.and()
			.withUser("user").password("{noop}user123").roles("USER");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
