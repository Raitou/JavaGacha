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
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class UserWindow extends JFrame 
        implements UserComponents, ItemListener, ActionListener, KeyListener{
    
    private static boolean IS_CHECKED = false;
    private static User m_user;
    private static ArrayList<GachaItem> m_itemOwned;
    private static Component panelChangeProfile;
    private static Component panelLogout;
    private static Component panelPlayGacha;
    private static Component panelInventory;
    private static boolean m_bpanelChangeProfileHasOperation = false;
    private static boolean m_bpanelPlayGachaHasOperation = false;
    private static boolean m_bpanelInventoryHasOperation = false;
    
    private static final GachaBox BOX = new GachaBox();
    private static JList itemsList;
    
    private final Timer m_timer = new Timer(100,this);
    
    public UserWindow(User user){
        UserWindow.m_user = user;
        
        super.setTitle("User Window");
        super.setLayout(new GridBagLayout());
        super.setResizable(false);        
        
        initiateComponents();
        m_itemOwned = SQLCore.getItemsOf(m_user.getUID());
                
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
        
        if((panelPlayGacha = inititatePlayGacha()) != null){
            gbc.gridx = 0;
            gbc.gridy = 0;
            super.add(panelPlayGacha, gbc);
        }   
                    
        if((panelInventory = initiateInventory()) != null){
            gbc.gridx = 1;
            gbc.gridy = 0;
            super.add(panelInventory,gbc);
        }
        if((panelChangeProfile = initiateChangeProfile()) != null){
            gbc.gridx = 0;
            gbc.gridy = 1;
            super.add(panelChangeProfile = initiateChangeProfile(), gbc);
        }
        
        if((panelLogout = initiateLogout()) != null){
            gbc.gridx = 1;
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
            BUTTON_INVENTORY.addActionListener(this);
            INVENTORY_BUTTON_SELL.addActionListener(this);
            INVENTORY_BUTTON_BACK.addActionListener(this);
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
            BUTTON_INVENTORY.removeActionListener(this);
            INVENTORY_BUTTON_SELL.removeActionListener(this);
            INVENTORY_BUTTON_BACK.removeActionListener(this);
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
        if(panelInventory != null){
            super.remove(panelInventory);
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
                m_bpanelPlayGachaHasOperation  ||
                m_bpanelInventoryHasOperation
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
                !(m_bpanelInventoryHasOperation)&&
                (m_bpanelPlayGachaHasOperation)){
            
            // 1st column
            
            gbc.insets = new Insets(5,5,5,5);
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            userLayout.add(PLAY_GACHA_LABEL_TITLE,gbc);
            
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.gridx = 0;
            gbc.gridy = 1;
            PLAY_GACHA_TEXTFIELD_ITEM.setHorizontalAlignment(SwingConstants.CENTER);
            
            PLAY_GACHA_TEXTFIELD_ITEM.setEditable(false);
            userLayout.add(PLAY_GACHA_TEXTFIELD_ITEM,gbc);
            
            gbc.gridwidth = 2;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 3;
            userLayout.add(PLAY_GACHA_BUTTON_ROLL,gbc);
            
            // 2nd column
            
            gbc.gridx = 2;
            gbc.gridy = 1;
            PLAY_GACHA_LABEL_GAMEPOINTS.setText("GP: "+m_user.getGP());
            userLayout.add(PLAY_GACHA_LABEL_GAMEPOINTS,gbc);

            gbc.gridx = 2;
            gbc.gridy = 3;
            userLayout.add(PLAY_GACHA_BUTTON_BACK,gbc);
       
        } else {
            if(!(m_bpanelChangeProfileHasOperation ||
                m_bpanelPlayGachaHasOperation ||
                    m_bpanelInventoryHasOperation
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
                !(m_bpanelPlayGachaHasOperation) &&
                !(m_bpanelInventoryHasOperation)){
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
            CHANGE_PROFILE_TEXTFIELD_USER.setEditable(false);
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
                m_bpanelPlayGachaHasOperation ||
                m_bpanelInventoryHasOperation
                )){
               return BUTTON_CHANGE_PROFILE;
            }else {
                return null;
            }
        }
        return userLayout;
    }
    
    public Component initiateInventory(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel userLayout = new JPanel(new GridBagLayout());
        
        if(m_bpanelInventoryHasOperation &&
                !(m_bpanelPlayGachaHasOperation) &&
                !(m_bpanelChangeProfileHasOperation)){
            // 1st column
            
            gbc.insets = new Insets(5,5,5,5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            userLayout.add(INVENTORY_LABEL_INVENTORY,gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 0;
            PLAY_GACHA_LABEL_GAMEPOINTS.setText("GP: "+m_user.getGP());
            userLayout.add(PLAY_GACHA_LABEL_GAMEPOINTS,gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 1;
            updateItemsList();
            userLayout.add(itemsList,gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 1;
            userLayout.add(INVENTORY_BUTTON_SELL,gbc);
       
            gbc.gridx = 1;
            gbc.gridy = 2;
            userLayout.add(INVENTORY_BUTTON_BACK,gbc);
        } else {
            if(!(m_bpanelInventoryHasOperation ||
                 m_bpanelChangeProfileHasOperation ||
                 m_bpanelPlayGachaHasOperation   
                    )){
               return BUTTON_INVENTORY;
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
            buttonChangeProfile();
            return;
        }
        if(e.getSource() == BUTTON_PLAY_GACHA){
            buttonPlayGacha();
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
            changeProfileButtonApply();
            return;
        }
        
        if(e.getSource() == PLAY_GACHA_BUTTON_ROLL){
            playGachaButtonRoll();
        }
        
        if(e.getSource() == PLAY_GACHA_BUTTON_BACK){
            playGachaButtonBack();
        }
        
        if(e.getSource() == BUTTON_INVENTORY){
            playGachaButtonShowItems();
        }
        
        if(e.getSource() == INVENTORY_BUTTON_SELL){
            inventoryButtonSell();
        }
        
        if(e.getSource() == INVENTORY_BUTTON_BACK){
            inventoryButtonBack();
        }
        
        if(e.getSource() == m_timer){
            PLAY_GACHA_TEXTFIELD_ITEM.setText(BOX.roll().toString());
        } 
    }
    
    public void buttonPlayGacha(){
        
        m_bpanelChangeProfileHasOperation = false;
        m_bpanelPlayGachaHasOperation = true;
        m_bpanelInventoryHasOperation = false;
        BUTTON_PLAY_GACHA.removeActionListener(this);
        refreshFrame();
    }
    
    public void buttonChangeProfile(){
        m_bpanelChangeProfileHasOperation = true;
        m_bpanelPlayGachaHasOperation = false;
        m_bpanelInventoryHasOperation = false;
        BUTTON_CHANGE_PROFILE.removeActionListener(this);
        refreshFrame();
    }
    
    public void changeProfileButtonApply(){
            
        if((IS_CHECKED ? !CHANGE_PROFILE_TEXTFIELD_PASS.getText().isEmpty() 
                : CHANGE_PROFILE_PASSFIELD_USER.getPassword().length != 0)
                ||CHANGE_PROFILE_TEXTFIELD_NICKNAME.getText().isEmpty()){
            String nickname;
            String password;
            nickname = CHANGE_PROFILE_TEXTFIELD_NICKNAME.getText();
            password = IS_CHECKED ? CHANGE_PROFILE_TEXTFIELD_PASS.getText()
                    : String.copyValueOf(CHANGE_PROFILE_PASSFIELD_USER.getPassword());
            if((!IS_CHECKED)&& !password.equals(String.copyValueOf(CHANGE_PROFILE_PASSFIELD_USER_CONFIRM.getPassword()))){
                JOptionPane.showMessageDialog(rootPane, 
                        "Password confirm must match",
                        "Warning",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(changeProfileApply(nickname,password))
                JOptionPane.showMessageDialog(rootPane,
                    "Changes has been applied",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            JOptionPane.showMessageDialog(rootPane, 
                "Field/s are empty",
                "Warning",
                JOptionPane.ERROR_MESSAGE);
        }
        
        m_bpanelChangeProfileHasOperation = false;
        m_bpanelPlayGachaHasOperation = false;
        m_bpanelInventoryHasOperation = false;
        CHANGE_PROFILE_BUTTON_APPLY.removeActionListener(this);
        refreshFrame();
    }
    
    public boolean changeProfileApply(String nickname, String password){
        boolean modifiedFields = false;
        
        if(!nickname.equals(m_user.getNickname()))
            modifiedFields = true;
        if(!password.equals("********"))
            modifiedFields = true;
        
        if(modifiedFields){
            SQLCore.setNickname(m_user.getUID(),nickname);
            SQLCore.setPassword(m_user.getUID(),password);
            m_user.refresh();
            return true;
        }
        
        return false;
    }
    
    public void playGachaButtonRoll(){
        if("ROLL".equals(PLAY_GACHA_BUTTON_ROLL.getText())){
            if(m_user.getGP() < GachaConstants.ROLL_PRICE){
                JOptionPane.showMessageDialog(rootPane, 
                        "Your current GP is not enough to roll",
                        "Insufficient GP",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!((JOptionPane.showConfirmDialog(rootPane, 
                "Rolling will cost you "+GachaConstants.ROLL_PRICE+"GP.\n"
                + "Are you sure you want to roll?",
                "Confirmation",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE)) == JOptionPane.OK_OPTION)){
                return;
            }
            
            m_timer.start();
            PLAY_GACHA_BUTTON_ROLL.setText("STOP");
            PLAY_GACHA_BUTTON_BACK.setEnabled(false);
            BUTTON_INVENTORY.setEnabled(false);
            updateGPby(-GachaConstants.ROLL_PRICE);
            refreshFrame();
            
        }
            
        else{
            GachaItem rolled = gachaRoll();
            m_timer.stop();
            PLAY_GACHA_BUTTON_ROLL.setText("ROLL");
            PLAY_GACHA_BUTTON_BACK.setEnabled(true);
            BUTTON_INVENTORY.setEnabled(true);
            JOptionPane.showMessageDialog(rootPane, 
                    "You rolled a "+rolled.getName(),
                    "Congratulations!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void playGachaButtonBack(){
        int c = JOptionPane.showConfirmDialog(rootPane, 
                    "Are you sure you leave?", "Back", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            
        if(c == JOptionPane.OK_OPTION){
            m_bpanelChangeProfileHasOperation = false;
            m_bpanelPlayGachaHasOperation = false;
            m_bpanelInventoryHasOperation = false;
            PLAY_GACHA_BUTTON_BACK.removeActionListener(this);
            PLAY_GACHA_BUTTON_ROLL.removeActionListener(this);
            refreshFrame();
        }
    }
    
    public void playGachaButtonShowItems(){
        m_bpanelPlayGachaHasOperation = false;
        m_bpanelInventoryHasOperation = true;
        m_bpanelChangeProfileHasOperation = false;
        BUTTON_INVENTORY.removeActionListener(this);
        
        refreshFrame();
    }
    
    public void inventoryButtonSell(){
        if(itemsList.isSelectionEmpty()){
            JOptionPane.showMessageDialog(rootPane, 
                    "Please select an item to sell",
                    "Nothing is selected",
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            GachaItem selectedItem = m_itemOwned.get(itemsList.getSelectedIndex());
            int c = JOptionPane.showConfirmDialog(rootPane, 
                    "Sell for "+selectedItem.getPrice()+"GP?",
                    "Sell Item?",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(c == JOptionPane.OK_OPTION){
                SQLCore.removeItemOwn(selectedItem.getOwnershipID());
                updateGPby(selectedItem.getPrice());
                
                JOptionPane.showMessageDialog(rootPane, 
                        "You received "+selectedItem.getPrice()+"GP",
                        "Transaction Complete",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        m_itemOwned = SQLCore.getItemsOf(m_user.getUID());
        refreshFrame();
    }
    
    public void inventoryButtonBack(){
        int c = JOptionPane.showConfirmDialog(rootPane, 
                    "Are you sure you leave?", "Back", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            
        if(c == JOptionPane.OK_OPTION){
            m_bpanelChangeProfileHasOperation = false;
            m_bpanelPlayGachaHasOperation = false;
            m_bpanelInventoryHasOperation = false;
            INVENTORY_BUTTON_SELL.removeActionListener(this);
            INVENTORY_BUTTON_BACK.removeActionListener(this);
            refreshFrame();
        }
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
    
    public GachaItem gachaRoll(){
        GachaItem item = BOX.roll();
        PLAY_GACHA_TEXTFIELD_ITEM.setText(item.toString());
        
        SQLCore.itemOwn(m_user.getUID(), item);
        
        return item;
    }
    
    public void updateGPby(int gp){
        int currentGP = m_user.getGP();
        currentGP += gp;
        SQLCore.setGP(m_user.getUID(), currentGP);
    }

    private void updateItemsList() {
        ArrayList<GachaItem> updatedList = SQLCore.getItemsOf(m_user.getUID());
        String list[] = list2array(updatedList);
        
        itemsList = new JList<>(list);
        m_itemOwned = updatedList;
    }
        
    public static String[] list2array(ArrayList<GachaItem> collection){
        String string[] = new String[collection.size()];
        
        for(int i = 0; i < collection.size(); i++){
            string[i] = collection.get(i).toString();
        }
        
        return string;
    }

}

