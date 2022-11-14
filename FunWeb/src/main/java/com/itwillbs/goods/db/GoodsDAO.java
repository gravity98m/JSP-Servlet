package com.itwillbs.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.basket.db.BasketDTO;

public class GoodsDAO {
	
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
	
	// 상품 리스트 - getGoodsList() (R)
	public List getGoodsList() {
		List goodsList = new ArrayList();
		
		try {
			// 1.2. 디비연결
			con = getConnection();
			// 3. sql 작성 & pstmt 객체
			sql = "select * from itwill_goods";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while(rs.next()) {
				// DB(테이블) -> DTO -> List
				GoodsDTO dto = new GoodsDTO();
				
				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setColor(rs.getString("color"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setGno(rs.getInt("gno"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));
				
				goodsList.add(dto);
				
			} // while
			System.out.println(" DAO : 관리자 상품리스트 저장완료 ");
			System.out.println(" DAO : " + goodsList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 메서드에서 가져다 사용하기 위해 return
		return goodsList; 
	}
	// 상품 리스트 - getGoodsList()
	
	// 상품 리스트 - getGoodsList(item) (R)
	public List getGoodsList(String item) {
		List goodsList = new ArrayList();
		
		StringBuffer SQL = new StringBuffer();
		
		
		try {
			// 1.2. 디비연결
			con = getConnection();
			// 3. sql 작성 & pstmt 객체
			//sql = "select * from itwill_goods category=item";
			SQL.append("select * from itwill_goods");
			
			if(item.equals("all")) {	
				System.out.println(" DAO : all "+SQL);
			}
			else if(item.equals("best")) {
				SQL.append(" where best = ?");
				System.out.println(" DAO : best "+SQL);
			}
			else {
				SQL.append(" where category = ?");
				System.out.println(" DAO : category "+SQL);
			}
			
//			pstmt = con.prepareStatement(SQL.toString()); // String으로 변환
			pstmt = con.prepareStatement(SQL+"");
			
			if(item.equals("all")) {
			}else if(item.equals("best")) {
				pstmt.setInt(1, 1);
			}else {
				pstmt.setString(1, item);
			}
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while(rs.next()) {
				// DB(테이블) -> DTO -> List
				GoodsDTO dto = new GoodsDTO();
				
				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setColor(rs.getString("color"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setGno(rs.getInt("gno"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));
				
				goodsList.add(dto);
				
			} // while
			System.out.println(" DAO : 관리자 상품리스트 저장완료 ");
			System.out.println(" DAO : " + goodsList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 메서드에서 가져다 사용하기 위해 return
		return goodsList; 
	}
	// 상품 리스트 - getGoodsList(item)
	
	// 상품 상세정보 조회 - getGoods(gno)
	public GoodsDTO getGoods(int gno) {
		GoodsDTO dto = null;
		
		try {
			con = getConnection();
			sql = "select * from itwill_goods where gno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// DB -> DTO
				dto = new GoodsDTO();
				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setColor(rs.getString("color"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setGno(rs.getInt("gno"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));
			}	// if
			System.out.println(" DAO : 상품조회 완료");
			System.out.println(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	}
	// 상품 상세정보 조회 - getGoods(gno)

	// 구매후 상품 수량변경 - updateAmount()
	public void updateAmount(List basketList) {
		
		try {
			con = getConnection();
			for(int i=0;i<basketList.size();i++) {
				BasketDTO bkDTO = (BasketDTO)basketList.get(i);
				
				sql = "update itwill_goods set amount=amount-? where gno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,bkDTO.getB_g_amount());
				pstmt.setInt(2, bkDTO.getB_g_num());
				pstmt.executeUpdate();
			} // for
			System.out.println(" DAO : 구매후 수량 변경 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
	}
	// 구매후 상품 수량변경 - updateAmount()
	
}
