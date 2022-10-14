package com.itwillbs.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
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

		// 회원가입  - memberJoin(DTO)
		/**
		 * 회원가입 해주는 메서드, 실행할 때 회원정보(DTO) 받아서 사용
		 * 리턴 x, 실행결과가 0보다 클 때 메세지 출력
		 * @param dto
		 */
		public void memberJoin(MemberDTO dto) {
			
			// 1.2. 디비연결
			try {
				con = getConnection();
				// 3. SQL 작성 & pstmt 객체
				sql = "insert into itwill_member(id,pw,name,birth,gender,email,addr,tel) values(?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				//???
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPw());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.getBirth());
				pstmt.setString(5, dto.getGender());
				pstmt.setString(6, dto.getEmail());
				pstmt.setString(7, dto.getAddr());
				pstmt.setString(8, dto.getTel());
				
				// 4. SQL 실행
				int result = pstmt.executeUpdate();
				
				if(result>0) {
					System.out.println(" DAO : 회원가입 성공!");
				} 
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
		}
		// 회원가입  - memberJoin(DTO)
		
		// 아이디 중복체크 - memberIdCheck(ID)
		public int memberIdCheck(String id) {
			int result = 0; // 0 - 중복x(아이디사용 o)	1 - 중복o(아이디 사용x)
			
			// 1.2. 디비연결
			try {
				con = getConnection();
				// 3. sql & pstmt
				sql = "select * from itwill_member where id=?";
				// 선택할 정보는 아무거나 상관 X 
				pstmt = con.prepareStatement(sql);
				
				// ???
				pstmt.setString(1, id);
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					// 회원이 있다(중복)
					result = 1;
					System.out.println(" DAO : 아이디 중복 ("+result+")");
				}
				// result == 0
				System.out.println(" DAO : 아이디 중복결과 ("+result+")");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return result;
		}
		// 아이디 중복체크 - memberIdCheck(ID)
		
		// 로그인 memberLogin(id,pw)
		public int memberLogin(String id, String pw) {
			int result = 0;
			
			try {
				con = getConnection();
				
				sql = "select * from itwill_member where id=? and pw=?";
				
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				

				rs = pstmt.executeQuery();
				
				MemberDTO dto = new MemberDTO();
				
				if(rs.next()) {

					if(pw.equals(rs.getString("pw"))) {
						result = 1;
						
					}else {
						result = 0; // 비밀번호 오류
					}
				}else {
					result = -1; // 로그인 정보 없음
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return result;
			
		}
		// 로그인 memberLogin(id,pw)
		
}
