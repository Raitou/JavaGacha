
import java.util.ArrayList;
import java.util.Random;

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
    /*
    This class contains all the Items available in the Gacha
    This represents the actual Gacha Vending machine
    */
    
    private static ArrayList<GachaItem> nItems;
    private static ArrayList<GachaItem> rItems;
    private static ArrayList<GachaItem> srItems;
    

    
    public GachaBox(){
        fill();
    }
    
    /*
    fills the ArrayList instance variable with all items of all item types
    */
    private void fill(){
        nItems=SQLCore.getAllItems(NORMAL);
        rItems=SQLCore.getAllItems(RARE);
        srItems=SQLCore.getAllItems(SUPER_RARE);
    }
    
    /*
    returns a GachaItem based on random chance
    The chance of getting a specific Item type is weighted
    Normal items have a greater chance of being rolled than rare items
    and so on
    */
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
