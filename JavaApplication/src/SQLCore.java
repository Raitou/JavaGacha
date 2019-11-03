
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raito
 */
public class SQLCore extends SQLDriver implements AuthLevel {
    
    /**
     * Method that adds data in the MySQL Database
     * 
     * @param user
     * @param password
     * @param nickName
     * @return  A Boolean that returns true if data is added else return false
     */    
    public static boolean addData(String user, String password, String nickName){
        
        String statement = "SELECT * FROM Users WHERE Login='" + user + "';";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            if(res.next()){
                return false;
            }
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        
        statement = "INSERT INTO Users(Login, Password, Nickname) VALUES('" 
                + user + "', '" + password + "', '" + nickName + "');";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            query.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
    
    public static String[] searchUserInformation(String user){
        String statement = "SELECT * FROM Users WHERE Login='"+ user + "';";
        String arr[] = new String[3];
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            while(res.next()){
                arr[0] = res.getString("Nickname");
                arr[1] = res.getString("Password");
                arr[2] = Integer.toString(getUserAuth(res.getInt("UserID")));
                return arr;
            }
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        
        return null;
    
    }
    
    public static int getUserAuth(int userID){
        String statement = "SELECT AuthLevel FROM userauth WHERE UserID="+ userID +";";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            while(res.next()){
                return res.getInt("AuthLevel");
            }
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return NORMAL_USER;
    }
    
    /**
     Returns UserID[0], Nickname[1] and Login[2] if account exists else returns null
     * @param user
     * @param pass
     * @return String[3] else null
     */
    public static String[] getLogin(String user, String pass){
        String arr[] = new String[3];
        String statement = "SELECT Nickname, UserID, Login FROM USERS WHERE Login='"
                + user + "' AND Password='" 
                + pass + "';";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            while(res.next()){
                arr[0] = res.getString("UserID");
                arr[1] = res.getString("Nickname");
                arr[2] = res.getString("Login");
                return arr;
            }
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static String getUsername(String user, int userID){
        String username;
        String statement = "SELECT Login, UserID FROM USERS WHERE UserID='" 
                + userID + "';";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            while(res.next()){
                username = res.getString("Login");
                return username;
            }
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static String getPassword(int userID){
        String password;
        String statement = "SELECT Password FROM USERS WHERE UserID='" 
                + userID + "';";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            while(res.next()){
                password = res.getString("Password");
                return password;
            }
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static String getNickname(int userID){
        String nickname;
        String statement = "SELECT Nickname FROM USERS WHERE UserID='" 
                + userID + "';";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            while(res.next()){
                nickname = res.getString("Nickname");
                return nickname;
            }
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static String setUsername(String user, int userID){
        String username;
        String statement = "SELECT Username FROM USERS WHERE UserID='"
                + userID + "';";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            while(res.next()){
                username = res.getString("Username");
                return username;
            }
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static ArrayList<GachaItem> getItems(int type){
        ArrayList<GachaItem> gachaItems = new ArrayList<>();
        String statement = "SELECT Item_ID,Item_Name,Item_Type FROM ItemInfoList WHERE Item_Type="
                + type + ";";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            ResultSet res = query.executeQuery();
            while(res.next()){
                gachaItems.add(new GachaItem(res.getInt("Item_ID"),
                        res.getInt("Item_Type"),
                        res.getString("Item_Name")));
            }
            return gachaItems;
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static void itemOwn(int userID, GachaItem item){
        String statement = "INSERT INTO ItemOwnership (UserID,ItemID) VALUES ("
                + userID + "," + item.getId() + ");";
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            query.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public static void updateGP(int userID, int gamePoints){
        String statement = "UPDATE Users"
                + "SET GamePoints = " + gamePoints;
        try(Connection con = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
                PreparedStatement query = con.prepareStatement(statement);
                ){
            query.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
