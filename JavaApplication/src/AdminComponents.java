
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
    
    
    /**
        Create User Components
    */
    public final static JButton BUTTON_ADD_USER = new JButton("Add User");
    public final JTextField TEXTFIELD_USER = new JTextField("", 12);
    public final JTextField TEXTFIELD_PASS = new JTextField("", 12);
    public final JPasswordField PASSFIELD_USER = new JPasswordField("", 12);
    public final JPasswordField PASSFIELD_USER_CONFIRM = new JPasswordField("", 12);
    public final static JLabel LABEL_CREATE_USER = new JLabel("Create User:");
    public final static JLabel LABEL_USER = new JLabel("Username", JLabel.CENTER);
    public final static JLabel LABEL_PASS = new JLabel("Password", JLabel.CENTER);
    public final static JCheckBox IS_VISIBLE = new JCheckBox("Visible", false);
}
