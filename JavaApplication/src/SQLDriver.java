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
    protected final static String CONNECTION_URL = "jdbc:mysql://95.216.115.22/testdb?useSSL=false";
    protected final static String USER = "oop";
    protected final static String PASS = "ExoPanda*0909";
    
    protected SQLDriver(){
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);){
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
   
}
