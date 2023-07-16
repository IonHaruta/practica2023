

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.ssn.practica.work.hibernate.Course;
import com.ssn.practica.work.hibernate.Evaluation;
import com.ssn.practica.work.hibernate.Trainee;
import com.ssn.practica.work.utils.WithSessionAndTransaction2;


public class ArticolExample {
	private Scanner scan = new Scanner(System.in);
	private Connection con;
	private WithSessionAndTransaction2 tmp;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ArticolExample articolExample = new ArticolExample();
		articolExample.startApplication();
	}

	private void startApplication() throws ClassNotFoundException {
		con = connect();

		int opt = -1;

		while (opt != 0) {

			showMenu();

			opt = getInt("Insert option :");

			switch (opt) {
			case 1: {
//				int id = getInt("Insert id: ");
//				add("articol", new Object[] { id, nume });
				String articleName = getString("Insert article:");
				addArticle(articleName);
				break;
			}

			case 2: {
//				int id = getInt("Insert id: ");
//				add("magazin", new Object[] { id, nume });
				String magazinName = getString("Insert magazin:");
				addMagazin(magazinName);
				break;
			}

			case 3: {
//				int articleId = getInt("Insert Articol_id : ");
//				int magazinId = getInt("Insert Magazin_id : ");
//				double pret = getDouble("Insert pret : ");
//				add("pret", new Object[] { articleId, magazinId, pret });
				String articleName = getString("Insert articol:");
				String magazinName = getString("Insert magazin:");
				int pretArticol = getInt("Insert pret:");
				addPret(pretArticol,articleName,magazinName);
				break;
			}
			case 4: {
				try {
					Statement s = con.createStatement();
					ResultSet rs = s.executeQuery(
							"select a.name articol, p.value, m.name magazin \n"
							+ "from Article a\n"
							+ "join Pret p on a.id = p.Article_id\n"
							+ "join Magazin m on p.Magazin_id = m.id\n"
							+ "where p.value = (\n"
							+ "  select MIN(value)\n"
							+ "  from Pret\n"
							+ "  where Article_id = a.id\n"
							+ ")");

					while (rs.next()) {
						System.out.println(rs.getString(1) + " " + rs.getDouble(2) + " " + rs.getString(3));
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

				break;
			}

			default:
				System.out.println("Bye.");
			}

		}
	}

	

	private void showMenu() {
		System.out.println("\nMenu :");
		System.out.println("0. Exit");
		System.out.println("1. Add Articol");
		System.out.println("2. Add Magazin");
		System.out.println("3. Add Preturi");
		System.out.println("4. Show statistics");
	}

	private Connection connect() throws ClassNotFoundException {
		Connection con = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/minitema", "root", "plmokn1234567890");
			if (con != null) {
				System.out.println("Success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public void addArticle(String articleName) {
		new WithSessionAndTransaction2() {
			@Override
			public void doAction(Session session) {

				
				Article article = new Article(articleName);
				session.persist(article);
			}
		}.run();
	}
	public void addMagazin(String magazinName) {
		new WithSessionAndTransaction2() {
			@Override
			public void doAction(Session session) {

				
				Magazin magazin = new Magazin(magazinName);
				session.persist(magazin);
			}
		}.run();
	}
	public void addPret(int pretArticol, String article, String magazin) {
		new WithSessionAndTransaction2() {
			@Override
			public void doAction(Session session) {

				TypedQuery<Magazin> query = session.createQuery("from Magazin where name = :nameParameter", Magazin.class);
				query.setParameter("nameParameter", magazin);
				
				TypedQuery<Article> query1 = session.createQuery("from Article where name = :nameParameter", Article.class);
				query1.setParameter("nameParameter", article);
				
				Magazin magazin1 = query.getSingleResult();
				magazin1.getArticles().toString();
				
				Article article1 = query1.getSingleResult();
				article1.getMagazin().toString();
				
//				magazin1.setName(magazin);
//				article1.setName(article);
				article1.getMagazin().add(magazin1);
				Pret pret = new Pret(pretArticol, magazin1, article1);
				pret.setValue(pretArticol);
				session.persist(pret);
			}
		}.run();
	}

	private String generatePlaceHolders(Object[] params) {
		String s = "?";
		for (int i = 1; i < params.length; i++) {
			s += ",?";
		}
		return s;
	}

	private int getInt(String message) {
		System.out.print(message);
		return Integer.parseInt(scan.nextLine());
	}
	private double getDouble(String message) {
		System.out.print(message);
		return Double.parseDouble(scan.nextLine());
	}
	private String getString(String message) {
		System.out.print(message);
		return scan.nextLine();
	}

}
