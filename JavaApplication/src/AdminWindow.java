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
    
    private static boolean IS_CHECKED = false;
    private static String m_strUser;
    private static int m_intUID;
    private static Component userPanel;
    private static Component logoutPanel;
    private static boolean m_bHasOperation = false;
    
    public AdminWindow(String user, int userID){
        AdminWindow.m_strUser = user;
        AdminWindow.m_intUID = userID;
        
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
        gbc.gridx = 0;
        gbc.gridy = 0;
        super.add(userPanel = initiateCreateUser(), gbc);
        if((logoutPanel = initiateLogout()) != null){
            gbc.gridx = 1;
            gbc.gridy = 0;
            super.add(logoutPanel, gbc);
        }

        loadListeners(true);
    }
    
    private void loadListeners(boolean c){
        if(c){
            IS_VISIBLE.addItemListener(this);
            (m_bHasOperation ? BUTTON_ADD_USER : BUTTON_CREATE_USER).addActionListener(this);
            BUTTON_LOGOUT.addActionListener(this);
            if(!IS_CHECKED){
                PASSFIELD_USER.addKeyListener(this);
                PASSFIELD_USER_CONFIRM.addKeyListener(this);
            } else {
                TEXTFIELD_PASS.addKeyListener(this);
            }
        }else{
            IS_VISIBLE.removeItemListener(this);
            BUTTON_ADD_USER.removeActionListener(this);
            BUTTON_CREATE_USER.removeActionListener(this);
            BUTTON_LOGOUT.removeActionListener(this);
            if(!IS_CHECKED){
                PASSFIELD_USER.removeKeyListener(this);
                PASSFIELD_USER_CONFIRM.removeKeyListener(this);
            } else {
                TEXTFIELD_PASS.removeKeyListener(this);
            }
        }
    }
    
    
    private void removeComponents(){
        super.remove(userPanel);
        if(logoutPanel != null){
            super.remove(logoutPanel);
        }
        loadListeners(false);
    }
    
    private void refreshFrame(){
        removeComponents();
        initiateComponents();
        super.pack();
    }
    
    private Component initiateLogout(){
        if(!m_bHasOperation){
            return BUTTON_LOGOUT;
        }else {
            return null;
        }
    }
    
    private Component initiateCreateUser(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel userLayout = new JPanel(new GridBagLayout());
        /** 
            Just wanting to have it a body o/
        */
        if(m_bHasOperation){
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //bottom, left, right, top
            gbc.insets = new Insets(5,0,0,0);
            gbc.gridx = 0;
            gbc.gridy = 0;
            userLayout.add(LABEL_CREATE_USER, gbc);

            
            gbc.gridx = 0;  
            gbc.gridy = 1;  
            userLayout.add(LABEL_USER, gbc);

            gbc.ipady = 0;
            gbc.gridx = 1;  
            gbc.gridy = 1;  
            userLayout.add(TEXTFIELD_USER, gbc);

            gbc.ipady = 5;
            gbc.gridx = 0;  
            gbc.gridy = 2;
            userLayout.add(LABEL_PASS, gbc);

            if(!IS_CHECKED){
                gbc.ipady = 0;
                gbc.gridx = 1;  
                gbc.gridy = 2;  
                userLayout.add(PASSFIELD_USER, gbc);

                gbc.ipady = 0;
                gbc.gridx = 1;
                gbc.gridy = 3;
                userLayout.add(PASSFIELD_USER_CONFIRM, gbc);
            } else {
                gbc.ipady = 0;
                gbc.gridx = 1;  
                gbc.gridy = 2;  
                userLayout.add(TEXTFIELD_PASS, gbc);
            }

            gbc.ipady = 0;
            gbc.gridx = 2;
            gbc.gridy = 2;
            userLayout.add(IS_VISIBLE, gbc);
            
            gbc.ipady = 0;
            gbc.gridwidth = 10;
            gbc.gridx = 0;
            gbc.gridy = (IS_CHECKED ? 3 : 4 );
            userLayout.add(BUTTON_ADD_USER, gbc);
        } else {
            return BUTTON_CREATE_USER;
        }
        
        
        return userLayout;
    }
    
    private void loadLoginWindow(){
        LoginWindow w = new LoginWindow();
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == IS_VISIBLE){
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
        if(e.getSource() == BUTTON_CREATE_USER){
            m_bHasOperation = true;
            BUTTON_CREATE_USER.removeActionListener(this);
            refreshFrame();
            return;
        }
        if(e.getSource() == BUTTON_ADD_USER){
            if((IS_CHECKED ? !TEXTFIELD_PASS.getText().isEmpty() 
                    : PASSFIELD_USER_CONFIRM.getPassword().length != 0)){
                
            }
            m_bHasOperation = false;
            BUTTON_ADD_USER.removeActionListener(this);
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
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
