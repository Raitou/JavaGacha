/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raito
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginWindow extends JFrame 
        implements ActionListener, KeyListener, AuthLevel, LoginComponents {
    
    
    private static User m_user;
    
    public LoginWindow(){
        super.setTitle("Login");
        super.setLayout(new GridLayout(3, 2));
        super.setResizable(false);
        
        super.add(LABEL_USER); super.add(TEXTFIELD_USER);
        super.add(LABEL_PASS); super.add(PASSFIELD_PASS);
        super.add(BUTTON_LOGIN); super.add(BUTTON_CANCEL);
        
        super.setSize(300, 150);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        
        loadListeners(true);
    }
    
    /*
    Adds all needed Listeners if parameter is true
    Removes all listeners if parameter is false;
    */
    private void loadListeners(boolean c){
        if(c){
            TEXTFIELD_USER.addKeyListener(this);
            PASSFIELD_PASS.addKeyListener(this);
            BUTTON_LOGIN.addActionListener(this);
            BUTTON_CANCEL.addActionListener(this);
        } else {
            TEXTFIELD_USER.removeKeyListener(this);
            PASSFIELD_PASS.removeKeyListener(this);
            BUTTON_LOGIN.removeActionListener(this);
            BUTTON_CANCEL.removeActionListener(this);
        }
    }
    
    /*
    returns true if the account in the parameters exists in the database
    takes username and password as parameters
    also instantiates m_user to the user details provided
    */
    
    private boolean isAccountExists(String user, String pass){
        String userData[] = SQLCore.getLogin(user, pass);
        if(userData != null){
            m_user = new User(user, pass);
            return true;
            
        } else {
            m_user = null; 
           return false;
        }
    }
    
    /*
    loads the new window depending on the authentication level
    AdminWindow if user is admin
    UserWindow if user is regular
    LoginWindow is disposed if user is banned
    */
    public void loadNewWindow(){
        loadListeners(false);
        switch(SQLCore.getUserAuth(m_user.getUID())){
            case ADMIN_USER:
                AdminWindow adminWin = new AdminWindow(m_user);
                break;
            case NORMAL_USER:
                UserWindow userWin = new UserWindow(m_user);
                break;
            case BANNED_USER:
                JOptionPane.showMessageDialog(rootPane,
                        "This user account is blocked!",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
                break;
            default:
                UserWindow userWinDef = new UserWindow(m_user);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == BUTTON_CANCEL){
            int c = JOptionPane.showConfirmDialog(rootPane, 
                    "Are you sure you want to exit?", "Exit", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(c == JOptionPane.OK_OPTION){
                    this.dispose();
            }
            return;
        }
        if(e.getSource() == BUTTON_LOGIN){
            if(isAccountExists(TEXTFIELD_USER.getText(), 
                    String.copyValueOf(PASSFIELD_PASS.getPassword()))){
                JOptionPane.showMessageDialog(rootPane,
                        "Welcome " + m_user.getNickname() + "!",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
                TEXTFIELD_USER.setText("");
                PASSFIELD_PASS.setText("");
                loadNewWindow();
                this.dispose();
                
            } else {
                JOptionPane.showMessageDialog(rootPane,
                        "User not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            BUTTON_LOGIN.doClick();
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            BUTTON_CANCEL.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
