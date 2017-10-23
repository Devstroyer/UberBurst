/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TileMap;

import Main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class LevelBackground {
    
   public BufferedImage image;

   
   public LevelBackground(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Resources/Backgrounds/surface2.png"));    
         
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
   public void update(){}
   public void draw(Graphics2D g){
       g.setColor(Color.black);
       g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
       g.drawImage(image,0,0,null);
       
  
   }
}
