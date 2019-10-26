
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 201811610
 */



public class GachaWindow extends JFrame implements ActionListener{
    private JLabel titleLbl;
    private JButton rollBtn;
    private JTextField itemFld;
    
    private static final GachaBox box = new GachaBox();;
    
    public GachaWindow(){
        super("Gacha");
        setup();
    }
    
    public void setup(){
        setLayout(new FlowLayout());
        
        titleLbl=new JLabel("JAVA HEROES ROULETTE");
        rollBtn=new JButton("ROLL");
        itemFld=new JTextField("",10);
        itemFld.setEditable(false);
        
        add(titleLbl);
        add(itemFld);
        add(rollBtn);
        
        setSize(400,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        rollBtn.addActionListener(this);
        
        
    }
    
    public static void main(String[] args){
        GachaWindow test = new GachaWindow();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==rollBtn){
            gachaRoll();
        }
    }
    
    public void gachaRoll(){
         itemFld.setText(box.roll().toString());
    }
}
