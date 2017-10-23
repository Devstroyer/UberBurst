/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TileMap;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class Background {
    private static Background instance = null;
    public BufferedImage image;
    public BufferedImage image2;
    
    private double x;
    private double y;
    private double dx;
    private double dy;
    
    private double moveScale;
    

    protected Background(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Resources/Backgrounds/Chmury.png"));    
            image2 = ImageIO.read(getClass().getResourceAsStream("/Resources/Backgrounds/Gora.png"));   
            moveScale = 1;
          
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static Background getInstance() {
      if(instance == null) {
         instance = new Background();
      }
      return instance;
   }
    
    public void setPosition(double x, double y){
        this.x= (dx* moveScale) % GamePanel.WIDTH;
        this.y = (dy* moveScale) % GamePanel.HEIGHT;
    }
    
    public void setVector(double dx, double dy){
        this.dx =dx;
        this.dy= dy;
    }
    
    public void update(){
        this.x+=dx;
        this.y+=dy;
    }
    public void draw(Graphics2D g){
        g.drawImage(image, (int)x,(int)y, null);
        
        if(x<0){
            g.drawImage(image,(int)x+GamePanel.WIDTH,(int)y,null);
        }
        if(x>0){
            g.drawImage(image,(int)x-GamePanel.WIDTH,(int)y,null);
        }
        if(x<-GamePanel.WIDTH){
            x=0;
        }
        g.drawImage(image2, 0,0, null);
    }
}
