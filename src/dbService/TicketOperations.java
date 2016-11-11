/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author x00122527
 */
public class TicketOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;

    // This method opens a connection to the Oracle database
    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

            // Tallaght
             ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
             ods.setUser("X00122527");
             ods.setPassword("db27Jul95");
            // Home Oracle XE
            //ods.setURL("jdbc:oracle:thin:HR/pmagee@localhost:1521:XE");
            //ods.setUser("hr");
            //ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (Exception e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
        return conn;
    }

    public void dropTicketsTable() { // todo -> create tickets table
        System.out.println("Checking for existence of Contacts table");
        try {
            String s1 = "DROP TABLE Contacts";
            pstmt = conn.prepareStatement(s1);
            try {
                // Drop the Contacts table.
                pstmt.execute();
                System.out.println("Contacts table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Contacts table");
        }

    }

    public void dropTable() {
        System.out.println("Checking for existence of AddressBookOwners table.");
        try {
            String s1 = "DROP TABLE AddressBookOwners";
            pstmt = conn.prepareStatement(s1);
            try {
                pstmt.execute();
                System.out.println("AddressBook table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the AddressBook table");
        }
    }

public void dropContactsAddressBookTable() {
        System.out.println("Checking for existence of ContactsAddressBook table.");
        try {
            String s1 = "DROP TABLE ContactsAddressBook";
            pstmt = conn.prepareStatement(s1);
            try {
                pstmt.execute();
                System.out.println("ContactsAddressBook table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the ContactsAddressBook table");
        }
    }

    public void dropAddressBookOwnerSequence() {
        try {
            String s2 = "drop sequence addressownerid_seq";
            pstmt = conn.prepareStatement(s2);
            try {
                pstmt.execute();
                System.out.println("AddressBookOwner Sequence dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The sequence does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the AddressBook sequence");
        }
    }
        public void dropContactSequence() {
        try {
            String s2 = "drop sequence cid_seq";
            pstmt = conn.prepareStatement(s2);
            try {
                pstmt.execute();
                System.out.println("Contact Sequence dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The sequence does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Contact sequence");
        }
    }
    public void createTicketSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence tid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Ticket Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Ticket Sequence " + ex.getMessage());
        }

    }

    public void createMovieSequence() {
        // Creating a sequence    
        try {
            String createseq2 = "create sequence movieid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq2);
            pstmt.executeUpdate();
            System.out.println("Movie Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Create Sequence " + ex.getMessage());
        }
    }


    public void CreateTicketsTable() {
        try {

            // Create a Table
            String create = "CREATE TABLE Tickets "
                    + "(ticket_id NUMBER PRIMARY KEY, ticket_price double, "
                    + "total_qty number, qty_sold number)";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertData = "INSERT INTO Tickets(ticket_id,ticket_price,total_qty,qty_sold) "
                    + "values(tid_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertData);

            pstmt.setDouble(1, 7.5);
            pstmt.setInt(2, 100);
            pstmt.setInt(3, 78);
            pstmt.execute();

            pstmt.setDouble(1, 6.0);
            pstmt.setInt(2, 100);
            pstmt.setInt(3, 33);
            pstmt.execute();
            
            pstmt.setDouble(1, 10.0);
            pstmt.setInt(2, 50);
            pstmt.setInt(3, 50);
            pstmt.execute();
            
            pstmt.setDouble(1, 8.25);
            pstmt.setInt(2, 25);
            pstmt.setInt(3, 3);
            pstmt.execute();

            System.out.println("Tickets table created");
        } catch (SQLException e) {
            System.out.print("SQL Exception creating and inserting values into Tickets " + e.getMessage());
            System.exit(1);
        }
    }

    public void createMovieTable() {
        // Create a Table           
        try {
            String create = "CREATE TABLE Movie "
                    + "(movie_id NUMBER PRIMARY KEY NOT NULL, movie_name VARCHAR(40), category VARCHAR(40), released_date DATE, running_time number)";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertString1 = "INSERT INTO AddressBookOwners(movie_id, movie_name, category, released_date, running_time) "
                    + "values(movieid_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString1);

            pstmt.setString(1, "Arrival");
            pstmt.setString(2, "Drama");
            pstmt.setDate(3, java.sql.Date.valueOf("2016-09-01"));
            pstmt.setInt(4, 116);
            pstmt.execute();
            
            pstmt.setString(1, "Trolls");
            pstmt.setString(2, "Animation");
            pstmt.setDate(3, java.sql.Date.valueOf("2016-10-08"));
            pstmt.setInt(4, 92);
            pstmt.execute();
            
            pstmt.setString(1, "Nocturnal Animals");
            pstmt.setString(2, "Drama");
            pstmt.setDate(3, java.sql.Date.valueOf("2016-09-02"));
            pstmt.setInt(4, 117);
            pstmt.execute();
            
            pstmt.setString(1, "Inferno");
            pstmt.setString(2, "Action");
            pstmt.setDate(3, java.sql.Date.valueOf("2016-10-08"));
            pstmt.setInt(4, 120);
            pstmt.execute();

            

            System.out.println("AddressBookOwners table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values into AddressBookOwners" + ex.getMessage());
        }
    }

    
    public void createContactsAddressBook() {
        // Create a Table           
        try {
           
            
            String create = "CREATE TABLE ContactsAddressBook "
                    + "(abid NUMBER, cid NUMBER, PRIMARY KEY (abid, cid), FOREIGN KEY (abid) REFERENCES AddressBookOwners (id),"
                    + "FOREIGN KEY (cid) REFERENCES Contacts (id) ON DELETE CASCADE )";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertString1 = "INSERT INTO ContactsAddressBook(abid,cid) "
                    + "values(?,?)";
            pstmt = conn.prepareStatement(insertString1);

            pstmt.setInt(1, 1);
            pstmt.setInt(2, 1);
            pstmt.execute();
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 2);
            pstmt.execute();

            pstmt.setInt(1, 2);
            pstmt.setInt(2, 1);
            pstmt.execute();
            pstmt.setInt(1, 2);
            pstmt.setInt(2, 3);
            pstmt.execute();

            pstmt.setInt(1, 3);
            pstmt.setInt(2, 1);
            pstmt.execute();

            System.out.println("ContactsAddressBook table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values into ContactsAddressBook" + ex.getMessage());
        }
    }

    public void closeDB() {
        try {
            pstmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
    

    
    public static void main(String[] args) {
        TicketOperations to = new TicketOperations();
        to.openDB();
        to.dropContactSequence();
        to.dropAddressBookOwnerSequence();

        to.dropTicketTable();
        to.dropAddressBookOwnersTable();
        to.dropContactsTable();

        to.createTicketSequence();
        to.createAddressBookOwnersSequence();

        to.CreateTicketsTable();
        to.createAddressBookOwnerstable();
        to.createContactsAddressBook();
    }
}

