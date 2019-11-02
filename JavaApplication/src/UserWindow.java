/*
 * ADD INPUT VALIDATION
 */

/**
 *
 * @author raito
 */



import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserWindow extends JFrame 
        implements UserComponents, ItemListener, ActionListener, KeyListener{
    
    private static boolean IS_CHECKED = false;
    private static User m_user;
    private static Component panelChangeProfile;
    private static Component panelLogout;
    private static Component panelPlayGacha;
    private static boolean m_bpanelChangeProfileHasOperation = false;
    private static boolean m_bpanelPlayGachaHasOperation = false;
    
    private static final GachaBox box = new GachaBox();
    
    public UserWindow(User user){
        UserWindow.m_user = user;
        
        super.setTitle("User Window");
        super.setLayout(new GridBagLayout());
        super.setResizable(false);        
        
        initiateComponents();
                
        super.pack();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initiateComponents(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        //bottom, left, right, top
        gbc.insets = new Insets(10 , 10, 10, 10);
        
        if((panelChangeProfile = initiateChangeProfile()) != null){
            gbc.gridx = 1;
            gbc.gridy = 0;
            super.add(panelChangeProfile = initiateChangeProfile(), gbc);
        }
        
        if((panelPlayGacha = inititatePlayGacha()) != null){
            gbc.gridx = 0;
            gbc.gridy = 0;
            super.add(panelPlayGacha, gbc);
        }
        
        if((panelLogout = initiateLogout()) != null){
            gbc.gridx = 0;
            gbc.gridy = 1;
            super.add(panelLogout, gbc);
        }
        
        loadListeners(true);
    }
    
    private void loadListeners(boolean c){
        if(c){
            CHANGE_PROFILE_IS_VISIBLE.addItemListener(this);
            CHANGE_PROFILE_BUTTON_APPLY.addActionListener(this);
            PLAY_GACHA_BUTTON_ROLL.addActionListener(this);
            PLAY_GACHA_BUTTON_BACK.addActionListener(this);
            BUTTON_CHANGE_PROFILE.addActionListener(this);
            BUTTON_PLAY_GACHA.addActionListener(this);
            BUTTON_LOGOUT.addActionListener(this);
            if(!IS_CHECKED){
                CHANGE_PROFILE_PASSFIELD_USER.addKeyListener(this);
                CHANGE_PROFILE_PASSFIELD_USER_CONFIRM.addKeyListener(this);
            } else {
                CHANGE_PROFILE_TEXTFIELD_PASS.addKeyListener(this);
            }
        }else{
            CHANGE_PROFILE_IS_VISIBLE.removeItemListener(this);
            CHANGE_PROFILE_BUTTON_APPLY.removeActionListener(this);
            PLAY_GACHA_BUTTON_ROLL.removeActionListener(this);
            PLAY_GACHA_BUTTON_BACK.removeActionListener(this);
            BUTTON_CHANGE_PROFILE.removeActionListener(this);
            BUTTON_LOGOUT.removeActionListener(this);
            BUTTON_PLAY_GACHA.removeActionListener(this);
            if(!IS_CHECKED){
                CHANGE_PROFILE_PASSFIELD_USER.removeKeyListener(this);
                CHANGE_PROFILE_PASSFIELD_USER_CONFIRM.removeKeyListener(this);
            } else {
                CHANGE_PROFILE_TEXTFIELD_PASS.removeKeyListener(this);
            }
        }
    }
    
    
    private void removeComponents(){
        if(panelChangeProfile != null){
            super.remove(panelChangeProfile);
        }
        if(panelPlayGacha != null){
            super.remove(panelPlayGacha);
        }
        if(panelLogout != null){
            super.remove(panelLogout);
        }
        loadListeners(false);
    }
    
    private void refreshFrame(){
        removeComponents();
        initiateComponents();
        super.pack();
    }
    
    private Component initiateLogout(){
        if(!(m_bpanelChangeProfileHasOperation ||
                m_bpanelPlayGachaHasOperation
                )){
            return BUTTON_LOGOUT;
        }else {
            return null;
        }
    }
    
    private Component inititatePlayGacha(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel userLayout = new JPanel(new GridBagLayout());
        if(!(m_bpanelChangeProfileHasOperation) &&
                (m_bpanelPlayGachaHasOperation)){
            
            // 1st column
            
            gbc.insets = new Insets(5,5,5,5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            userLayout.add(PLAY_GACHA_LABEL_TITLE,gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 1;
            PLAY_GACHA_TEXTFIELD_ITEM.setEditable(false);
            userLayout.add(PLAY_GACHA_TEXTFIELD_ITEM,gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 2;
            userLayout.add(PLAY_GACHA_BUTTON_ROLL,gbc);
            
            // 2nd column
            
            gbc.gridx = 1;
            gbc.gridy = 0;
            userLayout.add(PLAY_GACHA_LABEL_GAMEPOINTS,gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 1;
            PLAY_GACHA_LABEL_GP_VALUE.setText(new Integer(m_user.getGP()).toString());
            userLayout.add(PLAY_GACHA_LABEL_GP_VALUE,gbc);
            
            
            gbc.gridx = 1;
            gbc.gridy = 2;
            userLayout.add(PLAY_GACHA_BUTTON_BACK,gbc);
            
            
        } else {
            if(!(m_bpanelChangeProfileHasOperation ||
                m_bpanelPlayGachaHasOperation
                )){
               return BUTTON_PLAY_GACHA;
            }else {
                return null;
            }
        }
        return userLayout;
    }
    
    private Component initiateChangeProfile(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel userLayout = new JPanel(new GridBagLayout());
        /** 
            Just wanting to have it a body o/
        */
        if(m_bpanelChangeProfileHasOperation &&
                !(m_bpanelPlayGachaHasOperation)){
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //bottom, left, right, top
            gbc.insets = new Insets(5,0,0,0);
            gbc.gridx = 0;
            gbc.gridy = 0;
            userLayout.add(CHANGE_PROFILE_LABEL_CREATE_USER, gbc);

            
            gbc.gridx = 0;  
            gbc.gridy = 1;  
            userLayout.add(CHANGE_PROFILE_LABEL_USER, gbc);

            gbc.ipady = 0;
            gbc.gridx = 1;  
            gbc.gridy = 1;  
            CHANGE_PROFILE_TEXTFIELD_USER.setText(SQLCore.getUsername(m_user.getUID()));
            userLayout.add(CHANGE_PROFILE_TEXTFIELD_USER, gbc);
            
            gbc.ipady = 5;
            gbc.gridx = 0;
            gbc.gridy = 2;
            userLayout.add(CHANGE_PROFILE_LABEL_NICKNAME, gbc);
            
            gbc.ipady = 0;
            gbc.gridx = 1;
            gbc.gridy = 2;
            CHANGE_PROFILE_TEXTFIELD_NICKNAME.setText(SQLCore.getNickname(m_user.getUID()));
            userLayout.add(CHANGE_PROFILE_TEXTFIELD_NICKNAME,gbc);

            gbc.ipady = 5;
            gbc.gridx = 0;  
            gbc.gridy = 3;
            userLayout.add(CHANGE_PROFILE_LABEL_PASS, gbc);

            if(!IS_CHECKED){
                gbc.ipady = 0;
                gbc.gridx = 1;  
                gbc.gridy = 3;  
                userLayout.add(CHANGE_PROFILE_PASSFIELD_USER, gbc);

                gbc.ipady = 0;
                gbc.gridx = 1;
                gbc.gridy = 4;
                userLayout.add(CHANGE_PROFILE_PASSFIELD_USER_CONFIRM, gbc);
            } else {
                gbc.ipady = 0;
                gbc.gridx = 1;  
                gbc.gridy = 3;  
                userLayout.add(CHANGE_PROFILE_TEXTFIELD_PASS, gbc);
            }

            gbc.ipady = 0;
            gbc.gridx = 2;
            gbc.gridy = 3;
            userLayout.add(CHANGE_PROFILE_IS_VISIBLE, gbc);
            
            gbc.ipady = 0;
            gbc.gridwidth = 10;
            gbc.gridx = 0;
            gbc.gridy = (IS_CHECKED ? 4 : 5 );
            userLayout.add(CHANGE_PROFILE_BUTTON_APPLY, gbc);
        } else {
            if(!(m_bpanelChangeProfileHasOperation ||
                m_bpanelPlayGachaHasOperation
                )){
               return BUTTON_CHANGE_PROFILE;
            }else {
                return null;
            }
        }
        return userLayout;
    }
    
    private void loadLoginWindow(){
        LoginWindow w = new LoginWindow();
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == CHANGE_PROFILE_IS_VISIBLE){
            if(e.getStateChange() == 1){
                IS_CHECKED = true;
                refreshFrame();
            } else {
                IS_CHECKED = false;
                refreshFrame();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Main Frame buttons
        if(e.getSource() == BUTTON_CHANGE_PROFILE){
            m_bpanelChangeProfileHasOperation = true;
            m_bpanelPlayGachaHasOperation = false;
            BUTTON_CHANGE_PROFILE.removeActionListener(this);
            refreshFrame();
            return;
        }
        if(e.getSource() == BUTTON_PLAY_GACHA){
            m_bpanelChangeProfileHasOperation = false;
            m_bpanelPlayGachaHasOperation = true;
            BUTTON_PLAY_GACHA.removeActionListener(this);
            refreshFrame();
            return;
        }
        
        if(e.getSource() == BUTTON_LOGOUT){
            int c = JOptionPane.showConfirmDialog(rootPane, 
                    "Are you sure you want to logout?", 
                    "Logout",
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);
            if(c == JOptionPane.OK_OPTION){
                loadListeners(false);
                loadLoginWindow();
                this.dispose();
            }
        }
        
        // Panel buttons
        
        if(e.getSource() == CHANGE_PROFILE_BUTTON_APPLY){
            changeProfileApply();
            
            if((IS_CHECKED ? !CHANGE_PROFILE_TEXTFIELD_PASS.getText().isEmpty() 
                    : CHANGE_PROFILE_PASSFIELD_USER_CONFIRM.getPassword().length != 0)){
                
            }
            m_bpanelChangeProfileHasOperation = false;
            m_bpanelPlayGachaHasOperation = false;
            CHANGE_PROFILE_BUTTON_APPLY.removeActionListener(this);
            refreshFrame();
            return;
        }
        
        if(e.getSource() == PLAY_GACHA_BUTTON_ROLL){
            System.out.println("Rolling rollers");
            gachaRoll();
        }
        
        if(e.getSource() == PLAY_GACHA_BUTTON_BACK){
            System.out.println("Backityback");
            m_bpanelChangeProfileHasOperation = false;
            m_bpanelPlayGachaHasOperation = false;
            PLAY_GACHA_BUTTON_BACK.removeActionListener(this);
            refreshFrame();
            return;
        }
        
        
    }
    
    public void changeProfileApply(){
        
        if(!CHANGE_PROFILE_TEXTFIELD_USER.getText().isEmpty())
            SQLCore.setUsername(m_user.getUID(), CHANGE_PROFILE_TEXTFIELD_USER.getText());
        
        if(!CHANGE_PROFILE_TEXTFIELD_NICKNAME.getText().isEmpty())
            SQLCore.setNickname(m_user.getUID(), CHANGE_PROFILE_TEXTFIELD_NICKNAME.getText());
        
        String newPass = SQLCore.getPassword(m_user.getUID());
        if(IS_CHECKED){
            if(!CHANGE_PROFILE_TEXTFIELD_PASS.getText().isEmpty())
                newPass = CHANGE_PROFILE_TEXTFIELD_PASS.getText();
        }
        else{
            if(0 != CHANGE_PROFILE_PASSFIELD_USER.getPassword().length)
                newPass = array2String(CHANGE_PROFILE_PASSFIELD_USER.getPassword());
        }
        
        SQLCore.setPassword(m_user.getUID(), newPass);
    }
    
    public String array2String(char[] array){
        String string = "";
        for(char x:array){
            string+=x;
        }
        
        return string;
        
    }
    
    public void gachaRoll(){
        GachaItem get = box.roll();
        
        PLAY_GACHA_TEXTFIELD_ITEM.setText(get.getName());
        
        SQLCore.itemOwn(m_user.getUID(), get);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
    public static void main(String args[]){
        UserWindow x = new UserWindow(new User(2));
    }
}

