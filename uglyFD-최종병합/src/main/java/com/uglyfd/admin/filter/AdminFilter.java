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
import javax.servlet.http.HttpSession;

import com.uglyfd.member.model.vo.Member;

@WebFilter(filterName = "admin", urlPatterns = {"/admin/member/page","/admin/member","/admin/member/update","/product/delete","/productinout","/product/register","/productStock","/productstockupdate","/product/update"})
public class AdminFilter implements Filter {

    public AdminFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		if(loginMember == null || loginMember.getGrade() != 1) {
			request.setAttribute("msg","잘못된 경로로 접근하셨습니다.");
			request.setAttribute("location","/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

			return;
		}
		
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
