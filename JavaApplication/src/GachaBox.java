
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 201811610
 */
public class GachaBox implements GachaConstants{
    private static ArrayList<GachaItem> nItems;
    private static ArrayList<GachaItem> rItems;
    private static ArrayList<GachaItem> srItems;
    

    
    public GachaBox(){
        fill();
    }
    
    private void fill(){
        nItems=SQLCore.getAllItems(NORMAL);
        rItems=SQLCore.getAllItems(RARE);
        srItems=SQLCore.getAllItems(SUPER_RARE);
        listAll();
    }
    
    public void listAll(){
        System.out.print("Normal Items: ");
        listItems(nItems);
        System.out.print("Rare Items: ");
        listItems(rItems);
        System.out.print("Super Rare Items: ");
        listItems(srItems);
        
    }
    
    public void listItems(ArrayList<GachaItem> x){
        for(GachaItem y: x)
            System.out.println(y);
    }
    
    public GachaItem roll(){
        GachaItem item=null;
        
        Random r = new Random();
        double rng_ = r.nextDouble();
        
        int rng = r.nextInt(100);
        if(rng_ < 0.6){
            int rng2 = r.nextInt(nItems.size());
            item = nItems.get(rng2);
            return item;
        }
        if(rng_ < 0.99){
            int rng2 = r.nextInt(rItems.size());
            item = rItems.get(rng2);
            return item;
        }
        
        if(rng_ < 1){
            int rng2 = r.nextInt(srItems.size());
            item = srItems.get(rng2);
            return item;
        }
        
        return null;
    }

}
