/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 201811610
 */
public class GachaItem {
    private String name;
    private int id;
    private int type;
    
    public GachaItem(int id, int type,String name){
        setName(name);
        setType(type);
        setId(id);
        
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
    
    public void setName(String name){
        this.name=name;
    }
    
    public void setType(int type){
        this.type=type;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    @Override
    public String toString(){
        String stars = "";
        if(this.type >= GachaBox.NORMAL)
            stars += "*";
        if(this.type >= GachaBox.RARE)
            stars += "*";
        if(this.type >= GachaBox.SUPER_RARE)
            stars += "*";
        
        return this.getName()+stars;
    }
}
