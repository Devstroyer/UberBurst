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
public class RedCrawler implements Monster {
    
    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;
    
    
    //position and vector
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    
    //dimensions
    
   protected int width;
   protected int height;
   
   
   //collision box
   protected int cwidth;
   protected int cheight;
   
   //collision
   protected double xdest;
   protected double ydest;
   protected double xtemp;
   protected double ytemp;

   //animation
   protected Animation animation;
   protected int currentAction;
   protected int previousAction;
   
   //movement
   protected boolean left;
   protected boolean right;
   protected boolean up;
   protected boolean down;
   protected boolean moving;
   protected boolean movingR;
   protected boolean movingL;
   protected boolean movingU;
   protected boolean movingD;

   //movement attributes
   protected double moveSpeed;
   protected double maxSpeed;
   protected double stopSpeed;
   
   private int health;
   public boolean dead;
    
   private boolean dynCol;
    
   private boolean dynColR = false;
   private boolean dynColL = false;
   private boolean dynColU = false;
   private boolean dynColD = false;
   
   private ArrayList<BufferedImage[]> sprites;
    

   private final int[] numFrames ={
        1,2,2,2,2,1
   };
   public RedCrawler(){
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
        cheight = 10;
        
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
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
   
    @Override
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
    
    @Override
    public void die(){
        dead=true;
    }
    @Override
    public void update(){
        
       //getNextPosition();
       if(tileMap!=null){
       
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
                    animation.setFrames(sprites.get(3)); //LOL CO TO ?
                    animation.setDelay(70);
               }
           }
           if(i==1){
               if(tileMap.getType((int)(y-30)/15,(int)(x-15)/15)==0 && !dynColL){
                    left=true;
                    animation.setFrames(sprites.get(4)); //LOL CO TO ?
                    animation.setDelay(70);
               }
           }
           if(i==2){
               if(tileMap.getType((int)(y-45)/15, (int)(x)/15)==0 && !dynColU){
                    up=true;
                    animation.setFrames(sprites.get(2)); //LOL CO TO ?
                    animation.setDelay(70);
               }
           }
           if(i==3){
               
               if(tileMap.getType((int)((y-15)/15), (int)(x)/15)==0 && !dynColD){
                   
                    down=true;
                    animation.setFrames(sprites.get(1)); //LOL CO TO ?
                    animation.setDelay(70);
               }
           }
       }
       if(dead){
           animation.setFrames(sprites.get(5)); //LOL CO TO ?
           animation.setDelay(70);
       }
       if(!dead){
           checkTileMapCollision();
       }
       }
       
    }
    
    
    @Override
    public void draw(Graphics2D g){
        
        g.drawImage(animation.getImage(),(int)x,(int)y,null);
        
    }     
    
    @Override
    public boolean getDead(){
        return dead;
    }

    @Override
   public void moveRight(){
       x+=0.5;
       if(x%15==0){
           moving=false;
           movingR=false;
       }
   }
    @Override
   public void moveLeft(){
       x-=0.5;
       if(x%15==0){
           moving=false;
           movingL=false;
       }
   }
    @Override
   public void moveUp(){
       y-=0.5;
       if(y%15==0){
           moving=false;
           movingU=false;
       }
   }
    @Override
   public void moveDown(){
       y+=0.5;
       if(y%15==0){
           moving=false;
           movingD=false;
       }
   }

    @Override
    public void checkTileMapCollision(){
       
       
       if(moving){
           if(movingR){
               moveRight();
           }
           if(movingL){
               moveLeft();
           }
           if(movingU){
               moveUp();
           }
           if(movingD){
               moveDown();
           }
       }
       
       if(right && !moving){
           if(tileMap.getType((int)(y-30)/15,  (int)(x+0.5+15)/15)==0){
               moving=true;
               movingR=true;
           }
           
           
       }
       else if(left && !moving){
           if(tileMap.getType((int)(y-30)/15,(int)(x-15)/15)==0){
                moving=true;
                movingL=true;
           }
           
       }
       else if(up && !moving){
           if(tileMap.getType((int)(y-45)/15, (int)(x)/15)==0){
               moving=true;
               movingU=true;
           }
           
       }
       else if(down && !moving){
           if(tileMap.getType((int)(y+0.5-15)/15, (int)(x)/15)==0){
               moving=true;
               movingD=true;
           }
       }
       
       
   }
   
   
    @Override
   public int getx(){return (int)x;}
    @Override
   public int gety(){return (int)y;}
    @Override
   public int getWidth(){return width;}
    @Override
   public int getHeight(){return height;}
   
   public void setPosition(double x,double y){
       this.x = x;
       this.y = y;
   }
   
   @Override
   public void setVector(double dx,double dy){
       this.dx = dx;
       this.dy = dy;
       
   }
   
    @Override
   public void setLeft(boolean b){left = b;}
    @Override
   public void setRight(boolean b){right = b;}
    @Override
   public void setUp(boolean b){up = b;}
    @Override
   public void setDown(boolean b){down = b;}
   
   @Override
   public void setTileMap(TileMap tm){
       this.tileMap=tm;
   }
   
   public boolean checkDead(){
       return dead;
   };
   
   public void setNoCollision(){
        dynColR = false;
        dynColL = false;
        dynColU = false;
        dynColD = false;
    }

    
}
