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
public class Bonus {
    
    
    private BufferedImage[] bonusType;
    private int howMany;
    private int[][] tab;
    
    public Bonus(){
        bonusType=new BufferedImage[7];
        tab = new int[20][20];
        howMany=0;
         try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Resources/Tilesets/Bonuses.png"));
            
            bonusType[0] = spritesheet.getSubimage(0,0,15,15);
            bonusType[1] = spritesheet.getSubimage(15,0,15,15);
            bonusType[2] = spritesheet.getSubimage(30,0,15,15);
            bonusType[3] = spritesheet.getSubimage(45,0,15,15);
            bonusType[4] = spritesheet.getSubimage(60,0,15,15);
            bonusType[5] = spritesheet.getSubimage(75,0,15,15);
            bonusType[6] = spritesheet.getSubimage(90,0,15,15);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    public void add(int x, int y, int type){
        tab[(x+15)/15][(y-45)/15]=type;
    }
    
    public void checkPlayerCollision(Player player){
        double x=player.getx();
        double y=player.gety();
        
        if(x%15==0 && y%15==0 && x>0 && y>0){
                if(tab[(int)(x)/15][(int)(y-45)/15]==1){
                    player.addPoints(10);
                }
                if(tab[(int)(x)/15][(int)(y-45)/15]==2){
                    player.addPoints(50);
                }
                if(tab[(int)(x)/15][(int)(y-45)/15]==3){
                    player.addPoints(100);
                }
                if(tab[(int)(x)/15][(int)(y-45)/15]==4){
                    player.addPoints(250);
                }
                if(tab[(int)(x)/15][(int)(y-45)/15]==5){
                    player.bonusSpeed();
                }
                if(tab[(int)(x)/15][(int)(y-45)/15]==6){
                    player.dynamiteBonus();;
                }
                if(tab[(int)(x)/15][(int)(y-45)/15]==7){
                    player.setPower();
                }
                tab[(int)(x)/15][(int)(y-45)/15]=0;
        }
    }
          
    
    
    
    
     public void draw(Graphics2D g){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(tab[i][j]==1){
                    g.drawImage(bonusType[0],i*15,j*15+45,null);
                }
                if(tab[i][j]==2){
                    g.drawImage(bonusType[1],i*15,j*15+45,null);
                }
                if(tab[i][j]==3){
                    g.drawImage(bonusType[2],i*15,j*15+45,null);
                }
                if(tab[i][j]==4){
                    g.drawImage(bonusType[3],i*15,j*15+45,null);
                }
                if(tab[i][j]==5){
                    g.drawImage(bonusType[4],i*15,j*15+45,null);
                }
                if(tab[i][j]==6){
                    g.drawImage(bonusType[5],i*15,j*15+45,null);
                }
                if(tab[i][j]==7){
                    g.drawImage(bonusType[6],i*15,j*15+45,null);
                }
            }
        }
        
    }   
    
}
