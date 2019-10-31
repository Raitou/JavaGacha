
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raito
 */
public interface AdminComponents {
    
    public final static JButton BUTTON_LOGOUT = new JButton("Logout");
    public final static JButton BUTTON_CREATE_USER = new JButton("Create User");
    public final static JButton BUTTON_EDIT_USER = new JButton("Edit User");
    public final static JButton BUTTON_ITEM_MANAGER = new JButton("Item Manager");
    
    
    /**
        Create User Components
    */
    public final static JButton CREATE_USER_BUTTON_ADD_USER = new JButton("Add User / Minimize");
    public final JTextField CREATE_USER_TEXTFIELD_USER = new JTextField("", 12);
    public final JTextField CREATE_USER_TEXTFIELD_PASS = new JTextField("", 12);
    public final JPasswordField CREATE_USER_PASSFIELD_USER = new JPasswordField("", 12);
    public final JPasswordField CREATE_USER_PASSFIELD_USER_CONFIRM = new JPasswordField("", 12);
    public final static JLabel CREATE_USER_LABEL_CREATE_USER = new JLabel("Create User:");
    public final static JLabel CREATE_USER_LABEL_USER = new JLabel("Username", JLabel.CENTER);
    public final static JLabel CREATE_USER_LABEL_PASS = new JLabel("Password", JLabel.CENTER);
    public final static JCheckBox CREATE_USER_IS_VISIBLE = new JCheckBox("Visible", false);
    
    /**
        Edit User Components
    */
    public final static JLabel LABEL_EDIT_USER = new JLabel("Edit User:");
    public final static JLabel LABEL_SEARCH_USER = new JLabel("Search User", JLabel.CENTER);
    public final static JButton BUTTON_SEARCH_USER = new JButton("Search");
    
}
