import java.sql.*;
import java.util.*;
public class test {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/xxxxxx"; //<-- replace your database name with xxx
static final String USER = "user";	//<-- replace your username with xxx
   static final String PASS = "password";	//<-- replace your mysql password with xxx
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      // Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      // Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      Scanner scan = new Scanner(System.in);

      System.out.println("Creating statement...");
      System.out.println("Enter the from  airport code:");
      String fromAirport = scan.next();

      //System.out.println("this .."+fromAirport );      

      System.out.println("Enter the to airport to code: ");
      String toAirport = scan.next();

     // System.out.println("this..." +toAirport);

      System.out.println("Enter the seatclass:");
      String seatClass = scan.next();


      //System.out.println("this."+seatClass );

      String sql;
      sql = "SELECT * FROM Airport_To_Airport_Capacity WHERE FromA='"+fromAirport+"' AND ToA='"+toAirport+"' AND SeatClass='"+seatClass+"' " ;
      ResultSet rs = stmt.executeQuery(sql);

      // Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         System.out.println("From=" +rs.getString("FromA"));
         System.out.println("To=" +rs.getString("ToA"));
         System.out.println("seatType=" +rs.getString("SeatClass"));
         System.out.println("AvailableSeats=" +rs.getString("AvailableSeats")); 
      }
      // Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample
