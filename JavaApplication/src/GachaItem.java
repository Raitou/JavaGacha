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
    private String name;
    private int id;
    private int type;
    private int ownershipID = -1;       //ownership is -1 if we dont need to know if item is owned
    
    /*
    constructor without determing ownership
    use ful for listing available items
    */
    public GachaItem(int id, int type,String name){
        setName(name);
        setType(type);
        setId(id);
        
    }
    /*
    constructor that also includes ownership
    useful when trying to know the items of a user
    */
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
    
    /*
    returns the price of the item based on the item rarity
    */
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
    
    /*
    toString override that returns the name of the item and the
    associated stars/rarity of the item
    */
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
