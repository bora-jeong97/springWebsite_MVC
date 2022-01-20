package com.newlecture.web.service.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;



public class JDBCNoticeService implements NoticeService{
	// 공통 부분은 가장 최상위로 
	/*
	 * private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; 
	 * private String uid = "NEWLEC"; // 유저 id 
	 * private String pwd = "7731"; 
	 * private String driver = "oracle.jdbc.driver.OracleDriver";
	 */
	
	@Autowired
	private DataSource dataSource;
	

	//setter
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}


	// 함수들 select insert update delete	
	// 페이징 select
	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException{

		int start = 1 + (page-1)*10;  // 1, 11, 21, 31 ...
		int end = 10*page;  // 10, 20, 30, 40 ...
		
		String sql = "SELECT * FROM NOTICE_VIEW WHERE "+field+" LIKE ? AND NUM BETWEEN ? AND ?";
		
		//Class.forName(driver);
		//Connection con = DriverManager.getConnection(url, uid, pwd);
		Connection con = dataSource.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, start);
		st.setInt(3, end);
		
		ResultSet rs = st.executeQuery();
		// Statement st = con.createStatement();
		//ResultSet rs = st.executeQuery(sql);

		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) { // 전체 데이터를 받아오기 위해 while 반복문을 써준다.
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("hit");
			String files = rs.getString("FILES");
			
			// Notice 클래스 생성자 불러오기
			Notice notice = new Notice(
								id,
								title,
								writerId,
								regDate,
								content,
								hit,
								files
					);
			
			list.add(notice); // 하나하나 담아준다
			
//			System.out.printf("id : %d, title : %s, writerId : %s, "
//					+ "regDate : %s, content : %s, hit : %d\n",
//					id, title, writerId, regDate, content, hit);
		}

		
		
		
		rs.close();
		st.close();
		con.close();
		
	
		return list;
	}
	
	
	// 총 게시글의 수
	public int getCount() throws ClassNotFoundException, SQLException {
		int count = 0;
		
		String sql = "SELECT COUNT(ID) AS COUNT FROM NOTICE";
		
		//Class.forName(driver);
		//Connection con = DriverManager.getConnection(url, uid, pwd);
		Connection con = dataSource.getConnection();
		Statement st = con.createStatement();

		
		ResultSet rs = st.executeQuery(sql);
		// Statement st = con.createStatement();
		//ResultSet rs = st.executeQuery(sql);

		
		
		if(rs.next()) // 
		count = rs.getInt("COUNT");

		
		rs.close();
		st.close();
		con.close();
		
	
		return count;
	}
	
	
	// insert 
	public int insert(Notice notice) throws SQLException, ClassNotFoundException {
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		
		
		String sql = "INSERT INTO notice (" + 
				"    title," + 
				"    writer_id," + 
				"    content," + 
				"    files" + 
				") VALUES (?,?,?,?)";
		
		//Class.forName(driver);
		//Connection con = DriverManager.getConnection(url, uid, pwd);
		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title); // 물음표 인덱스를 앞에 넣어준다. 1부터 시작 (인덱스, 객체명)
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		// select는 executeQuery 그외 조작어는 executeUpdate사용
		// select의 경우
		// ResultSet rs = st.executeQuery(sql);  
		
		// insert, delete의 경우
		// st.executeUpdate();
		
		int result = st.executeUpdate(); // executeUpdate의 반환값은 rowcount 현재는 1이 나온다
		
		// System.out.println(result);
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int update(Notice notice) throws SQLException, ClassNotFoundException {
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		
		
		String sql = "UPDATE NOTICE " +   // 이때 notice뒤에 공백이 없다면 noticeset이라는 키워드가 된다 공백필수
				"SET" + 
				"    TITLE=?," + 
				"    CONTENT=?," + 
				"    FILES=?" + 
				"WHERE ID =?";
		
		//Class.forName(driver);
		//Connection con = DriverManager.getConnection(url, uid, pwd);
		Connection con = dataSource.getConnection();

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);


		int result = st.executeUpdate(); // executeUpdate의 반환값은 rowcount 현재는 1이 나온다
				

		
		st.close();
		con.close();
		
		return result;
	}
	
	public int delete(int id) throws SQLException, ClassNotFoundException {
		// 파라미터로 int id를 받기 때문에 여기서 notice.getId를 쓸 필요는 없다
		
		String sql = "DELETE NOTICE WHERE ID =?";
		
		//Class.forName(driver);
		//Connection con = DriverManager.getConnection(url, uid, pwd);
		Connection con = dataSource.getConnection();

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);


		int result = st.executeUpdate(); // executeUpdate의 반환값은 rowcount 현재는 1이 나온다
		

		
		st.close();
		con.close();
		
		return result;
	}



	
}











