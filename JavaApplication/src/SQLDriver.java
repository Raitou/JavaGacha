/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raito
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDriver {
    /*
    This class determines which SQL server to connect to 
    Also determines the User and password in order to use the database
    */
<<<<<<< HEAD
    protected final static String CONNECTION_URL = "jdbc:mysql://localhost/testdb?useSSL=false";
    protected final static String USER = "aevan";
    protected final static String PASS = "evanamel";
=======
    protected final static String CONNECTION_URL = "jdbc:mysql://95.216.115.22/testdb?useSSL=false";
    protected final static String USER = "oop";
    protected final static String PASS = "ExoPanda*0909";
>>>>>>> origin/master
    
    protected SQLDriver(){
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);){
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
   
}
