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
    
    
    public User(int userID){
        String[] userData = SQLCore.getLogin(userID);
        this.userID = Integer.parseInt(userData[0]);
        this.nickname = userData[1];
        this.username = userData[2];
    }
    
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
    
    public int getGP(){
        return SQLCore.getGP(userID);
    }
    
    public void updateGP(int gamePoints){
        int currentGP = SQLCore.getGP(userID);
        currentGP += gamePoints;
        
        SQLCore.setGP(userID, currentGP);
    }
    
    
    /*
    Keeps the User object up to date
    */
    public void refresh(){
        username = SQLCore.getUsername(userID);
        nickname = SQLCore.getNickname(userID);
    }
    
}
