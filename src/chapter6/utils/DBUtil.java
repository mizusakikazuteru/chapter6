package chapter6.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import chapter6.exception.SQLRuntimeException;

/**
 * DB(コネクション関係)のユーティリティー
 */
public class DBUtil {
	//mysql設定
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/twitter";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

//	Class.forName("com.mysql.jdbc.Driver");
//	Connection con =
//DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
//	Statement st = con.createStatement();
//	String sql ="SELECT * FROM bbs ORDER BY bbs_id DESC";
//	PreparedStatement pst = con.prepareStatement(sql);
//	ResultSet rs = st.executeQuery(sql);

//	private static final String DRIVER = "org.hsqldb.jdbcDriver";
//	private static final String URL = "jdbc:hsqldb:hsql://localhost/";
//	private static final String USER = "SA";
//	private static final String PASSWORD = "";

	static {

		try {
			Class.forName(DRIVER).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//*********** コネクションを取得します。***************
	public static Connection getConnection() {

		try {
			Connection connection = DriverManager.getConnection(URL, USER,
					PASSWORD);

			connection.setAutoCommit(false);

			return connection;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	//**************コミットします*********************
	public static void commit(Connection connection) {

		try {
			connection.commit();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * ロールバックします。
	 *
	 * @param connection
	 */
	public static void rollback(Connection connection) {

		if (connection == null) {
			return;
		}

		try {
			connection.rollback();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

}
