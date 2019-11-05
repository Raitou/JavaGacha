/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 201811610
 */
public class GachaItem implements GachaConstants{
    /*
    This class represents the information regarding a single item in the Gacha
    It contains name of the item, its ID in the database, its rarity type
    and which user owns it
    */
    
    private String name;
    private int id;
    private int type;
    private int ownershipID = -1;
    
    public GachaItem(int id, int type,String name){
        setName(name);
        setType(type);
        setId(id);
        
    }
    
    public GachaItem(int id, int type, String name, int ownershipID){
        setName(name);
        setType(type);
        setId(id);
        setOwnership(ownershipID);
    }
    
    public String getName(){
        return name;
    }
    
    public int getType(){
        return type;
    }
    
    public int getId(){
        return id;
    }
    
    public int getPrice(){
        int price;
        switch(type){
            case NORMAL:
                price = NORMAL_PRICE;
                break;
            case RARE:
                price = RARE_PRICE;
                break;
            case SUPER_RARE:
                price = SR_PRICE;
                break;
            default:
                price = -1;
        }
        
        return price;
    }
    
    public int getOwnershipID(){
        return ownershipID;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public void setType(int type){
        this.type=type;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public void setOwnership(int ownershipID){
        this.ownershipID=ownershipID;
    }
    
    @Override
    public String toString(){
        String stars = "";
        if(this.type >= GachaConstants.NORMAL)
            stars += "*";
        if(this.type >= GachaConstants.RARE)
            stars += "*";
        if(this.type >= GachaConstants.SUPER_RARE)
            stars += "*";
        
        return String.format("%s\n%s", name, stars);
    }
}
