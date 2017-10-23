/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TileMap;

import java.awt.image.BufferedImage;

/**
 *
 * @author Krzysztof
 */
public class Tile {
    private BufferedImage image;
    private int type;
    
    public Tile(BufferedImage image, int type){
        this.image = image;
        this.type=type;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
