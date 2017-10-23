/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.TileMap;
import java.awt.Graphics2D;

/**
 *
 * @author Krzysztof
 */
public interface Monster {
    
    
    public void moveRight();
    public void moveLeft();
    public void moveUp();
    public void moveDown();
    public void checkTileMapCollision();
    public void setTileMap(TileMap tm);
    public int getx();
    public int gety();
    public int getWidth();
    public int getHeight();
    public void setPosition(double x,double y);
    public void setVector(double dx,double dy);
    public void checkDynamiteCol(Dynamite dynamite);
    public void die();
    public void update();
    public void draw(Graphics2D g);
    public boolean getDead();
    
    public void setLeft(boolean b);
    public void setRight(boolean b);
    public void setUp(boolean b);
    public void setDown(boolean b);
    public void setNoCollision();
    public boolean checkDead();
}
