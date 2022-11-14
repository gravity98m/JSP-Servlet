package com.itwillbs.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;

public class BasketDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 디비 연결해주는 메서드(커넥션풀)
	private Connection getConnection() throws Exception {
		// 1. 드라이버 로드 // 2. 디비연결
		
		// Context 객체 생성 (JNDI API)
		Context initCTX = new InitialContext();
		// 디비연동정보 불러오기
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/funweb");
		// 디비정보(연결)불러오기
		con = ds.getConnection();
		
		System.out.println(" DAO : 디비연결 성공(커넥션풀)");
		System.out.println(" DAO : con : " +con);
		return con;
	}
		
	// 자원해제 메서드 - closeDB()
	public void closeDB() {
		System.out.println(" DAO : 디비연결자원 해제");
		
		try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 자원해제 메서드 - closeDB()
	
	// 기존의 장바구니 체크 - checkBasket(DTO) 
	public boolean checkBasket(BasketDTO dto) {
		boolean result = false;
		
		try {
			con = getConnection();
			sql = "select * from itwill_basket "
					+ "where b_m_id=? and b_g_num=? and b_g_size=? && b_g_color=?"; // 동일상품 정보 체크
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getB_m_id());
			pstmt.setInt(2, dto.getB_g_num());
			pstmt.setString(3, dto.getB_g_size());
			pstmt.setString(4, dto.getB_g_color());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 기존 동일상품 존재 -> 수량만 수정
				sql = "update itwill_basket set b_g_amount=b_g_amount+? "
						+ "where b_m_id=? and b_g_num=? and b_g_size=? and b_g_color=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getB_g_amount());
				pstmt.setString(2, dto.getB_m_id());
				pstmt.setInt(3, dto.getB_g_num());
				pstmt.setString(4, dto.getB_g_size());
				pstmt.setString(5, dto.getB_g_color());
				
				int tmp = pstmt.executeUpdate(); // 결과가 0 또는 1로 반환
				if(tmp == 1) {
					result = true;
				}
			}
			System.out.println(" DAO : "+(result? "기존의 정보 수정":"기존의 상품 없음"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 기존의 자바구니 체크 - checkBasket(DTO) 
	
	// 장바구니 상품 추가 - basketAdd(DTO)
	public void basketAdd(BasketDTO dto) {
		int b_num = 0;
		try {
			
			con = getConnection();
			// 1) 장바구니 번호 b_num
			sql="select max(b_num) from itwill_basket";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b_num = rs.getInt(1)+1; // 첫번째 인덱스를 가져와서 +1
			}
			// 2) 장바구니 저장 (insert)
			sql = "insert into itwill_basket(b_num,b_m_id,b_g_num,b_g_amount,b_g_size,b_g_color) "
					+ "value(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, b_num);
			pstmt.setString(2, dto.getB_m_id());
			pstmt.setInt(3, dto.getB_g_num());
			pstmt.setInt(4, dto.getB_g_amount());
			pstmt.setString(5, dto.getB_g_size());
			pstmt.setString(6, dto.getB_g_color());
			
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 장바구니 등록완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 장바구니 상품 추가 - basketAdd(DTO)
	
	// 장바구니 목록 조회 - getBasketList(ID) // arrayList와 Vector과 유사한 기능 수행
	public Vector getBasketList (String id) {
		Vector totalList = new Vector();
//		List basketList = new Vector(); 
		List basketList = new ArrayList();
		List goodsList = new ArrayList();
		
		try {
			con = getConnection();
			// sql - id 값에 해당하는 장바구니 정보 조회
			sql = "select * from itwill_basket where b_m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// 장바구니 정보 저장
				// DB -> DTO -> List
				BasketDTO bkDTO = new BasketDTO();
				
			   bkDTO.setB_date(rs.getTimestamp("b_date"));
			   bkDTO.setB_g_amount(rs.getInt("b_g_amount"));
			   bkDTO.setB_g_color(rs.getString("b_g_color"));
			   bkDTO.setB_g_num(rs.getInt("b_g_num"));
			   bkDTO.setB_g_size(rs.getString("b_g_size"));
			   bkDTO.setB_m_id(rs.getString("b_m_id"));
			   bkDTO.setB_num(rs.getInt("b_num"));
			   
			   basketList.add(bkDTO);
//			   System.out.println(" DAO : " + basketList);
					// 장바구니 상품에 해당하는 상품정보 조회
					// DB -> DTO -> List
			   		sql = "select * from itwill_goods where gno=?"; //값은 하나만 출력될 것임 
			   		PreparedStatement pstmt2 = con.prepareStatement(sql);
			   		pstmt2.setInt(1,bkDTO.getB_g_num());
			   		ResultSet rs2 = pstmt2.executeQuery();
			   		
			   		if(rs2.next()) {
			   			// 장바구니 상품정보를 찾음 -> 저장
			   			GoodsDTO gDTO = new GoodsDTO();
			   			
			   			gDTO.setName(rs2.getString("name"));
			   			gDTO.setPrice(rs2.getInt("price"));
			   			gDTO.setImage(rs2.getString("image"));
			   			gDTO.setGno(rs2.getInt("gno"));
			   			// ... 나머지 정보는 필요에 따라 추가
			   			
			   			// list 저장
			   			goodsList.add(gDTO);
			   		} // 상품정보 저장완료
			   		
			} // while
			//totalList 저장
			
			totalList.add(basketList);
			totalList.add(goodsList);
			
			System.out.println(" DAO : 장바구니 정보 + 상품정보 저장완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return totalList;
	}
	// 장바구니 목록 조회 - getBasketList(ID)
	
	// 장바구니 삭제 - deleteBasket(b_num)
	public int deleteBasket(int b_num) {
		int result = -1;
		
		try {
			con = getConnection();
			sql = "delete from itwill_basket where b_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			result = pstmt.executeUpdate();
			System.out.println(" DAO : " +b_num+"번 장바구니 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 장바구니 삭제 - deleteBasket(b_num)

	// 장바구니 삭제 - deleteBasket(id)
	public int deleteBasket(String id) {
		int result = -1;
		
		try {
			con = getConnection();
			sql = "delete from itwill_basket where b_m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			System.out.println(" DAO : "+id+"님 구매후 장바구니 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 장바구니 삭제 - deleteBasket(id)
	
}
