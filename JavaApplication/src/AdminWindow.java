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
import javax.swing.JPanel;

public class AdminWindow extends JFrame 
        implements AdminComponents, ItemListener, ActionListener, KeyListener{
    
    private static boolean IS_CHECKED = false;
    
    
    public AdminWindow(String user, int userID){
        super.setTitle("Admin Window");
        
        super.add(initiateCreateUser(IS_CHECKED));
        
        refreshFrame();
    }
    
    private void refreshFrame(){
        super.setLayout(new GridBagLayout());
        super.pack();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private Component initiateCreateUser(boolean isChecked){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel userLayout = new JPanel(new GridBagLayout());
        /** 
            UwU
        */
        {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //bottom, left, right, top
        gbc.insets = new Insets(5, 20, 0, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        userLayout.add(LABEL_CREATE_USER, gbc);
        
        gbc.insets = new Insets(5,0,0,0);
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
        
        gbc.ipady = 0;
        gbc.gridx = 1;  
        gbc.gridy = 2;  
        userLayout.add(PASSFIELD_USER, gbc);
        
        gbc.ipady = 0;
        gbc.gridx = 1;
        gbc.gridy = 3;
        userLayout.add(PASSFIELD_USER_CONFIRM, gbc);
        
        IS_VISIBLE.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
            };
        }
        
        );
        
        gbc.ipady = 0;
        gbc.gridx = 2;
        gbc.gridy = 2;
        userLayout.add(IS_VISIBLE, gbc);
        
        }
        return userLayout;
    }
    
    private void loadLoginWindow(){
        this.dispose();
        LoginWindow w = new LoginWindow();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == IS_VISIBLE){
            if(e.getStateChange() == 1){
                IS_CHECKED = true;
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
