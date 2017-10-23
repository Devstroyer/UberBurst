/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import TileMap.Background;
import TileMap.LevelBackground;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class StageFinishedState extends GameState {
    private BufferedImage image;
    
    public StageFinishedState(GameStateManager gsm){
        
        this.gsm = gsm;
        
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/Resources/Backgrounds/StageFinished.png"));    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void init() {}
    
    public void update() {
        
    }
    public void draw(Graphics2D g){
        g.drawImage(image,0,0,null);
    }
    
    public void keyPressed(int k) {
        if(k==KeyEvent.VK_ENTER)gsm.setState(0);
        if(k==KeyEvent.VK_ESCAPE)gsm.setState(0);
        if(k==KeyEvent.VK_SPACE)gsm.setState(0);
    }
    public void keyReleased(int k) {}
}
