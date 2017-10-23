/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.Player;
import Main.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Krzysztof
 */
public class NameState extends GameState {
    
    private Color color;
    private Font charFont,Gofont;
    char a,b,c;
    private int currentChoice = 0;
    
    private Font font;
    
    public NameState(GameStateManager gsm){
       
       currentChoice=0;
       this.gsm=gsm;
       charFont = new Font("Arial", Font.PLAIN, 40);
       font = new Font("Arial", Font.PLAIN,50);
       Gofont = new Font("Arial", Font.PLAIN,30);
       a='A';
       b='A';
       c='A';
    }
    
    public void init() {
      currentChoice=0;
      a='A';
      b='A';
      c='A';
    }
    
    public void update() { 
    }
    
    public void draw(Graphics2D g){
        color=new Color(0,0,0);
        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH , GamePanel.HEIGHT);
        color=new Color(255,0,0);
        g.setColor(color);
        g.setFont(font);
        g.drawString("Player Name", 10, 70);
        if(currentChoice==0){
            color=new Color(0,255,0);
            g.setColor(color);
            g.setFont(charFont);
            g.drawString(""+a, 50, 140);
            color=new Color(255,0,0);
            g.setColor(color);
            g.drawString(""+b, 90, 140);
            g.drawString(""+c, 130, 140);
            g.setFont(Gofont);
            g.drawString("start", 170, 140);
        }
        
        if(currentChoice==1){
            g.setFont(charFont);
            g.drawString(""+a, 50, 140);
            color=new Color(0,255,0);
            g.setColor(color);
            g.drawString(""+b, 90, 140);
            color=new Color(255,0,0);
            g.setColor(color);
            g.drawString(""+c, 130, 140);
            g.setFont(Gofont);
            g.drawString("start", 170, 140);
        }
        if(currentChoice==2){
            g.setFont(charFont);
            g.drawString(""+a, 50, 140);
            g.drawString(""+b, 90, 140);
            color=new Color(0,255,0);
            g.setColor(color);
            g.drawString(""+c, 130, 140);
            color=new Color(255,0,0);
            g.setColor(color);
            g.setFont(Gofont);
            g.drawString("start", 170, 140);
        }
        if(currentChoice==3){
            g.setFont(charFont);
            g.drawString(""+a, 50, 140);
            g.drawString(""+b, 90, 140);
            g.drawString(""+c, 130, 140);
            color=new Color(0,255,0);
            g.setColor(color);
            g.setFont(Gofont);
            g.drawString("start", 170, 140);
            color=new Color(255,0,0);
            g.setColor(color);
            
        }
       
        
    }
    
    public void keyPressed(int k) {
        if(k==KeyEvent.VK_ENTER){
            if(currentChoice==3){
                Player.setName(a+""+b+""+c);
                gsm.setState(2);
            }
        }
        if(k==KeyEvent.VK_ESCAPE)gsm.setState(0);
        if(k==KeyEvent.VK_UP){
            if(currentChoice==0){
                a++;
                if(a>'Z'){
                    a='A';
                }
            }
            if(currentChoice==1){
                b++;
                 if(b>'Z'){
                    b='A';
                }
            }
            if(currentChoice==2){
                c++;
                 if(c>'Z'){
                    c='A';
                }
            }
        }
        if(k==KeyEvent.VK_DOWN){
            if(currentChoice==0){
                a--;
                 if(a<'A'){
                    a='Z';
                }
            }
            if(currentChoice==1){
                b--;
                if(b<'A'){
                   b='Z';
                }
            }
            if(currentChoice==2){
                c--;
                if(c<'A'){
                   c='Z';
                }
            }
        }
        if(k==KeyEvent.VK_LEFT){
            currentChoice--;
            if(currentChoice<0){
                currentChoice =3;
            }
        }
        
        if(k==KeyEvent.VK_RIGHT){
            currentChoice++;
            if(currentChoice>3){
                currentChoice =0;
            }
        }
    }
    public void keyReleased(int k) {}
}