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

public class UserWindow extends JFrame{
    public UserWindow(String user, int userID){
        setTitle("User Window");
        super.setSize(300, 150);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
