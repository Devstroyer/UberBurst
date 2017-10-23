/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class Vault {
    
    protected boolean action;
    protected boolean opened;
    protected boolean played;
    protected boolean playedOnce;
    protected int counter;
    
    protected int x;
    protected int y;
    
     private ArrayList<BufferedImage[]> sprites;
    
    private Animation animation;

    private final int[] numFrames ={
        1,6,1
    };
    
    
    public Vault(){
       animation = new Animation();
       sprites =new ArrayList<BufferedImage[]>();
       try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Resources/Sprites/Player/Vault.png"));
            
            sprites = new ArrayList<BufferedImage[]>();
            for(int i = 0;i<3;i++){
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for(int j=0;j<numFrames[i];j++){
                    bi[j]=spritesheet.getSubimage(j*15,i*15,15,15);
                    
                }
       
                sprites.add(bi);
            }
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
       opened=false;
       action=false;
       played=false;
       animation.setFrames(sprites.get(0));
       animation.setDelay(140);
       playedOnce=false;
       x=285;
       y=210;
       
    }
    
    public void open(){
        if(!opened){
            opened = true;
            action = true;
        }
        
    }
    
    
    public void update(){
        if(action) { 
            if(!played){
                played=true;
                animation.setFrames(sprites.get(1));
                animation.setDelay(140);
            }
            if(!playedOnce && animation.getFrame()==5){
                playedOnce=true;
            }
        }
        if(playedOnce && action){
            animation.setFrames(sprites.get(2));
            animation.setDelay(140);
            action=false;
        }
        animation.update();
    }
    
    public void draw(Graphics2D g){
        g.drawImage(animation.getImage(),x,y,null);
    }
}
