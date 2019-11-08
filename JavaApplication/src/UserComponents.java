
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aevan
 */
public interface UserComponents {
    public final static JButton BUTTON_LOGOUT = new JButton("Logout");
    public final static JButton BUTTON_CHANGE_PROFILE = new JButton("Change Profile");
    public final static JButton BUTTON_PLAY_GACHA = new JButton("Play Gacha");
    public final static JButton BUTTON_INVENTORY = new JButton("Inventory");
    public final static JButton BUTTON_SETTINGS = new JButton("Settings");
    
    
    /**
        Change Profile Components
    */
    public final static JButton CHANGE_PROFILE_BUTTON_APPLY = new JButton("Apply Changes");
    public final JTextField CHANGE_PROFILE_TEXTFIELD_USER = new JTextField("", 12);
    public final JTextField CHANGE_PROFILE_TEXTFIELD_NICKNAME = new JTextField("",12);
    public final JTextField CHANGE_PROFILE_TEXTFIELD_PASS = new JTextField("", 12);
    public final JPasswordField CHANGE_PROFILE_PASSFIELD_USER = new JPasswordField("", 12);
    public final JPasswordField CHANGE_PROFILE_PASSFIELD_USER_CONFIRM = new JPasswordField("", 12);
    public final static JLabel CHANGE_PROFILE_LABEL_CREATE_USER = new JLabel("Change Profile:");
    public final static JLabel CHANGE_PROFILE_LABEL_USER = new JLabel("Username", JLabel.CENTER);
    public final static JLabel CHANGE_PROFILE_LABEL_NICKNAME = new JLabel("Nickname", JLabel.CENTER);
    public final static JLabel CHANGE_PROFILE_LABEL_PASS = new JLabel("Password", JLabel.CENTER);
    public final static JCheckBox CHANGE_PROFILE_IS_VISIBLE = new JCheckBox("Visible", false);
    
    /*
        Play Gacha Components
    */
    
    public final static JLabel PLAY_GACHA_LABEL_TITLE = new JLabel("Gachapon",JLabel.CENTER);
    public final static JLabel PLAY_GACHA_LABEL_GAMEPOINTS = new JLabel("GP: ",JLabel.CENTER);
    public final static JButton PLAY_GACHA_BUTTON_ROLL = new JButton("ROLL");
    public final static JButton PLAY_GACHA_BUTTON_BACK = new JButton("BACK");
    public final JTextField PLAY_GACHA_TEXTFIELD_ITEM = new JTextField("",10);
    
    /*
        Show Items Components
    */
    public final static JLabel INVENTORY_LABEL_INVENTORY = new JLabel("Inventory",JLabel.CENTER);
    public final static JButton INVENTORY_BUTTON_SELL = new JButton("SELL");
    public final static JButton INVENTORY_BUTTON_BACK = new JButton("BACK");
    public static final JList<String> INVENTORY_LIST_ITEMS = new JList<>();
    public static final JScrollPane INVENTORY_LIST_SCROLL = new JScrollPane(INVENTORY_LIST_ITEMS);
    
    /*
        Settings Components
    */
    public final static JLabel SETTINGS_LABEL_SETTINGS = new JLabel("Settings", JLabel.CENTER);
    public final static JComboBox SETTINGS_CB_COLOR = new JComboBox();
    public final static JComboBox SETTINGS_CB_FONT = new JComboBox();
    public final static JButton SETTINGS_BUTTON_BACK = new JButton("Back");
    public final static JButton SETTINGS_BUTTON_APPLY = new JButton("Apply");
    
    
}
