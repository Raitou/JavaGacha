
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
    private final static SQLCore SQL_CONN = new SQLCore();
    private ArrayList<GachaItem> nItems;
    private ArrayList<GachaItem> rItems;
    private ArrayList<GachaItem> srItems;
    
    public final static int NORMAL = 0;
    public final static int RARE = 1;
    public final static int SUPER_RARE = 2;
    
    public GachaBox(){
        fill();
    }
    
    private void fill(){
        nItems=SQL_CONN.getItems(NORMAL);
        rItems=SQL_CONN.getItems(RARE);
        srItems=SQL_CONN.getItems(SUPER_RARE);
    }
    
    public void listAll(){
        listItems(nItems);
        listItems(rItems);
        listItems(srItems);
        
    }
    
    public void listItems(ArrayList<GachaItem> x){
        for(GachaItem y: x)
            System.out.println(y);
    }
    
    public GachaItem roll(){
        GachaItem item=null;
        
        Random r = new Random();
        
        int rng = r.nextInt(100);
        
        if(rng < 60){
            rng = r.nextInt(nItems.size());
            item = nItems.get(rng);
            
        }
        else if(rng < 80){
            rng = r.nextInt(rItems.size());
            item = rItems.get(rng);
        }
        else if(rng < 99){
            rng = r.nextInt(srItems.size());
            item = srItems.get(rng);
        }
        
        return item;
    }
}
