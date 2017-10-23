/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class Enemy extends MapObject{
    
    private int health;
    protected boolean dead;
    
    private boolean dynCol;
    
    private boolean dynColR = false;
    private boolean dynColL = false;
    private boolean dynColU = false;
    private boolean dynColD = false;
    
    private ArrayList<BufferedImage[]> sprites;
    
    private Animation animation;

    private final int[] numFrames ={
        1,2,2,2,2,1
    };
    
    public Enemy(TileMap tm){
        
        super(tm);
        
        dead=false;
        dynColR = false;
        dynColL = false;
        dynColU = false;
        dynColD = false;
        
        dynCol=false;
        animation=new Animation();
        width =15;
        height = 15;
        cwidth = 10;
        dx=0.5;
        cheight = 10;
       
        moving=false;
        
        health = 1;
        
        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Resources/Sprites/Player/Enemysml.png"));
            
            sprites = new ArrayList<BufferedImage[]>();
            for(int i = 0;i<6;i++){
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
        animation.setFrames(sprites.get(0)); //LOL CO TO ?
        animation.setDelay(1);
        
        
        
    }
    
    public void checkDynamiteCol(Dynamite dynamite){
        
        if (dynamite.planted){
           if((dynamite.getx()==this.getx()+15 ||dynamite.getx()==this.getx()+1+15)&& dynamite.gety()==this.gety()){
               dynColR=true;
               right = false;
           }
           if(dynamite.getx()==this.getx()-15 && dynamite.gety()==this.gety() ){
               dynColL=true;
               left = false;
           }
           if( dynamite.gety()==this.gety()-15&& dynamite.getx()==this.getx()){
               dynColU=true;
               up = false;
           }
           if(dynamite.getx()==this.getx()&& (dynamite.gety()==this.gety()+15||dynamite.gety()==this.gety()+1+15)){
               dynColD=true;
               down=false;
           }
        }
    }
    
    public void die(){
        dead=true;
    }
    public void update(){
        
       //getNextPosition();
       
       
       animation.update();
       right=false;
       left=false;
       up=false;
       down=false;
       
       if(!moving){
           Random rand = new Random();
           int i = rand.nextInt(4);
           if(i==0){
               if(tileMap.getType((int)(y-30)/15,  (int)(x+15)/15)==0 && !dynColR){
                    right=true;
                    animation.setFrames(sprites.get(3));
                    animation.setDelay(70);
               }
           }
           if(i==1){
               if(tileMap.getType((int)(y-30)/15,(int)(x-15)/15)==0 && !dynColL){
                    left=true;
                    animation.setFrames(sprites.get(4));
                    animation.setDelay(70);
               }
           }
           if(i==2){
               if(tileMap.getType((int)(y-45)/15, (int)(x)/15)==0 && !dynColU){
                    up=true;
                    animation.setFrames(sprites.get(2));
                    animation.setDelay(70);
               }
           }
           if(i==3){
               
               if(tileMap.getType((int)((y-15)/15), (int)(x)/15)==0 && !dynColD){
                   
                    down=true;
                    animation.setFrames(sprites.get(1));
                    animation.setDelay(70);
               }
           }
       }
       if(dead){
           animation.setFrames(sprites.get(5));
           animation.setDelay(70);
       }
       if(!dead){
           checkTileMapCollision();
       }
       
    }
    
    
    public void draw(Graphics2D g){
        
        g.drawImage(animation.getImage(),(int)x,(int)y,null);
        
    }     
    
    public boolean getDead(){
        return dead;
    }
    
    public void setNoCollision(){
        dynColR = false;
        dynColL = false;
        dynColU = false;
        dynColD = false;
    }
    
}
