/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author nhatl
 */
public class GroupProject {

    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/GroupDatabase";

    //  Database credentials
    static final String USER = "nhatle";
    static final String PASS = "123";
    static final String one = "SELECT * FROM WritingGroups";

    static final String three = "SELECT * FROM Publishers";
    static final String five = "SELECT * FROM Books";

    /**
     * @param args the command line arguments
     */
    public static void one() {
        printDatabase("WritingGroups");
    }

    public static void two() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        //Assuming that users always enter a group that exists in our table
        System.out.println("Enter group name: ");

        String groupName = myObj.nextLine();  // Read user input
        System.out.println("GroupName is: " + groupName);  // Output user input
        
        

        try {
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //Check if group name exists
            String check_query = "select groupName from WritingGroups where groupName = ?";
            PreparedStatement check_stmt = conn.prepareStatement(check_query);
            check_stmt.setString(1, groupName);
            ResultSet result = check_stmt.executeQuery();
            if (!result.next()) {
                System.out.println("Group name " + groupName + "does not exist");
                return;
            }
            
            String query = "select * from "
                    + "Books natural join WritingGroups natural join Publishers where groupName = ?";
                    

            pstmt = conn.prepareStatement(query); // create a statement
            pstmt.setString(1, groupName); // set input parameter
            rs = pstmt.executeQuery();
            // extract data from the ResultSet
            while (rs.next()) {
                String group_name = rs.getString("GroupName");
                    String head_writer = rs.getString("HeadWriter");
                    String year_formed = rs.getString("YearFormed");
                    String subject = rs.getString("Subject");
                    String book_title = rs.getString("BookTitle");
                    String publisher_name = rs.getString("PublisherName");
                    String year_published = rs.getString("YearPublished");
                    String number_pages = rs.getString("NumberPages");
                    String publisher_address = rs.getString("PublisherAddress");
                    String publisher_phone = rs.getString("PublisherPhone");
                    String publisher_email = rs.getString("PublisherEmail");
    
                    System.out.print("GroupName: " + group_name);
                    System.out.print(", HeadWriter: " + head_writer);
                    System.out.print(", year_formed: " + year_formed);
                    System.out.println(", subject: " + subject);
                    System.out.println(", Book title: " + book_title);
                    System.out.println(", Publisher name: " + publisher_name);
                    System.out.println("Year published: " + year_published);
                    System.out.println("Number pages: " + number_pages);
                    System.out.println("Publisher address: " + publisher_address);
                    System.out.println("Publisher phone: " + publisher_phone);
                    System.out.println("Publisher email: " + publisher_email);
                    rs.close();
                    pstmt.close();
                    conn.close();
            }
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public static void three() {
        printDatabase("Publishers");
    }

    public static void printDatabase(String table_name) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //String sql;
            //sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
            String sql = "SELECT * from " + table_name;
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                if (table_name.equals("WritingGroups")) {
                    String group_name = rs.getString("GroupName");
                    String head_writer = rs.getString("HeadWriter");
                    String year_formed = rs.getString("YearFormed");
                    String subject = rs.getString("Subject");
                    System.out.print("GroupName: " + group_name);
                    System.out.print(", HeadWriter: " + head_writer);
                    System.out.print(", year_formed: " + year_formed);
                    System.out.println(", subject: " + subject);

                } else if (table_name.equals("Books")) {
                    String group_name = rs.getString("GroupName");
                    String book_title = rs.getString("BookTitle");
                    String publisher_name = rs.getString("PublisherName");
                    String year_published = rs.getString("YearPublished");
                    String number_pages = rs.getString("NumberPages");
                    System.out.print("GroupName: " + group_name);
                    System.out.print(", BookTitle: " + book_title);
                    System.out.print(", PublisherName: " + publisher_name);
                    System.out.println(", YearPublished: " + year_published);
                    System.out.println(", NumberPages:" + number_pages);
                } else if (table_name.equals("Publishers")) {
                    String publisher_name = rs.getString("PublisherName");
                    String publisher_address = rs.getString("PublisherAddress");
                    String publisher_phone = rs.getString("PublisherPhone");
                    String publisher_email = rs.getString("PublisherEmail");
                    System.out.print("PublisherName: " + publisher_name);
                    System.out.print(", PublisherAddress: " + publisher_address);
                    System.out.print(", Publisher_phone: " + publisher_phone);
                    System.out.println(", publisher_email: " + publisher_email);
                }
            }
            //Display values
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    public void seven() {
        Connection conn = null;
        Statement stmt = null;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        //Assuming that users always enter a group that exists in our table
        System.out.println("Enter group name: ");

        String groupName = myObj.nextLine();  // Read user input
        System.out.println("GroupName is: " + groupName);  // Output user input
        System.out.println("Enter book title: ");
        String book_title = myObj.nextLine();
        System.out.println("Enter number of pages: ");
        String number_of_pages = myObj.nextLine();
        System.out.println("Enter publisher: ");
        String publisher = myObj.nextLine();
        System.out.println("Enter year published: ");
        int year_published = myObj.nextInt();
        //Check if group name exists
        
        //Check if publisher exists
        
      
        
        
    }

    public static void main(String[] args) {
        // TODO code application logic here
        //printDatabase("WritingGroups");
        two();

    }

}
