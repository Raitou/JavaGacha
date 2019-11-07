/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aevan
 */

public class User {
    private int userID;
    private String username;
    private String nickname;
    
    /*
    Constructor that takes userID as a parameter
    Retrieves the row of a user that matches userID and uses those values
    to initialize the object
    */
    public User(int userID){
        String[] userData = SQLCore.getLogin(userID);
        this.userID = Integer.parseInt(userData[0]);
        this.nickname = userData[1];
        this.username = userData[2];
    }
    
    /*
    Overloaded constructor that takes username and password as parameters
    Retrieves the row of a user that matches both username and password
    Details from the row is used to initialize the object
    */
    public User(String username, String password){
        String userData[] = SQLCore.getLogin(username, password);
        userID = Integer.parseInt(userData[0]);
        this.nickname = userData[1];
        this.username = userData[2];
        
    }
    
    public int getUID(){
        return userID;
    }
    
    public String getName(){
        return username;
    }
    
    public String getNickname(){
        return nickname;
    }
    
    /*
    Returns the GP of the user
    Uses an SQL DML  to retrieve the GP of the user
    We don't want to keep a GP attribute because we do not need to know
    the GP in the initial LoginWindow
    */
    public int getGP(){
        return SQLCore.getGP(userID);
    }
    
    /*
    Updates the User GP by the parameter
    Uses SQL DML to update the user's GP in the database
    by the parameter
    */
    public void updateGP(int gamePoints){
        int currentGP = SQLCore.getGP(userID);
        currentGP += gamePoints;
        
        SQLCore.setGP(userID, currentGP);
    }
    
    
    /*
    Keeps the User object up to date
    Used in Change Profile Panel
    */
    public void refresh(){
        username = SQLCore.getUsername(userID);
        nickname = SQLCore.getNickname(userID);
    }
    
}
