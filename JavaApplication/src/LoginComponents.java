
import javax.swing.JButton;
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
public interface LoginComponents {
    public final static SQLCore SQL_CONN = new SQLCore();
    public final static JLabel LABEL_USER = new JLabel("Username", JLabel.CENTER);
    public final static JLabel LABEL_PASS = new JLabel("Password", JLabel.CENTER);
    public final static JButton BUTTON_LOGIN = new JButton("Login");
    public final static JButton BUTTON_CANCEL = new JButton("Cancel");
    public final JTextField TEXTFIELD_USER = new JTextField();
    public final JPasswordField PASSFIELD_PASS = new JPasswordField();
}
