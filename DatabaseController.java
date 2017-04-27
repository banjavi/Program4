import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class DatabaseController {
	static final long serialVersionUID = 1L;
	  /**
	   * A handle to the connection to the DBMS.
	   */
	  protected Connection connection_;
	  /**
	   * A handle to the statement.
	   */
	  protected Statement statement_;
	  /**
	   * The connect string to specify the location of DBMS
	   */
	  protected String connect_string_ = null;
	  /**
	   * The password that is used to connect to the DBMS.
	   */
	  protected String password = null;
	  /**
	   * The username that is used to connect to the DBMS.
	   */
	  protected String username = null;

	public DatabaseController() {
	    // your cs login name
	    username = "banjavi"; // need owner of table for insertions
	    // your Oracle password, NNNN is the last four digits of your CSID
	    password = "a1095"; // need owner of table for insertions
	    connect_string_ = "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
	  }


	/**
	   * Closes the DBMS connection that was opened by the open call.
	   */
	  public void Close() {
	    try {
	      statement_.close();
	      connection_.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    connection_ = null;
	  }
	  public void Open() {
	        try {
	            Class.forName("oracle.jdbc.OracleDriver");
	            connection_ = DriverManager.getConnection(connect_string_, username, password);
	            statement_ = connection_.createStatement();
	            return;
	        } catch (SQLException sqlex) {
	            sqlex.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            System.exit(1); //programemer/dbsm error
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            System.exit(2);
	        }
	    }

	  /**
	   * Commits all update operations made to the dbms.
	   * If auto-commit is on, which is by default, it is not necessary to call
	   * this method.
	   */
	  public void Commit() {
	    try {
	      if (connection_ != null && !connection_.isClosed())
	        connection_.commit();
	    } catch (SQLException e) {
	      System.err.println("Commit failed");
	      e.printStackTrace();
	    }
	  }

		//example method
	  public Vector<String> FindAllProducts() {
    String sql_query = "SELECT * FROM banjavi.products order by name";
    try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_employees = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("PRODUCTID") + "##" + rs.getString("NAME") +
             "##" + rs.getDouble("STOCK") + "##" +rs.getDouble("PRICE") + "##" + rs.getInt("CATEGORY");
        result_employees.add(temp_record);
      }
      return result_employees;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }

	public static void main(String[] args) throws SQLException {


		//1) customer order products
		//2) customer view past orders


//		Scanner reader = new Scanner(System.in);
//		int userSelection = 0;
//		try {
//			userSelection = reader.nextInt();
//		} catch (InputMismatchException exception) {
//			System.out.println("Invalid selection option");
//			System.exit(-1);
//		}
//
//		// Connection to Send the query to the DBMS
//	//	Statement stmt = databaseConnect.createStatement();
//
//		if (userSelection == 1) {
//			//Customer adds a new order
//			ResultSet productsToAdd = null; //all products to add
//
//			int randomNum = 1234;
//			int orderId = 3454326;
//			int userId = 67876;
//			Date date = productsToAdd.getDate(0);
//			int productID = 34526;
//			int qty = 3;
//
//			//while(there exists a next product){
//			stmt.executeQuery("insert into orders values (" + randomNum + ", " + orderId +", " + userId +", "+ date +", "
//					+ null +", "+ productID + ", " + qty + ")");
//

		}
	}

}
