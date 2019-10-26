
import java.util.Map;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 201811610
 */


public class GachaTest {
    public static void main(String[] args){
        GachaBox test = new GachaBox();
        
        
        test.listAll();
        
        System.out.println("Roll: ");
        System.out.println(test.roll().getName());
        
        
    }
}
