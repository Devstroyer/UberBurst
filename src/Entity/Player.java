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
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class Player extends MapObject {
    //player stuff
    
    private static String name;
    private int health;
    private int bomb;
    private boolean dead;
    private int points;
    private boolean dynamiteBonus;
    private int power;
    
    private boolean dynCol;
    
    //animations
    private ArrayList<BufferedImage[]> sprites;
    
    private Animation animation;

    private final int[] numFrames ={
        2,6,6,6,6,1
    };
    
    private int facing;
    private static final int IDLE = 0;
    private static final int WALKINGR = 3;
    private static final int WALKINGL = 4;
    private static final int WALKINGU = 2;
    private static final int WALKINGD = 1;
    private BufferedImage image;
    
    private static final int Idle = 0;
    private static final int Walking = 1;
    
    
    public Player(TileMap tm){
        
        super(tm);
        power = 1;
        points=0;
        dead = false;
        currentAction = IDLE;
        animation=new Animation();
        width =15;
        height = 15;
        cwidth = 10;
        cheight = 10;
        inMove=false;
        
        dynamiteBonus=false;
        dx=0.5;
        moving=false;
        
        health = 1;
        
        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Resources/Sprites/Player/Playersml.png"));
            
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
       animation.setFrames(sprites.get(0));
       animation.setDelay(140);
        
    }
    
    public void checkDynamiteCol(Dynamite dynamite){
        dynCol = false;
        if (dynamite.planted){
           if(right && (dynamite.getx()==this.getx()+15 || dynamite.getx()==this.getx()+1+15 || dynamite.getx()==this.getx()-1+15)&& dynamite.gety()<this.gety()+2 && dynamite.gety()>this.gety()-2){
               dynCol=true;
               right = false;
           }
           if(left && (dynamite.getx()==this.getx()-15 || dynamite.getx()==this.getx()+1-15 ||dynamite.getx()==this.getx()-1-15) && dynamite.gety()>this.gety()-2 && dynamite.gety()<this.gety()+2 ){
               dynCol=true;
               left = false;
           }
           if(up && (dynamite.gety()==this.gety()-15||dynamite.gety()==this.gety()+1-15 ||dynamite.gety()==this.gety()-1-15) && dynamite.getx()>this.getx()-2 && dynamite.getx()<this.getx()+2 ){
               dynCol=true;
               up = false;
           }
           if(down && dynamite.getx()>this.getx()-2 && dynamite.getx()<this.getx()+2   && (dynamite.gety()==this.gety()+15||dynamite.gety()==this.gety()+1+15||dynamite.gety()==this.gety()-1+15)){
               dynCol=true;
               down=false;
           }
        }
    }
    
    public void checkEnemyCol(Enemy enemy){
        if(!enemy.dead){
            //MAX DOBRE
            if(getx()>enemy.x-7  && getx()<enemy.x+7 && gety()>enemy.y-7 &&  gety()<enemy.y+7){ 
                die(); 
            }
        }
        
    }
    /********************DODANE******************************/
    public void checkEnemyCol(Monster monster){
        if(!monster.checkDead()){
            
            if(getx()>monster.getx()-7  && getx()<monster.getx()+7 && gety()>monster.gety()-7 &&  gety()<monster.gety()+7){ 
                die(); 
            }
        }
        
    }
    
    public void die(){
        this.dead = true;
    }
    
    public void addPoints(int i){
        points+=i;
    }
    
    public int getPoints(){
        return points;
    }
   
    
    public void update(){
        
        if(!dead){
            checkTileMapCollision();
        }
        
        if(right) {
            if(currentAction!=WALKINGR && !movingL && !movingU && !movingD ){
                currentAction = WALKINGR;
                animation.setFrames(sprites.get(WALKINGR));
                animation.setDelay(70);
            }
        }
        else {
                if(down) {
                 if(currentAction!=WALKINGD && !movingL && !movingU && !movingR &&!dynCol){
                    currentAction = WALKINGD;
                    animation.setFrames(sprites.get(WALKINGD));
                    animation.setDelay(70);
                 }
            }
            else {
                    if(left) {
                        if(currentAction!=WALKINGL && !movingR && !movingU && !movingD &&!dynCol){
                            currentAction = WALKINGL;
                            animation.setFrames(sprites.get(WALKINGL));
                            animation.setDelay(70);
                        }
                   }
                   else {
                        if(up) {
                            if(currentAction!=WALKINGU && !movingL && !movingR && !movingD &&!dynCol){
                                currentAction = WALKINGU;
                                animation.setFrames(sprites.get(WALKINGU));
                                animation.setDelay(70);
                            }
                        }
                        else{
                                if(!moving){
                                    currentAction = IDLE;
                                    animation.setFrames(sprites.get(IDLE));
                                    animation.setDelay(70);
                                }
                            
                        }
                   }
            }
        }
        
        if(dead){
               animation.setFrames(sprites.get(5));
               animation.setDelay(70);
        }
        animation.update();
        
            
    }
    
    public void draw(Graphics2D g){
          
            g.drawImage(animation.getImage(),(int)x,(int)y,null);
        
        
    }        
    
    public boolean getDead(){
        return dead;
    }
    
    public static void setName(String s){
        name = s;
    }
    
    public static String getName(){
        return name;
    }
    
    public void bonusSpeed(){
        dx=1;
    }
    
     public void dynamiteBonus(){
        dynamiteBonus=true;
    }
     public boolean getdynamiteBonus(){
        return dynamiteBonus;
    }
    public void setPower(){
        power=2;
    }
     public int getPower(){
        return this.power;
    }
     
     public double getDx(){
         return dx;
     }
}
