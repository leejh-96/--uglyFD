package com.uglyfd.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// 모든 요청에 대해 필터를 적용
@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class EncodingFilter implements Filter{

	public EncodingFilter() {
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		System.out.println("인코딩 필터가 생성되어 초기화 진행");
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("서블릿 동작 전 코드 실행");
		
		String requestMethod = ((HttpServletRequest)request).getMethod();

		//post 요청에 대해서만 적용이 되도록 조건문을 통해 적용.
		if (requestMethod.equals("POST")) {
		
			request.setCharacterEncoding("UTF-8");
		
			System.out.println(request.getCharacterEncoding()+"인코딩 완료");
		}
		
		chain.doFilter(request, response);

		System.out.println("서블릿 동작 후 코드 실행");
		
	}

	@Override
	public void destroy() {

		System.out.println("인코딩 필터가 소멸됨");
	
	}
}
