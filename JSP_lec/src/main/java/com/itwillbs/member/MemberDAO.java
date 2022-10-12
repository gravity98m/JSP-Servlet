package com.itwillbs.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
		// DAO(Date Access Object) : 데이터(DB) 처리객체
		// => DB에 관한 모든 동작을 수행
		
		// 공통으로 사용할 변수 선언
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = null;
		
		public MemberDAO() {
			System.out.println(" DAO : 생성자 호출(객체 생성)");
			System.out.println(" DAO : DB연결에 대한 모든 처리 준비 완료");
		}
		
		// 디비 연결해주는 매서드
		private Connection getConnection() throws Exception { // private매서드는 현재 class(MemberDAO)내에서만 사용가능. 개인정보를 외부에서 접근 제한하기 위해
			// 디비연결정보 (상수)
			final String DRIVER ="com.mysql.cj.jdbc.Driver";
			final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
			final String DBID = "root";
			final String DBPW = "1234";
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			// 2. 디비 연결
			con =  DriverManager.getConnection(DBURL,DBID,DBPW);
			System.out.println(" DAO : 디비연결 성공");
			System.out.println(" DAO : " +con);
			return con;
		}
	
		// 정보 조회 매서드() 
		/**
		 *  JavaDoc 주석 : 문법에 대한 설명을 작성하는 주석문
		 *  
		 *  이 메서드는 로그인정보(id)를 입력받아서
		 *  id에 해당하는 정보 모두를 조회 후 MemberBean 객체에 담아서
		 *  리턴하는 메서드 입니다. 
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public MemberBean getMember(String id) throws Exception {	 // 리턴타입이 MemberBean임. 여기서 id변수를 사용하기위해 값을 받아옴.
			// DB에서 정보 조회(select) -> MemberBean 객체 만들기
			// DB에서 필요한 정보 가져오기
			// 디비연결정보 (상수)
			 final String DRIVER ="com.mysql.cj.jdbc.Driver";
			 final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
			 final String DBID = "root";
			 final String DBPW = "1234";
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			// 2. 디비 연결
			Connection con =  DriverManager.getConnection(DBURL,DBID,DBPW);
			// 3. SQL 작성(select) & pstmt 객체
			String sql = "select * from itwill_member where id =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1,id); // 변수에 커서를 두고 f3 또는 ctrl누른상태에서 해당 변수(id)를 누르면 무엇과 연관되는지 알려줌
			
			// 4. SQL 실행
			ResultSet rs = pstmt.executeQuery(); // 여기는 객체가 생성되었음. 'new'는 수동적으로 생성하는 것임. 
												 //	어디론가부터 객체정보를 받아온다면 객체가 생성된다는 것이다.
												 
			// MemberBean mb = new MemberBean();  -> new를 통해 객체를 직접 생성.
			MemberBean mb = null; // 객체가 만들어졌다는 것은 new를 해야한다는 것. 객체를 저장하는 변수만 생겼다는 의미
			// 5. 데이터 실행
			if(rs.next()){
				// 회원정보 저장(MemberBean)
				// rs(DB정보) -> MemberBean
				mb = new MemberBean();    // 시스템의 효율(메모리)를 고려해서, '필요할 때' 객체를 생성하기 위해 밖에 변수를 선언하고, 지역변수를 만든다.
				
				mb.setAge(rs.getInt("age"));
				mb.setEmail(rs.getString("email"));
				mb.setGender(rs.getString("gender"));
				mb.setId(rs.getString("id"));
				mb.setName(rs.getString("name"));
				mb.setPw(rs.getString("pw"));
				mb.setRegdate(rs.getTimestamp("regdate"));
				
			}
			

			// MemberBean 객체 생성완료
			System.out.println("정보 조회 완료! mb 리턴! ");
			
			return mb;
		}
		// 정보 조회 메서드() 
		
		// 정보 수정 동작()
		public int updateMember(MemberBean umb){
			// [1] - 정상수정, [0] - 비밀번호 오류, [-1] - 회원정보 없음
			int result = -1; // 초기화. 잘못된 상황을 default로 둠. 그냥 넘어가지 않도록
			
			
			try {
				// 1. 드라이버 로드
				// 2. 디비연결
				con = getConnection(); // 연결정보가 con에 저장되었다는 것을 한 번더 확인하기 위해 con변수에 한 번 더 저장함
				// 3. SQL 작성(select) & pstmt 객체
				// 본인여부 체크
				sql = "select pw from itwill_member where id =?";
				pstmt = con.prepareStatement(sql);
						
				
				//???
				pstmt.setString(1, umb.getId()); // id에 대한 정보는 umb에 저장되어있으므로
				
				// 4. SQL 실행
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					// 비밀번호가 있다.(회원)
					if(umb.getPw().equals(rs.getString("pw"))){
						// 	본인 -> 정보 수정
						
						// 3. SQL 작성(update-이름, 나이, 성별을 수정하는 구문)
						sql = "update itwill_member set name=?, age=?, gender=? where id=?";
						pstmt = con.prepareStatement(sql); // 쿼리하나에 sql + pstmt는 세트임. 항상 같이 취급할 것
						
						pstmt.setString(1, umb.getName());
						pstmt.setInt(2, umb.getAge());
						pstmt.setString(3, umb.getGender());
						pstmt.setString(4, umb.getId());
						
												
						// 4. SQL 실행		
						pstmt.executeUpdate();
						
						result = 1;
					}else {
						// 비밀번호 오류
						result = 0;					}
				}else {
					// 비밀번호가 없다(비회원) -> 테이블을 설계할 때 pw를 NN을 취급해줬기 때문
					// return -1;
					result = -1;
				}
				
				System.out.println(" DAO : 정보 수정완료 (" +result+ ")");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		// 정보 수정 동작()
		
		// 정보 삭제() - MemberDelete()
		public int MemberDelete (String id, String pw) {
			int result = -1;
			
			try {
				// 1. 드라이버 로드
				// 2. 디비연결
				con = getConnection();
				// 3. SQL 작성 & pstmt 객체 생성
				sql = "select pw from itwill_member where id =?";
				pstmt = con.prepareStatement(sql);
				
				//???
				pstmt.setString(1, id);
				
				// 4. SQL실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					if(pw.equals(rs.getString("pw"))) {
						// 3. SQL 작성 & pstmt 객체 생성
						sql = "delete from itwill_member where id=?";
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, id);
						// 4. SQL실행
						result = pstmt.executeUpdate();
						// => executeUpdate() : sql구문(insert, update, delete)
						// 실행했을 때 테이블에 영향을 준 row수를 리턴
						// result = 1;
					}else {
						result = 0;
					}
				}else {
					result = -1;
				}
				System.out.println(" DAO : 회원정보 삭제 완료("+result+")");
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
		// 정보 삭제() - MemberDelete()
	
		// 회원정보 조회(all) - memberList()
		public ArrayList memberList(){
			// 회원정보 저장 배열(가변 길이)
			ArrayList mList = new ArrayList();
			
			try {
				// 1.2. 디비연결
				con = getConnection();
				// 3. sql 작성 & pstmt 객체
				//sql = "select * from itwill_member";\
				// 관리자(admin) 정보를 제외한 정보 조회
				sql = "select * from itwill_member where id != ?";
				pstmt = con.prepareStatement(sql);
				//???
				pstmt.setString(1, "admin");
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				while(rs.next()) {
					// 데이터 있을 때 마다
					// DB(테이블)정보 -> MemberBean -> ArrayList
					
					// DB(테이블)정보 -> MemberBean
					MemberBean mb = new MemberBean();
					
						mb.setAge(rs.getInt("age"));
						mb.setEmail(rs.getString("email"));
						mb.setGender(rs.getString("gender"));
						mb.setId(rs.getString("id"));
						mb.setName(rs.getString("name"));
						mb.setPw(rs.getString("pw"));
						mb.setRegdate(rs.getTimestamp("regdate"));
					
					// MemberBean -> ArrayList
					mList.add(mb);
					
				} // while
				System.out.println(" DAO : 회원목록 조회 성공!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return mList;
		}
		// 회원정보 조회(all) - memberList()
		
} // class 끝
