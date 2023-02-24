package com.uglyfd.member.mypage_cart.mypage_cart_service;

import static com.uglyfd.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;

import com.uglyfd.member.mypage_cart.mypage_Cart_dao.CartDao;
import com.uglyfd.member.mypage_cart.vo.Cart;

public class CartService {

	public Cart insertCart(int productNum, int loginMemberNum) {
		
		Cart cart = null;
		
		Connection connection = getConnection();
		
		cart = new CartDao().insertCart(connection,productNum,loginMemberNum);
		
		
		
		
		
		
		
		
		
		return null;
	}


}
