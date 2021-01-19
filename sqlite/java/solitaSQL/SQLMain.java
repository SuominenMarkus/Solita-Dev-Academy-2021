package solitaSQL;



import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLMain {

    // Connect to the database

    private Connection connect() {
        
	// Default database file location
        String url = "jdbc:sqlite:C://sqlite/db/solita.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    
// Printing all the names in amount desc order   

public void selectAll(){
        String sql = "SELECT name, amount FROM names ORDER BY amount desc";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") +  "\t" + "Amount: " +
                                   rs.getInt("amount"));
                                   
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

// Printing all the names in alphabetical order

public void selectNames(){
        String sql = "SELECT name FROM names ORDER BY name";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                                   
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


// Sum of all the names

public void selectAmount(){
        String sql = "SELECT sum(amount) FROM names";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
            	System.out.println("\nThere are " + rs.getInt(1) + " names in total");

                                   
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

// Print out the amount of parameter name by user input

public void selectNameAmount(){
	
	Scanner myObj = new Scanner(System.in);
	
	System.out.println("\nInput one of the following names with a capital first letter\n\nVille\nAnna\nAntti\nSanna\nMikko\nMinna\nTimo\nSatu\nTuomas\nTiina\nTero\nKati\nSami\nHenna\nMika\nLiisa\nJanne\nPaula\nPetri\nSuvi\n");
	
	String Nimi = myObj.nextLine();
        
		String sql = "select amount from names where name = " + "'" +Nimi + "'";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println("\nThere are " + rs.getString(1) + " " + "people named " + Nimi);
                                   
            }
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        
        }
        
        
    }

    
   
   
    public static void main(String[] args) {
    	
    	
	SQLMain app = new SQLMain();
        
	int number;

      
	// creating the scanner

    Scanner console = new Scanner(System.in);

	// info for user      

    System.out.println("\nChoose one by entering a number:\n1. List all the names and amounts, organized by the most popular name first\n2. List all the names in alphabetical order\n3. List the total amount of all the names\n4. Return the amount of the name given as a parameter\n");
    number = console.nextInt();


	// switch for user input
      
    switch (number)	{
      
	case 1 :
		
    app.selectAll();
	break;
	
	case 2 :
		
	app.selectNames();
	break;

	case 3 :
		
	app.selectAmount();
	break;

	case 4:

	app.selectNameAmount();
	break;

	default:
	System.out.println("Invalid number");
	
      }


    }

}