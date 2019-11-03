/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminWindow extends JFrame 
        implements AdminComponents, ItemListener, ActionListener, KeyListener{
    
    private static boolean CREATE_USER_IS_CHECKED = false;
    private static String m_strUser;
    private static int m_intUID;
    private static User m_user;
    private static Component panelUserAdd;
    private static Component panelLogout;
    private static Component panelUserEdit;
    private static Component panelItem;
    private static boolean m_bpanelUserAddHasOperation = false;
    private static boolean m_bpanelUserEditHasOperation = false;
    
    public AdminWindow(User user){
        AdminWindow.m_user = user;
        
        super.setTitle("Admin Window");
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
        
        if((panelUserAdd = initiateCreateUser()) != null){
            gbc.gridx = 0;
            gbc.gridy = 0;
            super.add(panelUserAdd = initiateCreateUser(), gbc);
        }
        
        if((panelUserEdit = inititateEditUser()) != null){
            gbc.gridx = 1;
            gbc.gridy = 0;
            super.add(panelUserEdit, gbc);
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
            CREATE_USER_IS_VISIBLE.addItemListener(this);
            CREATE_USER_BUTTON_ADD_USER.addActionListener(this);
            BUTTON_CREATE_USER.addActionListener(this);
            BUTTON_EDIT_USER.addActionListener(this);
            BUTTON_LOGOUT.addActionListener(this);
            EDIT_USER_BUTTON_MINIMIZE.addActionListener(this);
            EDIT_USER_BUTTON_SEARCH_USER.addActionListener(this);
            if(!CREATE_USER_IS_CHECKED){
                CREATE_USER_PASSFIELD_USER.addKeyListener(this);
                CREATE_USER_PASSFIELD_USER_CONFIRM.addKeyListener(this);
            } else {
                CREATE_USER_TEXTFIELD_PASS.addKeyListener(this);
            }
        }else{
            CREATE_USER_IS_VISIBLE.removeItemListener(this);
            CREATE_USER_BUTTON_ADD_USER.removeActionListener(this);
            BUTTON_CREATE_USER.removeActionListener(this);
            BUTTON_LOGOUT.removeActionListener(this);
            BUTTON_EDIT_USER.removeActionListener(this);
            EDIT_USER_BUTTON_MINIMIZE.removeActionListener(this);
            EDIT_USER_BUTTON_SEARCH_USER.removeActionListener(this);
            if(!CREATE_USER_IS_CHECKED){
                CREATE_USER_PASSFIELD_USER.removeKeyListener(this);
                CREATE_USER_PASSFIELD_USER_CONFIRM.removeKeyListener(this);
            } else {
                CREATE_USER_TEXTFIELD_PASS.removeKeyListener(this);
            }
        }
    }
        
    private void removeComponents(){
        if(panelUserAdd != null){
            super.remove(panelUserAdd);
        }
        if(panelUserEdit != null){
            super.remove(panelUserEdit);
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
        if(!(m_bpanelUserAddHasOperation ||
                m_bpanelUserEditHasOperation
                )){
            return BUTTON_LOGOUT;
        }else {
            return null;
        }
    }
    
    private Component inititateEditUser(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel editUserLayout = new JPanel(new GridBagLayout());
        if(!(m_bpanelUserAddHasOperation) &&
                (m_bpanelUserEditHasOperation)){
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //bottom, left, right, top
            gbc.insets = new Insets(5,0,0,0);
            gbc.gridx = 0;
            gbc.gridy = 0;
            editUserLayout.add(EDIT_USER_LABEL_EDIT_USER, gbc);
            
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = 1;
            editUserLayout.add(EDIT_USER_LABEL_SEARCH_USER, gbc);
            
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 1;
            gbc.gridy = 1;
            editUserLayout.add(EDIT_USER_TEXTFIELD_SEARCH_USER, gbc);
            
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 2;
            gbc.gridy = 1;
            editUserLayout.add(EDIT_USER_BUTTON_SEARCH_USER, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 2;
            editUserLayout.add(EDIT_USER_LABEL_NICK, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 2;
            editUserLayout.add(EDIT_USER_TEXTFIELD_NICK, gbc);
            EDIT_USER_TEXTFIELD_NICK.setEditable(false);
            
            gbc.gridx = 2;
            gbc.gridy = 2;
            editUserLayout.add(EDIT_USER_LABEL_ACCESS, gbc);
            
            gbc.gridx = 2;
            gbc.gridy = 3;
            editUserLayout.add(EDIT_USER_COMBOBOX_ACCESS, gbc);
            EDIT_USER_COMBOBOX_ACCESS.setEnabled(false);
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            editUserLayout.add(EDIT_USER_LABEL_PASS, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 3;
            editUserLayout.add(EDIT_USER_PASSFIELD_USER, gbc);
            EDIT_USER_PASSFIELD_USER.setEditable(false);
            
            gbc.gridx = 1;
            gbc.gridy = 4;
            editUserLayout.add(EDIT_USER_PASSFIELD_USER_CONFIRM, gbc);
            EDIT_USER_PASSFIELD_USER_CONFIRM.setEditable(false);
            
            gbc.gridx = 1;
            gbc.gridy = 5;
            editUserLayout.add(EDIT_USER_BUTTON_UPDATE_USER, gbc);
            EDIT_USER_BUTTON_UPDATE_USER.setEnabled(false);
            
            gbc.gridx = 2;
            gbc.gridy = 5;
            editUserLayout.add(EDIT_USER_BUTTON_MINIMIZE, gbc);
            
        } else {
            if(!(m_bpanelUserAddHasOperation ||
                m_bpanelUserEditHasOperation
                )){
               return BUTTON_EDIT_USER;
            }else {
                return null;
            }
        }
        return editUserLayout;
    }
    
    private Component initiateCreateUser(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel userLayout = new JPanel(new GridBagLayout());
        /** 
            Just wanting to have it a body o/
        */
        if(m_bpanelUserAddHasOperation &&
                !(m_bpanelUserEditHasOperation)){
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //bottom, left, right, top
            gbc.insets = new Insets(5,0,0,0);
            gbc.gridx = 0;
            gbc.gridy = 0;
            userLayout.add(CREATE_USER_LABEL_CREATE_USER, gbc);

            
            gbc.gridx = 0;  
            gbc.gridy = 1;  
            userLayout.add(CREATE_USER_LABEL_USER, gbc);

            gbc.ipady = 0;
            gbc.gridx = 1;  
            gbc.gridy = 1;  
            userLayout.add(CREATE_USER_TEXTFIELD_USER, gbc);

            gbc.ipady = 5;
            gbc.gridx = 0;  
            gbc.gridy = 2;
            userLayout.add(CREATE_USER_LABEL_PASS, gbc);

            if(!CREATE_USER_IS_CHECKED){
                gbc.ipady = 0;
                gbc.gridx = 1;  
                gbc.gridy = 2;  
                userLayout.add(CREATE_USER_PASSFIELD_USER, gbc);

                gbc.ipady = 0;
                gbc.gridx = 1;
                gbc.gridy = 3;
                userLayout.add(CREATE_USER_PASSFIELD_USER_CONFIRM, gbc);
            } else {
                gbc.ipady = 0;
                gbc.gridx = 1;  
                gbc.gridy = 2;  
                userLayout.add(CREATE_USER_TEXTFIELD_PASS, gbc);
            }

            gbc.ipady = 0;
            gbc.gridx = 2;
            gbc.gridy = 2;
            userLayout.add(CREATE_USER_IS_VISIBLE, gbc);
            
            gbc.ipady = 0;
            gbc.gridwidth = 10;
            gbc.gridx = 0;
            gbc.gridy = (CREATE_USER_IS_CHECKED ? 3 : 4 );
            userLayout.add(CREATE_USER_BUTTON_ADD_USER, gbc);
        } else {
            if(!(m_bpanelUserAddHasOperation ||
                m_bpanelUserEditHasOperation
                )){
               return BUTTON_CREATE_USER;
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
        if(e.getSource() == CREATE_USER_IS_VISIBLE){
            if(e.getStateChange() == 1){
                CREATE_USER_IS_CHECKED = true;
                refreshFrame();
            } else {
                CREATE_USER_IS_CHECKED = false;
                refreshFrame();
            }
        }
    }
    
    private void buttonCreateUser(){
        m_bpanelUserAddHasOperation = true;
        m_bpanelUserEditHasOperation = false;
        BUTTON_CREATE_USER.removeActionListener(this);
        refreshFrame();
    }
    
    private void buttonEditUser(){
        m_bpanelUserAddHasOperation = false;
        m_bpanelUserEditHasOperation = true;
        BUTTON_EDIT_USER.removeActionListener(this);
        refreshFrame();
    }
    
    private void editUserButtonMinimize(){
        m_bpanelUserAddHasOperation = false;
        m_bpanelUserEditHasOperation = false;
        EDIT_USER_BUTTON_MINIMIZE.removeActionListener(this);
        refreshFrame();
    }
    
    private void createUserButtonAddUser(){
        if((CREATE_USER_IS_CHECKED ? !CREATE_USER_TEXTFIELD_PASS.getText().isEmpty() 
                    : CREATE_USER_PASSFIELD_USER_CONFIRM.getPassword().length != 0)){
            if(!(new String(CREATE_USER_PASSFIELD_USER_CONFIRM.getPassword()).equals(
                    new String(CREATE_USER_PASSFIELD_USER.getPassword()))
                    )){
                JOptionPane.showMessageDialog(panelLogout, 
                        "Password and Confirm Password is not equal", 
                        "Warning", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            
            if(!SQLCore.addData(
                    CREATE_USER_TEXTFIELD_USER.getText(), 
                    (
                        CREATE_USER_IS_CHECKED ? 
                                CREATE_USER_TEXTFIELD_PASS.getText() :
                                new String(CREATE_USER_PASSFIELD_USER_CONFIRM.getPassword()
                                )
                            ),
                    CREATE_USER_TEXTFIELD_USER.getText()
            )){
                JOptionPane.showMessageDialog(panelLogout, 
                        "Data not sucesfully added due to connection error! or duplication of data", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(panelLogout, 
                        "Data succesfully added!", 
                        "Information", 
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
        CREATE_USER_TEXTFIELD_USER.setText("");
        CREATE_USER_PASSFIELD_USER_CONFIRM.setText("");
        CREATE_USER_PASSFIELD_USER.setText("");
        CREATE_USER_TEXTFIELD_PASS.setText("");
        m_bpanelUserAddHasOperation = false;
        m_bpanelUserEditHasOperation = false;
        CREATE_USER_BUTTON_ADD_USER.removeActionListener(this);
        refreshFrame();
    }
    
    private void editUserButtonSearchUser() {
        if(EDIT_USER_TEXTFIELD_SEARCH_USER.getText().isEmpty()){
            return;
        }
        String arr[] = SQLCore.searchUserInformation(EDIT_USER_TEXTFIELD_SEARCH_USER.getText());
        if(arr == null){
            return;
        }
        EDIT_USER_TEXTFIELD_NICK.setText(arr[0]);
        EDIT_USER_PASSFIELD_USER.setText("********");
        EDIT_USER_PASSFIELD_USER_CONFIRM.setText("********");
        EDIT_USER_TEXTFIELD_NICK.setEditable(true);
        EDIT_USER_PASSFIELD_USER.setEditable(true);
        EDIT_USER_PASSFIELD_USER_CONFIRM.setEditable(true);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BUTTON_CREATE_USER){
            buttonCreateUser();
            return;
        }
        if(e.getSource() == BUTTON_EDIT_USER){
            buttonEditUser();
            return;
        }
        if(e.getSource() == EDIT_USER_BUTTON_MINIMIZE){
            editUserButtonMinimize();
            return;
        }
        if(e.getSource() == CREATE_USER_BUTTON_ADD_USER){
            createUserButtonAddUser();
            return;
        }
        if(e.getSource() == EDIT_USER_BUTTON_SEARCH_USER){
            editUserButtonSearchUser();
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
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
