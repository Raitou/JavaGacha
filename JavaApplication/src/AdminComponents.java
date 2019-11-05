
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
    public final static JLabel EDIT_USER_LABEL_EDIT_USER = new JLabel("Edit User:");
    public final static JLabel EDIT_USER_LABEL_SEARCH_USER = new JLabel("Search User", JLabel.CENTER);
    public final static JButton EDIT_USER_BUTTON_SEARCH_USER = new JButton("Search");
    public final static JButton EDIT_USER_BUTTON_UPDATE_USER = new JButton("Update");
    public final static JButton EDIT_USER_BUTTON_MINIMIZE = new JButton("Minimize");
    public final JTextField EDIT_USER_TEXTFIELD_SEARCH_USER = new JTextField("", 12);
    public final static JLabel EDIT_USER_LABEL_ACCESS = new JLabel("Access:", JLabel.CENTER);
    public final static JLabel EDIT_USER_LABEL_NICK = new JLabel("Nickname:", JLabel.CENTER);
    public final static JLabel EDIT_USER_LABEL_PASS = new JLabel("Password:", JLabel.CENTER);
    public final JComboBox EDIT_USER_COMBOBOX_ACCESS = new JComboBox();
    public final JTextField EDIT_USER_TEXTFIELD_NICK = new JTextField("", 12);
    public final JPasswordField EDIT_USER_PASSFIELD_USER = new JPasswordField("", 12);
    public final JPasswordField EDIT_USER_PASSFIELD_USER_CONFIRM = new JPasswordField("", 12);
    
    /**
        Item Manager Components
    */
    public final static String[] COLUMN_NAMES = {  };
    public final static JTable ITEM_LIST = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column){
            return column != 0;
        }
    };
    public final static JScrollPane ITEM_LIST_SCROLL = new JScrollPane(ITEM_LIST);
}
