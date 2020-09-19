package com.dxc.book;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.dxc.book.filter.JwtFilter;


/**
 * @author sgangal2
 *
 */
@SpringBootApplication
public class BookserviceApplication {

	/**
	 * @param args
	 * @return  FilterRegistrationBean
	 */
	
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter()
	{
		final FilterRegistrationBean<JwtFilter> registrationBean=new FilterRegistrationBean<JwtFilter>();
		JwtFilter filter=new JwtFilter();
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/api/v1/bookapp/*");
		return registrationBean;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}

}
