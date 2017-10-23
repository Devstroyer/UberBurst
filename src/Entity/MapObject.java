/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.Tile;
import TileMap.TileMap;
import java.awt.Rectangle;

/**
 *
 * @author Krzysztof
 */
public abstract class MapObject {
    
    
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
   
   protected boolean inMove;

   //movement attributes
   protected double moveSpeed;
   protected double maxSpeed;
   protected double stopSpeed;
   
   //constructor
   public MapObject(TileMap tm){
       tileMap = tm;
       tileSize = tm.getTileSize();
   }

   
   
   
   public void moveRight(){
       x+=dx;
       if(x%15==0){
           moving=false;
           movingR=false;
       }
   }
   public void moveLeft(){
       x-=dx;
       if(dx==1 && x%1!=0){
           x+=0.5;
       }
       if(x%15==0){
           moving=false;
           movingL=false;
       }
   }
   public void moveUp(){
       y-=dx;
       if(dx==1 && y%1!=0){
           y+=0.5;
       }
       if(y%15==0){
           moving=false;
           movingU=false;
       }
   }
   public void moveDown(){
       y+=dx;
       
       if(y%15==0){
           moving=false;
           movingD=false;
       }
   }
   
   
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
   
   
   public int getx(){return (int)x;}
   public int gety(){return (int)y;}
   public int getWidth(){return width;}
   public int getHeight(){return height;}
   
   public void setPosition(double x,double y){
       this.x = x;
       this.y = y;
   }
   
   
   public void setLeft(boolean b){left = b;}
   public void setRight(boolean b){right = b;}
   public void setUp(boolean b){up = b;}
   public void setDown(boolean b){down = b;}
}
