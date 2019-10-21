import java.sql.*;
import java.util.Scanner;

public class testing1 { // JDBC driver name and database URL
   static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
   static final String DB_URL = "jdbc:derby://localhost:1527/sqlPractice1";

   //  Database credentials
   static final String USER = "user001";
   static final String PASS = "PASS";
   
   ////////////////////////
     static String group_name;
     static String head_writer; 
     static String year_formed;          
     static String subject;
     static String book_title;
     static String publisher_name;
     static String year_published;
     static String number_pages;
     static String publisher_address;
     static String publisher_phone;
     static String publisher_email;
   //////////////////////
     
     static Connection conn = null;
     static PreparedStatement pstmt = null;
   public static void main(String[] args) throws SQLException {
   
   try {
   //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      
      
      System.out.println("Connected database successfully...");
      
      
      //publisherInfo();
      //listBookTitles();
      listOneBookAll();
      
      //STEP 4: Execute a query
      System.out.println("Creating table...");
      //stmt = conn.createStatement();
     
      /*
      String publisherNameUser = "DoubleDay";
      String sql = "SELECT * FROM WRITINGGROUPS NATURAL JOIN PUBLISHERS NATURAL JOIN BOOKS WHERE publisherName = ?";  
      
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, publisherNameUser);
      
      ResultSet rs = stmt.executeQuery();
      
       System.out.println("///////////////////" + publisherNameUser + " information///////////////////");
      while(rs.next())
      {
         group_name = rs.getString("GroupName");
         head_writer = rs.getString("HeadWriter");
         year_formed = rs.getString("YearFormed");
         subject = rs.getString("Subject");
         book_title = rs.getString("BookTitle");
         publisher_name = rs.getString("PublisherName");
         year_published = rs.getString("YearPublished");
         number_pages = rs.getString("NumberPages");
         publisher_address = rs.getString("PublisherAddress");
         publisher_phone = rs.getString("PublisherPhone");
         publisher_email = rs.getString("PublisherEmail");
    
         System.out.print("Publisher Name: " + publisher_name + " ");
         System.out.print("Publisher Address: " + publisher_address + " ");
         System.out.print("Publisher Phone: " + publisher_phone + " ");
         System.out.print("Publisher email: " + publisher_email + " ");
         System.out.print("Book Title: " + book_title + " ");
         System.out.print("Year Published: " + year_published + " ");
         System.out.print("Number Pages: " + number_pages + " ");
         System.out.print("Group name: " + group_name + " ");
         System.out.print("Head Writer: " + head_writer + " ");
         System.out.print("Year Formed: " + year_formed + " ");
         System.out.println("Subject: " + subject + " ");
      }
      
       System.out.println("Exiting print");
      */
      
      /*
     ///////////////////////////////////////////////////////////////////// CLEARS THE TABLES 
     String sql = "DROP TABLE WritingGroups";
     stmt.executeUpdate(sql);
     
     sql = "DROP TABLE Publishers";
     stmt.executeUpdate(sql);
     
     sql = "DROP TABLE Books";
     stmt.executeUpdate(sql);
     ///////////////////////////////////////////////////////////////////////////////////////////
      
     
      
     sql = "CREATE TABLE WritingGroups " +
                   "(groupName VARCHAR(255) NOT NULL, " +
                   " headWriter VARCHAR(255) NOT NULL, " +
                   " yearFormed INT , " +
                   " subject VARCHAR(255), " +
                   " CONSTRAINT pk_groupName PRIMARY KEY (groupName))";
      
      
     stmt.executeUpdate(sql);
      System.out.println("Created writing group table");
      
      sql = "Create TABLE Publishers " +
              " (publisherName VARCHAR(255) NOT NULL, " +
              " publisherAddress VARCHAR(255) NOT NULL, " +
              " publisherPhone VARCHAR(255) NOT NULL, " + 
              " publisherEmail VARCHAR(255) NOT NULL, " +
              " CONSTRAINT pk_publisherName PRIMARY KEY (publisherName))";
      
      stmt.executeUpdate(sql);
       System.out.println("Created publishers table");
       
       sql = "CREATE TABLE Books " +
               " (bookTitle VARCHAR(255) NOT NULL, " + 
               " yearPublished INT, " +
               " numberPages INT, " +
               " groupName VARCHAR(255) NOT NULL, "+
               " publisherName VARCHAR(255) NOT NULL, " + 
               " CONSTRAINT groupName_FK FOREIGN KEY (groupName) REFERENCES WritingGroups (groupName), " +
               " CONSTRAINT publisherName_FK FOREIGN KEY (publisherName) REFERENCES Publishers (publisherName), " +
               " CONSTRAINT pk_gName_bTitle PRIMARY KEY (groupName, bookTitle))";
       
       stmt.executeUpdate(sql);
       System.out.println("Created Books Table");
       
      */
      /*  
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         String id  = rs.getString("au_id");
         String phone = rs.getString("phone");
         String first = rs.getString("au_fname");
         String last = rs.getString("au_lname");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", First: " + first);
         System.out.print(", Last: " + last);
         System.out.println(", Phone: " + phone);
      }
      
     
      //STEP 6: Clean-up environment
      
       */
       //rs.close();
      
      //rs.close();
      pstmt.close();
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
         if(pstmt!=null)
            pstmt.close();
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

public static void listBookTitles() throws SQLException
{
    Statement stmt = conn.createStatement();
    
    String sql = "SELECT bookTitle FROM BOOKS";  
    
    ResultSet rs = stmt.executeQuery(sql);

    //pstmt = conn.prepareStatement(sql);
   
    //ResultSet rs = pstmt.executeQuery(sql);
      
    System.out.println("/////////////////// Listing All Book Titles ///////////////////");
    while(rs.next())
    {
         String titleBook = rs.getString("bookTitle");
         System.out.println("Book Title: " + titleBook + " ");
    }
    
    rs.close();
    stmt.close();  
    
}

public static void listOneBookAll() throws SQLException
{
    String temp, userBookTitle, userGroupName, userPublisherName;
    
    temp = "Book Title";
    
    userBookTitle = getUserInput(temp);
    
    temp = "Group Name";
    
    userGroupName = getUserInput(temp);
    
    temp = "Publisher Name";
    
    userPublisherName = getUserInput(temp);
    
    String check_query = "SELECT bookTitle FROM BOOKS where bookTitle = ?";
            PreparedStatement check_stmt = conn.prepareStatement(check_query);
            check_stmt.setString(1, userBookTitle);
            ResultSet result = check_stmt.executeQuery();
            if (!result.next()) {
                System.out.println("Book titled  " + userBookTitle + " does not exist");
                result.close();
                return;
            }
            
    
    check_query = "SELECT groupName FROM WritingGroups NATURAL JOIN BOOKS where groupName = ?";
            check_stmt = conn.prepareStatement(check_query);
            check_stmt.setString(1, userGroupName);
            result = check_stmt.executeQuery();
            if (!result.next()) {
                System.out.println("Group name " + userGroupName + "does not exist, , or doesn't match book title " + userBookTitle);
                result.close();
                return;
            }
    
    check_query = "SELECT publisherName FROM Publishers NATURAL JOIN BOOKS where publisherName = ?";
            check_stmt = conn.prepareStatement(check_query);
            check_stmt.setString(1, userPublisherName);
            result = check_stmt.executeQuery();
            if (!result.next()) {
                System.out.println("Publisher Name " + userPublisherName + "does not exist, or doesn't match book title " + userBookTitle);
                result.close();
                return;
            }
    
    String sql = "SELECT * FROM WRITINGGROUPS NATURAL JOIN PUBLISHERS NATURAL JOIN BOOKS WHERE bookTitle = ?";  
      
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, userBookTitle);
      
    ResultSet rs = pstmt.executeQuery();
      
      System.out.println("///////////////////" + userBookTitle + " information///////////////////");
      while(rs.next())
      {
         group_name = rs.getString("GroupName");
         head_writer = rs.getString("HeadWriter");
         year_formed = rs.getString("YearFormed");
         subject = rs.getString("Subject");
         book_title = rs.getString("BookTitle");
         publisher_name = rs.getString("PublisherName");
         year_published = rs.getString("YearPublished");
         number_pages = rs.getString("NumberPages");
         publisher_address = rs.getString("PublisherAddress");
         publisher_phone = rs.getString("PublisherPhone");
         publisher_email = rs.getString("PublisherEmail");
    
         System.out.print("Book Title: " + book_title + " ");
         System.out.print("Year Published: " + year_published + " ");
         System.out.print("Number Pages: " + number_pages + " ");
         System.out.print("Group name: " + group_name + " ");
         System.out.print("Head Writer: " + head_writer + " ");
         System.out.print("Year Formed: " + year_formed + " ");
         System.out.println("Subject: " + subject + " ");
         System.out.print("Publisher Name: " + publisher_name + " ");
         System.out.print("Publisher Address: " + publisher_address + " ");
         System.out.print("Publisher Phone: " + publisher_phone + " ");
         System.out.print("Publisher email: " + publisher_email + " ");
      }
      
       System.out.println("Exiting print");
       rs.close();
       result.close();
      

}



/*
public static void enterNewPublisher(String blah) throws SQLException
{
    Connection conn = null;
    PreparedStatement stmt = null;
    
    Scanner scan = new Scanner(System.in);
    
    System.out.print("Enter publisher name: ");
    String pName = scan.nextLine();
    
    System.out.print("Enter teh publisher address: ");
    String pAddress = scan.nextLine();
    
    System.out.print("Enter publisher phone: ");
    String pPhone = scan.nextLine();
    
    System.out.print("Enter publisher email");
    String pEmail = scan.nextLine();
    
    String sql = "INSERT INTO PUBLISHERS (publisher_name, publisher_address, publisher_phone, publisher_email) " +
                 "VALUES (?,?,?,?)";
    
    stmt = conn.prepareStatement(sql);
    stmt.setString(1,pName);
    stmt.setString(2,pAddress);
    stmt.setString(3,pPhone);
    stmt.setString(4,pEmail);
    
    stmt.executeQuery(); //Fill in table with information 
    
    //PRINT DATABASE 
    
}

public static void enterNewGroup() throws SQLException
{
    Connection conn = null;
    PreparedStatement stmt = null;
    
    Scanner scan = new Scanner(System.in);
    
    System.out.print("Enter group name: ");
    String gName = scan.nextLine();
    
    System.out.print("Enter Head Writer: ");
    String gHeadWriter = scan.nextLine();
    
    System.out.print("Enter Year Formed: ");
    String gYearFormed = scan.nextLine();
    
    System.out.print("Enter Subject");
    String gSubject = scan.nextLine();
    
    String sql = "INSERT INTO WRITINGGROUPS (group_name, head_writer, year_formed, subject) " +
                 "VALUES (?,?,?,?)";
    
    stmt = conn.prepareStatement(sql);
    stmt.setString(1,gName);
    stmt.setString(2,gHeadWriter);
    stmt.setString(3,gYearFormed);
    stmt.setString(4,gSubject);
    
    stmt.executeQuery(); //Fill in table with information 
    
    //PRINT DATABASE 
    
}
*/
      

public static void publisherInfo() throws SQLException
{
    String temp = "Publisher Name";
    String userPublisherName = getUserInput(temp);
    
    String sql = "SELECT * FROM WRITINGGROUPS NATURAL JOIN PUBLISHERS NATURAL JOIN BOOKS WHERE publisherName = ?";  
      
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, userPublisherName);
      
    ResultSet rs = pstmt.executeQuery();
      
      System.out.println("///////////////////" + userPublisherName + " information///////////////////");
      while(rs.next())
      {
         group_name = rs.getString("GroupName");
         head_writer = rs.getString("HeadWriter");
         year_formed = rs.getString("YearFormed");
         subject = rs.getString("Subject");
         book_title = rs.getString("BookTitle");
         publisher_name = rs.getString("PublisherName");
         year_published = rs.getString("YearPublished");
         number_pages = rs.getString("NumberPages");
         publisher_address = rs.getString("PublisherAddress");
         publisher_phone = rs.getString("PublisherPhone");
         publisher_email = rs.getString("PublisherEmail");
    
         System.out.print("Publisher Name: " + publisher_name + " ");
         System.out.print("Publisher Address: " + publisher_address + " ");
         System.out.print("Publisher Phone: " + publisher_phone + " ");
         System.out.print("Publisher email: " + publisher_email + " ");
         System.out.print("Book Title: " + book_title + " ");
         System.out.print("Year Published: " + year_published + " ");
         System.out.print("Number Pages: " + number_pages + " ");
         System.out.print("Group name: " + group_name + " ");
         System.out.print("Head Writer: " + head_writer + " ");
         System.out.print("Year Formed: " + year_formed + " ");
         System.out.println("Subject: " + subject + " ");
      }
      
       System.out.println("Exiting print");
       rs.close();
      
}

public static String getUserInput(String temp)
{
    String userInput;
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter " + temp + ": ");
    userInput = scan.nextLine();
    return userInput;
}
   
}//end FirstExample
 
   
