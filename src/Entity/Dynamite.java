/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class Dynamite extends MapObject {
    
    protected boolean planted;
    protected boolean blowing;
    protected BufferedImage image;
    
    public boolean rBonus;
    public boolean uBonus;
    public boolean dBonus;
    public boolean lBonus;
    
    public boolean rDraw;
    public boolean uDraw;
    public boolean dDraw;
    public boolean lDraw;
    
    public boolean rBlocked;
    public boolean uBlocked;
    public boolean dBlocked;
    public boolean lBlocked;
    //animations
    private ArrayList<BufferedImage[]> sprites;
    
    private Animation animation;

    private final int[] numFrames ={
        4,2
    };
    
    protected int power;
    protected int timer;
    protected int blowingTime;
    
    public Dynamite(TileMap tm) {
       super(tm);
       
       rBlocked=true;
       uBlocked=true;
       dBlocked=true;
       lBlocked=true;
        
       rBonus=false;
       lBonus=false;
       uBonus=false;
       dBonus=false;

       
       animation=new Animation();
       sprites =new ArrayList<BufferedImage[]>();
       try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Resources/Sprites/Player/dynamitesprites.png"));
            
            sprites = new ArrayList<BufferedImage[]>();
            for(int i = 0;i<2;i++){
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
       
        this.tileMap=tileMap;
        this.timer =180;
        this.blowingTime=60;
        planted = false;
    }
    
    
    
    public void plant(int x, int y,int power){
        if(planted==false){
            planted = true;
            if(x%15>7)this.x = x+(15-(x%15));
            else{
                this.x=x-(x%15);
            }
            
            if(y%15>7)this.y = y+(15-(y%15));
            else{
                this.y=y-(y%15);
            }
            this.power = power;
        }
       
    }
    
    public void blow(){
        int b=4;
        
        rBlocked=true;
        uBlocked=true;
        dBlocked=true;
        lBlocked=true;
        
        if(tileMap.getModel((int)(y-30)/15, (int)(x+15)/15)==2){
            Random rand = new Random();
            int i = rand.nextInt(16);
            if(i<b){
                rBonus=true;
            }
        }
        if(tileMap.getModel((int)(y-30)/15,(int)(x-15)/15)==2){
            Random rand = new Random();
            int i = rand.nextInt(16);
            if(i<b){
                lBonus=true;
            }
        }
        if(tileMap.getModel((int)(y-45)/15, (int)(x)/15)==2){
            Random rand = new Random();
            int i = rand.nextInt(16);
            if(i<b){
                
                uBonus=true;
            }
        }
        if(tileMap.getModel((int)(y-15)/15, (int)(x)/15)==2){
            Random rand = new Random();
            int i = rand.nextInt(16);
            if(i<b){
                dBonus=true;
            }
        }
        if(tileMap.getModel((int)(y-30)/15, (int)(x+15)/15)==0){           
            rBlocked=false;
        }
        if(tileMap.getModel((int)(y-30)/15,(int)(x-15)/15)==0){
            lBlocked=false;
        }
        if(tileMap.getModel((int)(y-45)/15, (int)(x)/15)==0){
            uBlocked=false;
        }
        if(tileMap.getModel((int)(y-15)/15, (int)(x)/15)==0){
            dBlocked=false;
        }
        tileMap.setType((int)(y-30)/15, (int)(x+15)/15);
        tileMap.setType((int)(y-30)/15,(int)(x-15)/15);
        tileMap.setType((int)(y-45)/15, (int)(x)/15);
        tileMap.setType((int)(y-15)/15, (int)(x)/15);
        
        if(power==2){
           if(!rBlocked){
            tileMap.setType((int)(y-30)/15, (int)(x+30)/15);
            }
            if(!lBlocked){
                tileMap.setType((int)(y-30)/15,(int)(x-30)/15);
            }
            if(!uBlocked){
                tileMap.setType((int)(y-60)/15, (int)(x)/15);
            }
            if(!dBlocked){
                tileMap.setType((int)(y)/15, (int)(x)/15);
            } 
        }
        
    }
    
    public void checkTimer(){
        if(planted && !blowing){
            timer--;
            if(timer==0){
                blow();
                timer=180;
                blowing=true;
                
                animation.setFrames(sprites.get(1));
                animation.setDelay(120);
            }
        }
    }
    
    public void checkBlowing(){
        blowingTime--;
        if(blowingTime==0){
            animation.setFrames(sprites.get(0));
            animation.setDelay(140);
            blowingTime=60;
            planted=false;
            blowing=false;            
        }
    }
    
    public void checkDeath(Enemy enemy){
        if(blowing){
            if(power==1){
                if(enemy.getx()>this.x-7  && enemy.getx()<this.x+7 && enemy.gety()>this.y-23 &&  enemy.gety()<this.y+23){ 
                    enemy.die();
                    }
                if(enemy.getx()>this.x-23  && enemy.getx()<this.x+23 && enemy.gety()>this.y-7 &&  enemy.gety()<this.y+7){
                    enemy.die();
                }      
            }
            if(power==2){
                if(enemy.getx()>this.x-7  && enemy.getx()<this.x+7 && enemy.gety()>this.y-38 &&  enemy.gety()<this.y+38){ 
                    if(!uBlocked && enemy.gety()>y-38 && enemy.gety()<this.y+23){
                        enemy.die();
                    }
                    if(!dBlocked && enemy.gety()>y-23 && enemy.gety()<this.y+38){
                        enemy.die();
                    }
                }
                if(enemy.getx()>this.x-38  && enemy.getx()<this.x+38 && enemy.gety()>this.y-7 &&  enemy.gety()<this.y+7){
                    if(!lBlocked && enemy.getx()>x-38 && enemy.getx()<this.x+23){
                        enemy.die();
                    }
                    if(!rBlocked && enemy.getx()>x-23 && enemy.getx()<this.x+38){
                        enemy.die();
                    }
                }      
            }
        }
    }
    
    public void checkDeath(Player player){
        if(blowing){
            if(power==1){
                if(player.getx()>this.x-7  && player.getx()<this.x+7 && player.gety()>this.y-23 &&  player.gety()<this.y+23){ 
                player.die();
                }
                if(player.getx()>this.x-23  && player.getx()<this.x+23 && player.gety()>this.y-7 &&  player.gety()<this.y+7){
                    player.die();
                }      
            }
            if(power==2){
                if(player.getx()>this.x-7  && player.getx()<this.x+7 && player.gety()>this.y-38 &&  player.gety()<this.y+38){
                    if(!uBlocked && player.gety()>y-38 && player.gety()<this.y+23){
                        player.die();
                    }
                    if(!dBlocked && player.gety()>y-23 && player.gety()<this.y+38){
                        player.die();
                    }
                
                }
                if(player.getx()>this.x-38  && player.getx()<this.x+38 && player.gety()>this.y-7 &&  player.gety()<this.y+7){
                    if(!lBlocked && player.getx()>x-38 && player.getx()<this.x+23){
                        player.die();
                    }
                    if(!rBlocked && player.getx()>x-23 && player.getx()<this.x+38){
                        player.die();
                    }
                }      
            }
            
        }
    }
    public void checkDeath(Monster monster){
        if(blowing){
            if(power==1){
                if(monster.getx()>this.x-7  && monster.getx()<this.x+7 && monster.gety()>this.y-23 &&  monster.gety()<this.y+23){ 
                    monster.die();
                }
                if(monster.getx()>this.x-23  && monster.getx()<this.x+23 && monster.gety()>this.y-7 &&  monster.gety()<this.y+7){
                            monster.die();
                } 
            }
             if(power==2){
                if(monster.getx()>this.x-7  && monster.getx()<this.x+7 && monster.gety()>this.y-38 &&  monster.gety()<this.y+38){ 
                    if(!uBlocked && monster.gety()>y-38 && monster.gety()<this.y+23){
                        monster.die();
                    }
                    if(!dBlocked && monster.gety()>y-23 && monster.gety()<this.y+38){
                        monster.die();
                    }
                }
                if(monster.getx()>this.x-38  && monster.getx()<this.x+38 && monster.gety()>this.y-7 &&  monster.gety()<this.y+7){
                    if(!lBlocked && monster.getx()>x-38 && monster.getx()<this.x+23){
                        monster.die();
                    }
                    if(!rBlocked && monster.getx()>x-23 && monster.getx()<this.x+38){
                        monster.die();
                    }
                }      
            }
        }
        
    }
    
    
    
    public void update(){
        
            checkTimer();
            
            if(blowing){
                checkBlowing();
            }
            animation.update();
                    
    }
    
    public void draw(Graphics2D g){
        if(planted){
            g.drawImage(animation.getImage(),(int)x,(int)y,null);
        }
        if(blowing){
            g.drawImage(animation.getImage(),(int)x,(int)y,null);
            if(tileMap.getType((int)(y-30)/15, (int)(x-15)/15)==0){
                g.drawImage(animation.getImage(),(int)x-15,(int)y,null);     
            }
            if(tileMap.getType((int)(y-30)/15, (int)(x+15)/15)==0){
                g.drawImage(animation.getImage(),(int)x+15,(int)y,null);
            }
            if(tileMap.getType((int)(y-45)/15, (int)(x)/15)==0){
                g.drawImage(animation.getImage(),(int)x,(int)y-15,null);
            }
            if(tileMap.getType((int)(y-15)/15, (int)(x)/15)==0){
                g.drawImage(animation.getImage(),(int)x,(int)y+15,null);
            }
            if(power==2){
                if(!uBlocked){
                g.drawImage(animation.getImage(),(int)x,(int)y-30,null);
                }
                if(!dBlocked){
                    g.drawImage(animation.getImage(),(int)x,(int)y+30,null);
                }
                if(!rBlocked){
                    g.drawImage(animation.getImage(),(int)x+30,(int)y,null);
                }
                if(!lBlocked){
                    g.drawImage(animation.getImage(),(int)x-30,(int)y,null);
                }
            }
            
  
        }
    }
    
    public boolean getPlanted(){
       return planted;
    }
    
    public void setTimer(int i){
        this.timer = i;
    }
    public void checkSecond(Dynamite dynamite){
        if (this.blowing){
            if(power==1){
                if(dynamite.getPlanted() && !dynamite.blowing && this.x==dynamite.getx() && (this.y==(dynamite.gety()-15) || this.y==(dynamite.gety()+15))){
                    if(dynamite.timer>1){
                        dynamite.timer=1;
                    }

                }
                if(dynamite.getPlanted() && !dynamite.blowing && this.y==dynamite.gety() && (this.x==(dynamite.getx()-15) ||this.x==(dynamite.getx()+15))){
                    if(dynamite.timer>1){
                        dynamite.timer=1;
                    }
                }
            }
            if(power==2){
                if(dynamite.getPlanted() && !dynamite.blowing && this.x==dynamite.getx()){
                        if(dynamite.gety()==this.y+15 || dynamite.gety()==this.y-15 ){
                            if(dynamite.timer>1){
                                dynamite.timer=1;
                            }
                        }
                        if(dynamite.gety()==this.y+30 && !dBlocked){
                            if(dynamite.timer>1){
                                dynamite.timer=1;
                            }
                        } 
                         if(dynamite.gety()==this.y-30 && !uBlocked){
                            if(dynamite.timer>1){
                                dynamite.timer=1;
                            }
                        } 
                    
                    

                }
                if(dynamite.getPlanted() && !dynamite.blowing && this.y==dynamite.gety()){
                    if(dynamite.getx()==this.x+15 || dynamite.getx()==this.x-15 ){
                            if(dynamite.timer>1){
                                dynamite.timer=1;
                            }
                        }
                        if(dynamite.getx()==this.x+30 && !rBlocked){
                            if(dynamite.timer>1){
                                dynamite.timer=1;
                            }
                        } 
                         if(dynamite.getx()==this.x-30 && !lBlocked){
                            if(dynamite.timer>1){
                                dynamite.timer=1;
                            }
                        } 
                }
            }
        }
    }
    
}
