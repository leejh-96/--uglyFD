package com.uglyfd.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "cuscenter", urlPatterns = { "/board/cuscenter" })
public class CusCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CusCenterServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/board/cuscenter.jsp").forward(request, response);
	}

}
