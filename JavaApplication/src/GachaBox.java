
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
public class GachaBox {
    private static ArrayList<GachaItem> nItems;
    private static ArrayList<GachaItem> rItems;
    private static ArrayList<GachaItem> srItems;
    
    public final static int NORMAL = 0;
    public final static int RARE = 1;
    public final static int SUPER_RARE = 2;
    
    public GachaBox(){
        fill();
    }
    
    private void fill(){
        nItems=SQLCore.getItems(NORMAL);
        rItems=SQLCore.getItems(RARE);
        srItems=SQLCore.getItems(SUPER_RARE);
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
        double rng = r.nextDouble();
        System.out.println(rng);
        if(rng < 0.6){
            int rng2 = r.nextInt(nItems.size());
            item = nItems.get(rng2);
            return item;
        }
        if(rng < 0.8){
            int rng2 = r.nextInt(rItems.size());
            item = rItems.get(rng2);
            return item;
        }
        
        if(rng == 1.0){
            int rng2 = r.nextInt(srItems.size());
            item = srItems.get(rng2);
            return item;
        }
        return item;
    }
}
