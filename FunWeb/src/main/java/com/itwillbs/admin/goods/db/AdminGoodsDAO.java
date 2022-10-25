package com.itwillbs.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {
	
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
	
	// 상품등록메서드 - insertGoods(DTO) (C)
	public void insertGoods(GoodsDTO dto) {
		int gno = 0;
		
		try {
			// 1. 상품번호 계산
			con = getConnection();
			sql ="select max(gno) from itwill_goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				gno = rs.getInt(1) +1;
			}
			System.out.println(" DAO : gno : "+gno);
			
			// 2. 상품 등록
			sql="insert into itwill_goods(gno,category,name,content,size,color,amount,price,image,best) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, gno); // 계산해준 gno를 가져옴
			pstmt.setString(2, dto.getCategory());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getSize());
			pstmt.setString(6, dto.getColor());
			pstmt.setInt(7, dto.getAmount());
			pstmt.setInt(8, dto.getPrice());
			pstmt.setString(9, dto.getImage());
			pstmt.setInt(10, dto.getBest());
			
			pstmt.executeUpdate();
			System.out.println(" DAO : 상품 등록 완료! ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 상품등록메서드 - insertGoods(DTO)
	
	// 상품 리스트 - getadminGoodsList() (R)
	public List getadminGoodsList() {
		List adminGoodsList = new ArrayList();
		
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
				
				adminGoodsList.add(dto);
			} // while
			System.out.println(" DAO : 관리자 상품리스트 저장완료 ");
			System.out.println(" DAO : " + adminGoodsList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 메서드에서 가져다 사용하기 위해 return
		return adminGoodsList; 
	}
	// 상품 리스트 - getadminGoodsList()

	// 상품 1개의 정보를 가져오기 - getadminGoods(gno)
	public GoodsDTO getadminGoods(int gno) {
		GoodsDTO dto = null; // 초기화 되어있음  // return을 위해 try밖에 선언
//		GoodsDTO dto2; // 초기화 안되어 있음
//		System.out.println(dto.getAmount());  // Null pointer access: The variable dto can only be null at this location(런타임에러=exception(예외))
//		System.out.println(dto2.getAmount()); // The local variable dto2 may not have been initialized(컴파일에러)
//		GoodsDTO dto = new GoodsDTO();
		try {
			con = getConnection();
			sql = "select * from itwill_goods where gno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
				
			} // if
			
			System.out.println(" DAO : 상품정보 가져오기 완료 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	}
	// 상품 1개의 정보를 가져오기 - getadminGoods(gno)
	
	// 상품정보 수정메서드 - AdminModifyGoods(DTO)
	public void AdminModifyGoods(GoodsDTO dto) {
		
		try {
			con = getConnection();
			sql = "update itwill_goods set category=?,name=?,price=?,color=?,amount=?,size=?,content=?,best=? "
					+ "where gno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getCategory());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, dto.getColor());
			pstmt.setInt(5, dto.getAmount());
			pstmt.setString(6, dto.getSize());
			pstmt.setString(7, dto.getContent());
			pstmt.setInt(8, dto.getBest());
			pstmt.setInt(9, dto.getGno());
			
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 관리자 상품정보 수정");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 상품정보 수정메서드 - AdminModifyGoods(DTO)
	
	// 상품정보 삭제메서드 - adminRemoveGoods(gno)
	public void adminRemoveGoods(int gno) {
		
		try {
			con = getConnection();
			sql = "delete from itwill_goods where gno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			pstmt.executeUpdate();

			System.out.println(" DAO : 관리자 정보 삭제");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// 상품정보 삭제메서드 - adminRemoveGoods(dto)
	
}
