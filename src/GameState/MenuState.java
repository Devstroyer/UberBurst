/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.Player;
import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URI;

/**
 *
 * @author Krzysztof
 */
public class MenuState extends GameState{
    
    private Background bg;
    
    private int currentChoice = 0;
    private String[] options = {
        "Start",
        "About",
        "Quit"
    };
    private Color titleColor;
    private Font titleFont;
    
    private Font font;
    
    public MenuState(GameStateManager gsm){
        
        this.gsm = gsm;
        
        try{
            bg = Background.getInstance();
            
            bg.setVector(-0.3,0);
            
            titleColor = new Color (255,50,30);
            titleFont = new Font("Arial", Font.PLAIN, 70);
            font = new Font("Arial", Font.PLAIN,12);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init() {
        currentChoice=0;
    }
    public void update() {
        bg.update();
    }
    public void draw(Graphics2D g) {
        //draw bg
        bg.draw(g);
        
        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("UberBurst", -2, 70);
        
        //draw menu options
        g.setFont(font);
        for(int i = 0;i<options.length;i++){
            if( i == currentChoice){
                g.setColor(Color.GREEN);
            }
            else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i],145,140+i*15);
            
        }
        
    }
    
    private void select(){
        if(currentChoice ==0){
            gsm.setState(1);
        }
        if(currentChoice ==1){
            gsm.setState(4);
            
        }
        if(currentChoice ==2){
            System.exit(0);
        }
    }
    public void keyPressed(int k) {
        if(k ==KeyEvent.VK_ENTER){
            select();
        }
        if(k ==KeyEvent.VK_UP){
            currentChoice--;
            if(currentChoice==-1){
                currentChoice= options.length-1;
            }
        }
        if(k ==KeyEvent.VK_DOWN){
            currentChoice++;
            if(currentChoice==options.length){
                currentChoice= 0;
            }
        }
    }
    public void keyReleased(int k) {}

   
}
