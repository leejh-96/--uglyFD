package com.uglyfd.member.mypage_cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "mycartdelete", urlPatterns = { "/mycart/delete" })
public class mycartdeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public mycartdeleteServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				int no = Integer.parseInt(request.getParameter("no"));
				
				System.out.println("상품 번호 : " + no );
				
//				int result = new BoardService().delete(no);
				
//				if (result > 0) {
//					request.setAttribute("msg", "상품 삭제 성공");
//					request.setAttribute("location", "/mypage/mycart");
//				}else {
//					request.setAttribute("msg", "상품 삭제 실패");
//					request.setAttribute("location", "/mypage/mycart?no=" + no);
//					
//				}
//			
//				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
			

		
	}


