/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.Animation;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class AboutState extends GameState{
    
     private Animation animation;
     private BufferedImage image;
     private final int[] numFrames ={3};
     
     private ArrayList<BufferedImage[]> sprites;
    
    public AboutState(GameStateManager gsm){
       animation=new Animation();
       sprites =new ArrayList<BufferedImage[]>();
        
        this.gsm = gsm;
        
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/Resources/Backgrounds/About.png"));    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Resources/Backgrounds/LanternSprites.png"));
            
            sprites = new ArrayList<BufferedImage[]>();
            for(int i = 0;i<1;i++){
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for(int j=0;j<numFrames[i];j++){
                    bi[j]=spritesheet.getSubimage(j*80,i*80,80,150);
                    
                }
       
                sprites.add(bi);
            }
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        animation.setFrames(sprites.get(0));
        animation.setDelay(300);
        
        
    }
    
    public void init() {}
    
    public void update() {
        animation.update();     
    }
    
    public void draw(Graphics2D g){
        g.drawImage(image,0,0,null);
        g.drawImage(animation.getImage(),240,90,null);
    }
    
    public void keyPressed(int k) {
        if(k==KeyEvent.VK_ENTER)gsm.setState(0);
        if(k==KeyEvent.VK_ESCAPE)gsm.setState(0);
        if(k==KeyEvent.VK_SPACE)gsm.setState(0);
    }
    public void keyReleased(int k) {}
}
