package com.uglyfd.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uglyfd.member.model.service.MemberService;

@WebServlet("/member/idCheck")
public class IdCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IdCheckServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Boolean> map = new HashMap<>();
        String userId = request.getParameter("userId");

        map.put("duplicate", new MemberService().isDuplicateId(userId));

        response.setContentType("application/json;charset=UTF-8");

        new Gson().toJson(map, response.getWriter());
    }
}
