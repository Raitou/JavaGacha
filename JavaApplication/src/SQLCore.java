
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raito
 */
public class SQLCore extends SQLDriver {
    
    //Not yet tested: Supposedly for admin purposes    
    public boolean addData(String statement){
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
    
    public int getUserAuth(int userID){
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
        return 0;
    }
    
    /**
     Returns UserID[0] and Nickname[1] if account exists else returns null
     * @param user
     * @param pass
     * @return String[2]
     */
    public String[] getLogin(String user, String pass){
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
    
    public String getUsername(String user, int userID){
        String username;
        String statement = "SELECT Login, UserID FROM USERS WHERE Login='"
                + user + "' AND UserID='" 
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
    
    public String getPassword(String user, int userID){
        String password;
        String statement = "SELECT Password FROM USERS WHERE Login='"
                + user + "' AND UserID='" 
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
    
    public String getNickname(String user, int userID){
        String nickname;
        String statement = "SELECT Nickname FROM USERS WHERE Login='"
                + user + "' AND UserID='" 
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
    
    public String setUsername(String user, int userID){
        String username;
        String statement = "SELECT Username FROM USERS WHERE Login='"
                + user + "' AND UserID='"
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
    
    public ArrayList<GachaItem> getItems(int type){
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
}
