

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minitema", "root",
				"plmokn1234567890")) {
			if (conn != null) {
				System.out.println("Connection successful");
			}


//			insertArticle(conn, 1, "popcorn");
//			insertArticle(conn, 2, "lays");
//			insertArticle(conn, 3, "cola");
//			
//			insertMagazin(conn, 1, "Auchan");
//			insertMagazin(conn, 2, "Lidl");
//			insertMagazin(conn, 3, "Kaufland");
//			
//			insertPret(conn, 1, 1, 4.50);
//			insertPret(conn, 1, 2, 3.50);
//			insertPret(conn, 2, 3, 9.89);
//			insertPret(conn, 2, 2, 7.47);
//			insertPret(conn, 3, 2, 4.90);
//			insertPret(conn, 3, 1, 5.35);


			

			Statement statement4 = conn.createStatement();
			ResultSet resultSet = statement4.executeQuery("select * from pret_min");
			
			while (resultSet.next()) {
				System.out.println(//
						resultSet.getString(1) + " " + //
								resultSet.getString(2) + " " + //
								resultSet.getDouble(3) + " " 
				);
			}

			resultSet.close();
			
			
//			Statement statement = conn.createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from vw_note_studenti");
//
//			while (resultSet.next()) {
//				System.out.println(//
//						resultSet.getInt(1) + " " + //
//								resultSet.getString(2) + " " + //
//								resultSet.getDouble(3) + " " + //
//								resultSet.getInt(4) //
//				);
//			}
//
//			resultSet.close();
		}
		
	}
	public static void insertArticle(Connection conn, int id, String nume) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into articol values (?, ?)");
		statement.setInt(1, id);
		statement.setString(2, nume);
		
		statement.execute();
	}
	public static void insertMagazin(Connection conn, int id, String nume) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into magazin values (?, ?)");
		statement.setInt(1, id);
		statement.setString(2, nume);
		
		statement.execute();
	}
	public static void insertPret(Connection conn, int idArticle, int idMagazin, double pret) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into pret values (?, ?, ?)");
		statement.setInt(1, idArticle);
		statement.setInt(2, idMagazin);
		statement.setDouble(3, pret);
		
		statement.execute();
	}
	
}
