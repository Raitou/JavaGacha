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
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class AdminWindow extends JFrame 
        implements AdminComponents, ItemListener, ActionListener, KeyListener, AuthLevel, GachaConstants{
    
    
    private static LinkedHashMap<Integer, String> authLevel = null; 
    private static String userInformation[] = null;
    private static String itemList[][] = null;
    private static boolean CREATE_USER_IS_CHECKED = false;
    private static String m_strUser;
    private static int m_intUID;
    private static User m_user;
    private static int m_intItemID;
    private static String m_strItemName;
    private static int m_intItemRarity;
    
    private static Component panelUserAdd;
    private static Component panelLogout;
    private static Component panelUserEdit;
    private static Component panelItem;
    
    private static boolean m_bpanelUserAddHasOperation = false;
    private static boolean m_bpanelUserEditHasOperation = false;
    private static boolean m_bpanelItemManagerHasOperation = false;
    
    private static Component itemPanelEditItem;
    private static Component itemPanelRefresh;
    private static Component itemPanelAddItem;
    private static Component itemPanelSearchItem;
    private static Component itemPanelMinimize;
    
    private static boolean m_bItemPanelEditItemHasOperation = false;
    private static boolean m_bItemPanelAddItemHasOperation = false;
    
    private static DefaultTableModel tableModel = null;
    
    public AdminWindow(User user){
        AdminWindow.m_user = user;
        
        authLevel = new LinkedHashMap<>();
        authLevel.put(ADMIN_USER, "Administrator");
        authLevel.put(NORMAL_USER, "Normal User");
        authLevel.put(BANNED_USER, "Banned User");
        
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
            super.add(panelUserAdd, gbc);
        }
        
        if((panelUserEdit = inititateEditUser()) != null){
            gbc.gridx = 1;
            gbc.gridy = 0;
            super.add(panelUserEdit, gbc);
        }
        
        if((panelItem = initiateItemManager()) != null){
            gbc.gridx = 0;
            gbc.gridy = 1;
            super.add(panelItem, gbc);
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
            CREATE_USER_IS_VISIBLE.addItemListener(this);
            CREATE_USER_BUTTON_ADD_USER.addActionListener(this);
            BUTTON_CREATE_USER.addActionListener(this);
            BUTTON_EDIT_USER.addActionListener(this);
            BUTTON_ITEM_MANAGER.addActionListener(this);
            BUTTON_LOGOUT.addActionListener(this);
            EDIT_USER_BUTTON_MINIMIZE.addActionListener(this);
            EDIT_USER_BUTTON_SEARCH_USER.addActionListener(this);
            EDIT_USER_BUTTON_UPDATE_USER.addActionListener(this);
            BUTTON_EDIT_ITEM.addActionListener(this);
            EDIT_ITEM_BUTTON_APPLY_CHANGES.addActionListener(this);
            BUTTON_ADD_ITEM.addActionListener(this);
            ADD_ITEM_BUTTON_ADD_ITEM.addActionListener(this);
            BUTTON_SEARCH_ITEM.addActionListener(this);
            BUTTON_REFRESH.addActionListener(this);
            BUTTON_MINIMIZE.addActionListener(this);
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
            BUTTON_ITEM_MANAGER.removeActionListener(this);
            EDIT_USER_BUTTON_MINIMIZE.removeActionListener(this);
            EDIT_USER_BUTTON_SEARCH_USER.removeActionListener(this);
            EDIT_USER_BUTTON_UPDATE_USER.removeActionListener(this);
            BUTTON_EDIT_ITEM.removeActionListener(this);
            EDIT_ITEM_BUTTON_APPLY_CHANGES.removeActionListener(this);
            BUTTON_ADD_ITEM.removeActionListener(this);
            ADD_ITEM_BUTTON_ADD_ITEM.removeActionListener(this);
            BUTTON_SEARCH_ITEM.removeActionListener(this);
            BUTTON_REFRESH.removeActionListener(this);
            BUTTON_MINIMIZE.removeActionListener(this);
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
        if(panelItem != null){
            super.remove(panelItem);
        }
        if(panelLogout != null){
            super.remove(panelLogout);
        }
        if(itemPanelEditItem != null){
            super.remove(itemPanelEditItem);
        }
        if(itemPanelRefresh != null){
            super.remove(itemPanelRefresh);
        }
        if(itemPanelSearchItem != null){
            super.remove(itemPanelSearchItem);
        }
        if(itemPanelMinimize != null){
            super.remove(itemPanelMinimize);
        }
        if(itemPanelAddItem != null){
            super.remove(itemPanelAddItem);
        }
        
        loadListeners(false);
    }
    
    private void refreshFrame(){
        removeComponents();
        initiateComponents();
        super.pack();
    }
    
    private Component initiateItemManagerMinimize(){
        if(!(m_bItemPanelEditItemHasOperation ||
                m_bItemPanelAddItemHasOperation ||
                false
                )){
            return BUTTON_MINIMIZE;
        }else {
            return null;
        }
    }
    
    private Component initiateItemManagerSearchItem(){
        if(!(m_bItemPanelEditItemHasOperation ||
                m_bItemPanelAddItemHasOperation ||
                false
                )){
            return BUTTON_SEARCH_ITEM;
        }else {
            return null;
        }
    }
    
    private Component initiateItemManagerAddItem(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel itemManagerAddItemLayout = new JPanel(new GridBagLayout());
        /** 
            Just wanting to have it a body o/
        */
        if(!(m_bItemPanelEditItemHasOperation) &&
                (m_bItemPanelAddItemHasOperation)){
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //bottom, left, right, top
            gbc.insets = new Insets(5,0,0,0);
            gbc.gridx = 0;
            gbc.gridy = 0;
            itemManagerAddItemLayout.add(LABEL_ADD_ITEM, gbc);

            
            gbc.gridx = 0;  
            gbc.gridy = 1;  
            itemManagerAddItemLayout.add(LABEL_ADD_ITEM_RARITY, gbc);


            gbc.ipady = 0;
            gbc.gridx = 1;  
            gbc.gridy = 1;  
            itemManagerAddItemLayout.add(COMBOXBOX_ITEM_RARITY, gbc);

            gbc.ipady = 5;
            gbc.gridx = 0;  
            gbc.gridy = 2;
            itemManagerAddItemLayout.add(LABEL_ADD_ITEM_NAME, gbc);

            gbc.ipady = 0;
            gbc.gridx = 1;  
            gbc.gridy = 2;  
            itemManagerAddItemLayout.add(TEXTFIELD_ITEM_NAME, gbc);
            
            gbc.ipady = 0;
            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = 3;
            itemManagerAddItemLayout.add(ADD_ITEM_BUTTON_ADD_ITEM, gbc);
        } else {
            if(!(m_bItemPanelEditItemHasOperation ||
                false ||
                false
                )){
               return BUTTON_ADD_ITEM;
            }else {
                return null;
            }
        }
        return itemManagerAddItemLayout;
    }
    
    private Component initiateItemManagerRefresh(){
        if(!(m_bItemPanelEditItemHasOperation ||
                m_bItemPanelAddItemHasOperation ||
                false
                )){
            return BUTTON_REFRESH;
        }else {
            return null;
        }
    }
    
    private Component initiateItemManagerEditItem(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel itemManagerEditItemLayout = new JPanel(new GridBagLayout());
        /** 
            Just wanting to have it a body o/
        */
        if(!(m_bItemPanelAddItemHasOperation) &&
                (m_bItemPanelEditItemHasOperation)){
            gbc.fill = GridBagConstraints.HORIZONTAL;

            //bottom, left, right, top
            gbc.insets = new Insets(5,0,0,0);
            gbc.gridx = 0;
            gbc.gridy = 0;
            itemManagerEditItemLayout.add(EDIT_ITEM_LABEL_EDIT_ITEM, gbc);

            
            gbc.gridx = 0;  
            gbc.gridy = 1;  
            itemManagerEditItemLayout.add(EDIT_ITEM_LABEL_EDIT_ITEM_RARITY, gbc);


            gbc.ipady = 0;
            gbc.gridx = 1;  
            gbc.gridy = 1;  
            itemManagerEditItemLayout.add(EDIT_ITEM_COMBOBOX_ITEM_RARITY, gbc);

            gbc.ipady = 5;
            gbc.gridx = 0;  
            gbc.gridy = 2;
            itemManagerEditItemLayout.add(EDIT_ITEM_LABEL_EDIT_ITEM_NAME, gbc);

            gbc.ipady = 0;
            gbc.gridx = 1;  
            gbc.gridy = 2;  
            itemManagerEditItemLayout.add(EDIT_ITEM_TEXTFIELD_ITEM_NAME, gbc);
            
            gbc.ipady = 0;
            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = 3;
            itemManagerEditItemLayout.add(EDIT_ITEM_BUTTON_APPLY_CHANGES, gbc);
        } else {
            if(!(m_bItemPanelEditItemHasOperation ||
                m_bItemPanelAddItemHasOperation ||
                false
                )){
               return BUTTON_EDIT_ITEM;
            }else {
                return null;
            }
        }
        return itemManagerEditItemLayout;
        
    }
    
    private Component initiateItemManager(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel itemManagerLayout = new JPanel(new GridBagLayout());
        JPanel itemLayoutTable = new JPanel(new GridBagLayout());
        JPanel itemLayoutPanel = new JPanel(new GridBagLayout());
        if(!(m_bpanelUserAddHasOperation && m_bpanelUserEditHasOperation) &&
                (m_bpanelItemManagerHasOperation)){
            
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10,10,10,10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            tableModel = new DefaultTableModel();
            tableModel.addColumn("Item_ID");
            tableModel.addColumn("Item_Type");
            tableModel.addColumn("Item_Name");
            if((itemList = SQLCore.getAllItems()) != null){
                for (String[] items : itemList) {
                    if(items[1].equals("0")){
                        items[1] = "NORMAL";
                    }
                    else if(items[1].equals("1")){
                        items[1] = "RARE";
                    }
                    else if(items[1].equals("2")){
                        items[1] = "SUPER RARE";
                    } else {
                        items[1] = "INVALID";
                    }
                    tableModel.addRow(items);
                }
            }
            ITEM_LIST.setModel(tableModel);
            itemLayoutTable.add(ITEM_LIST_SCROLL, gbc);
            
            gbc.insets = new Insets(5,5,5,5);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 0;
            itemManagerLayout.add(itemLayoutTable, gbc);
            
            if(true){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                
                if((itemPanelRefresh = initiateItemManagerRefresh()) != null){
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 2;
                    itemLayoutPanel.add(itemPanelRefresh, gbc);
                }
                
                if((itemPanelAddItem = initiateItemManagerAddItem()) != null){
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 1;
                    itemLayoutPanel.add(itemPanelAddItem, gbc);
                }
                
                if((itemPanelEditItem = initiateItemManagerEditItem()) != null){
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    itemLayoutPanel.add(itemPanelEditItem, gbc);
                }
                
                if((itemPanelSearchItem = initiateItemManagerSearchItem()) != null){
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.gridwidth = 2;
                    itemLayoutPanel.add(itemPanelSearchItem, gbc);
                }          
                
                if((itemPanelMinimize = initiateItemManagerMinimize()) != null){
                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    gbc.gridwidth = 2;
                    itemLayoutPanel.add(itemPanelMinimize, gbc);
                }
                
            }
            
            gbc.insets = new Insets(5,5,5,5);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 1;
            gbc.gridy = 0;
            itemManagerLayout.add(itemLayoutPanel, gbc);
            
            
        } else {
            if(!(m_bpanelUserAddHasOperation ||
                m_bpanelUserEditHasOperation ||
                m_bpanelItemManagerHasOperation
                )){
               return BUTTON_ITEM_MANAGER;
            }else {
                return null;
            }
        }
        return itemManagerLayout;
    }
    
    private Component initiateLogout(){
        if(!(m_bpanelUserAddHasOperation ||
                m_bpanelUserEditHasOperation ||
                m_bpanelItemManagerHasOperation
                )){
            return BUTTON_LOGOUT;
        }else {
            return null;
        }
    }
    
    private Component inititateEditUser(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel editUserLayout = new JPanel(new GridBagLayout());
        if(!(m_bpanelUserAddHasOperation && m_bpanelItemManagerHasOperation) &&
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
            EDIT_USER_TEXTFIELD_SEARCH_USER.setText("");
            
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
            EDIT_USER_TEXTFIELD_NICK.setText("");     
            
            gbc.gridx = 2;
            gbc.gridy = 2;
            editUserLayout.add(EDIT_USER_LABEL_ACCESS, gbc);
            
            gbc.gridx = 2;
            gbc.gridy = 3;
            editUserLayout.add(EDIT_USER_COMBOBOX_ACCESS, gbc);
            EDIT_USER_COMBOBOX_ACCESS.setEnabled(false);
            EDIT_USER_COMBOBOX_ACCESS.removeAllItems();
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            editUserLayout.add(EDIT_USER_LABEL_PASS, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 3;
            editUserLayout.add(EDIT_USER_PASSFIELD_USER, gbc);
            EDIT_USER_PASSFIELD_USER.setEditable(false);
            EDIT_USER_PASSFIELD_USER.setText("");
            
            gbc.gridx = 1;
            gbc.gridy = 4;
            editUserLayout.add(EDIT_USER_PASSFIELD_USER_CONFIRM, gbc);
            EDIT_USER_PASSFIELD_USER_CONFIRM.setEditable(false);
            EDIT_USER_PASSFIELD_USER_CONFIRM.setText("");
            
            gbc.gridx = 1;
            gbc.gridy = 5;
            editUserLayout.add(EDIT_USER_BUTTON_UPDATE_USER, gbc);
            EDIT_USER_BUTTON_UPDATE_USER.setEnabled(false);
            
            gbc.gridx = 2;
            gbc.gridy = 5;
            editUserLayout.add(EDIT_USER_BUTTON_MINIMIZE, gbc);
            
            
            
            
        } else {
            if(!(m_bpanelUserAddHasOperation ||
                m_bpanelUserEditHasOperation ||
                m_bpanelItemManagerHasOperation
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
                !(m_bpanelUserEditHasOperation && m_bpanelItemManagerHasOperation)){
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
                m_bpanelUserEditHasOperation ||
                m_bpanelItemManagerHasOperation
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
        m_bpanelItemManagerHasOperation = false;
        BUTTON_CREATE_USER.removeActionListener(this);
        refreshFrame();
    }
    
    private void buttonEditUser(){
        m_bpanelUserAddHasOperation = false;
        m_bpanelUserEditHasOperation = true;
        m_bpanelItemManagerHasOperation = false;
        BUTTON_EDIT_USER.removeActionListener(this);
        refreshFrame();
    }
    
    private void buttonItemManager(){
        m_bpanelUserAddHasOperation = false;
        m_bpanelUserEditHasOperation = false;
        m_bpanelItemManagerHasOperation = true;
        BUTTON_ITEM_MANAGER.removeActionListener(this);
        refreshFrame();
    }
    
    private void itemManagerButtonAddItem(){
        m_bItemPanelEditItemHasOperation = false;
        m_bItemPanelAddItemHasOperation = true;
        BUTTON_ADD_ITEM.removeActionListener(this);
        refreshFrame();
    }
    
    private void itemManagerButtonSearchItem(){
        String strItemID = JOptionPane.showInputDialog(panelItem, "Please Enter the ITEM_ID you want to search: ", "Search Item", JOptionPane.QUESTION_MESSAGE);
        int itemID;
        try{
            itemID = Integer.parseInt(strItemID);
        }catch(NumberFormatException ex){
            System.out.println(ex.getLocalizedMessage());
            return;
        }
        String itemInfo[];
        if((itemInfo = SQLCore.getItemInformation(itemID)) == null){
            System.out.println("Item not found");
            return;
        }
        JOptionPane.showMessageDialog(panelItem, 
                "ItemID:   " + itemInfo[0] + "\n" +
                "ItemType: " + ("0".equals(itemInfo[1]) ? "Normal" : "1".equals(itemInfo[1]) ? "Rare" : "Super Rare") + "\n" +
                "ItemName: " + itemInfo[2],
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void itemManagerButtonDeleteItem(){
        m_intItemID = 0;
        int row[] = ITEM_LIST.getSelectedRows();
        if(tableModel == null){
                return;
        }
        for(Integer i : row){
            m_intItemID = Integer.parseInt(ITEM_LIST.getValueAt(i, 0).toString());
        }
    
    }
    
    private void itemManagerButtonEditItem(){
        m_intItemID = 0;
        int row[] = ITEM_LIST.getSelectedRows();
        for(Integer i : row){
            m_intItemID = Integer.parseInt(ITEM_LIST.getValueAt(i, 0).toString());
            System.out.println(m_intItemID);
            m_intItemRarity = "NORMAL".equals(ITEM_LIST.getValueAt(i, 1).toString()) ? 0 : 
                    "RARE".equals(ITEM_LIST.getValueAt(i, 1).toString()) ? 1 : 2;
            EDIT_ITEM_COMBOBOX_ITEM_RARITY.setSelectedIndex(m_intItemRarity);
            m_strItemName = ITEM_LIST.getValueAt(i, 2).toString();
            EDIT_ITEM_TEXTFIELD_ITEM_NAME.setText(m_strItemName);
        }
        if(m_intItemID == 0){
            return;
        }
        m_bItemPanelEditItemHasOperation = true;
        m_bItemPanelAddItemHasOperation = false;
        BUTTON_EDIT_ITEM.removeActionListener(this);
        refreshFrame();
    }
    
    private void itemManagerAddItemButtonApplyMinimizeItem(){
        if(TEXTFIELD_ITEM_NAME.getText().isEmpty()){
            m_bItemPanelEditItemHasOperation = false;
            m_bItemPanelAddItemHasOperation = false;
            ADD_ITEM_BUTTON_ADD_ITEM.removeActionListener(this);
            refreshFrame();
            return;
        }
        if(SQLCore.addItemData(TEXTFIELD_ITEM_NAME.getText(), COMBOXBOX_ITEM_RARITY.getSelectedIndex())){
            System.out.println("Item succesfully added");
        } else {
            System.out.println("Failed added item");
        }
        m_bItemPanelEditItemHasOperation = false;
        m_bItemPanelAddItemHasOperation = false;
        ADD_ITEM_BUTTON_ADD_ITEM.removeActionListener(this);
        refreshFrame();
        
    }
    
    private void itemManagerEditItemButtonApplyMinimizeItem(){
        int checkList = 0;
        if(EDIT_ITEM_COMBOBOX_ITEM_RARITY.getSelectedIndex() == m_intItemRarity){
            System.out.println("itemRarity+");
            checkList++;
        }
        if(EDIT_ITEM_TEXTFIELD_ITEM_NAME.getText().equals(m_strItemName)){
            System.out.println("itemName+");
            checkList++;
        }
        if(checkList == 2){
            m_bItemPanelEditItemHasOperation = false;
            m_bItemPanelAddItemHasOperation = false;
            EDIT_ITEM_BUTTON_APPLY_CHANGES.removeActionListener(this);
            refreshFrame();
            return;
        }
        if(SQLCore.updateItemData(m_intItemID, EDIT_ITEM_TEXTFIELD_ITEM_NAME.getText(), EDIT_ITEM_COMBOBOX_ITEM_RARITY.getSelectedIndex())){
            System.out.println("Data Succesfully Added");
            m_bItemPanelEditItemHasOperation = false;
            m_bItemPanelAddItemHasOperation = false;
        }
        EDIT_ITEM_BUTTON_APPLY_CHANGES.removeActionListener(this);
        refreshFrame();
    }
    
    private void editUserButtonMinimize(){
        m_bpanelUserAddHasOperation = false;
        m_bpanelUserEditHasOperation = false;
        m_bpanelItemManagerHasOperation = false;
        EDIT_USER_BUTTON_MINIMIZE.removeActionListener(this);
        EDIT_USER_BUTTON_UPDATE_USER.setEnabled(false);
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
        m_bpanelItemManagerHasOperation = false;
        CREATE_USER_BUTTON_ADD_USER.removeActionListener(this);
        refreshFrame();
    }
    
    private void editUserButtonSearchUser() {
        if(EDIT_USER_TEXTFIELD_SEARCH_USER.getText().isEmpty()){
            return;
        }
        userInformation = SQLCore.searchUserInformation(EDIT_USER_TEXTFIELD_SEARCH_USER.getText());
        if(userInformation == null){
            return;
        }
        EDIT_USER_TEXTFIELD_NICK.setText(userInformation[0]);
        EDIT_USER_PASSFIELD_USER.setText("********");
        EDIT_USER_PASSFIELD_USER_CONFIRM.setText("********");
        EDIT_USER_TEXTFIELD_NICK.setEditable(true);
        EDIT_USER_PASSFIELD_USER.setEditable(true);
        EDIT_USER_PASSFIELD_USER_CONFIRM.setEditable(true);
        EDIT_USER_BUTTON_UPDATE_USER.setEnabled(true);
        EDIT_USER_COMBOBOX_ACCESS.removeAllItems();
        authLevel.forEach((authLev, authString)->{
            EDIT_USER_COMBOBOX_ACCESS.addItem(authString);
        });
        EDIT_USER_COMBOBOX_ACCESS.setSelectedItem(authLevel.get(SQLCore.getUserAuth(Integer.parseInt(userInformation[2]))));
        EDIT_USER_COMBOBOX_ACCESS.setEnabled(true);
    }
    
    private void editUserButtonUpdateUser(){
        int checkList = 0;
        
        String userInfo = "";
        if(userInformation[3].equals(Integer.toString(ADMIN_USER)))
            userInfo = "Administrator";
        if(userInformation[3].equals(Integer.toString(NORMAL_USER)))
            userInfo = "Normal User";
        if(userInformation[3].equals(Integer.toString(BANNED_USER)))
            userInfo = "Banned User";
        
        if(userInformation[0].equals(EDIT_USER_TEXTFIELD_NICK.getText())){
            checkList++;
        }
        if(new String(EDIT_USER_PASSFIELD_USER_CONFIRM.getPassword()).equals("********")){
            checkList++;
        }
        if(userInfo.equals(EDIT_USER_COMBOBOX_ACCESS.getSelectedItem())){
            checkList++;
        }
        if(checkList == 3){
            return;
        }
        
        if(EDIT_USER_TEXTFIELD_NICK.getText().isEmpty()){
            JOptionPane.showMessageDialog(panelUserEdit, "Nickname is Empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(EDIT_USER_PASSFIELD_USER.getPassword().length == 0 ||
               EDIT_USER_PASSFIELD_USER_CONFIRM.getPassword().length == 0 ){
            JOptionPane.showMessageDialog(panelUserEdit, "Password is Empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(!(new String(EDIT_USER_PASSFIELD_USER.getPassword())
                .equals(new String(EDIT_USER_PASSFIELD_USER_CONFIRM.getPassword())))){
            JOptionPane.showMessageDialog(panelUserEdit, "Password is not the same with confirm password", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        
        if(userInformation[0].equals(EDIT_USER_TEXTFIELD_NICK.getText()) &&
                new String(EDIT_USER_PASSFIELD_USER_CONFIRM.getPassword()).equals("********")
                ){
            if(EDIT_USER_COMBOBOX_ACCESS.getSelectedItem() == userInfo){
                return;
            }
        }
        
        if(!EDIT_USER_TEXTFIELD_NICK.getText().isEmpty()){
                SQLCore.setNickname(Integer.parseInt(userInformation[2]), EDIT_USER_TEXTFIELD_NICK.getText());
                userInformation[0] =  EDIT_USER_TEXTFIELD_NICK.getText();
        }
        
        authLevel.forEach((authLev, authString)->{
                if(EDIT_USER_COMBOBOX_ACCESS.getSelectedItem() == authString){
                    SQLCore.setUserAuth(Integer.parseInt(userInformation[2]), authLev);
                };
        });
        
        if((new String(EDIT_USER_PASSFIELD_USER.getPassword())
                .equals(new String(EDIT_USER_PASSFIELD_USER_CONFIRM.getPassword())))){
           if(!new String(EDIT_USER_PASSFIELD_USER_CONFIRM.getPassword()).equals("********")){
               SQLCore.setPassword(Integer.parseInt(userInformation[2]), 
                   new String(EDIT_USER_PASSFIELD_USER_CONFIRM.getPassword()));
           }
           
        }
        JOptionPane.showMessageDialog(panelUserEdit, "Data succesfully updated!", "Information", JOptionPane.INFORMATION_MESSAGE);
        editUserButtonMinimize();       
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
        if(e.getSource() == BUTTON_ITEM_MANAGER){
            buttonItemManager();
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
        if(e.getSource() == EDIT_USER_BUTTON_UPDATE_USER){
            editUserButtonUpdateUser();
            return;
        }
        if(e.getSource() == EDIT_ITEM_BUTTON_APPLY_CHANGES){
            itemManagerEditItemButtonApplyMinimizeItem();
            return;
        }
        if(e.getSource() == BUTTON_EDIT_ITEM){
            itemManagerButtonEditItem();
            return;
        }
        if(e.getSource() == BUTTON_ADD_ITEM){
            itemManagerButtonAddItem();
            return;
        }
        if(e.getSource() == ADD_ITEM_BUTTON_ADD_ITEM){
            itemManagerAddItemButtonApplyMinimizeItem();
            return;
        }
        if(e.getSource() == BUTTON_SEARCH_ITEM){
            itemManagerButtonSearchItem();
            return;
        }
        if(e.getSource() == BUTTON_REFRESH){
            m_bItemPanelEditItemHasOperation = false;
            m_bItemPanelAddItemHasOperation = false;
            BUTTON_REFRESH.removeActionListener(this);
            refreshFrame();
            return;
        }
        if(e.getSource() == BUTTON_MINIMIZE){
            m_bpanelUserAddHasOperation = false;
            m_bpanelUserEditHasOperation = false;
            m_bpanelItemManagerHasOperation = false;
            BUTTON_MINIMIZE.removeActionListener(this);
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
