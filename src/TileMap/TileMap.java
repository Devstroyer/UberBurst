/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class TileMap {

    
    
    //map
    
    private int [][] map;
    private int tileSize;
    private int numCols;
    private int width;
    private int height;
    
    private Random rand;
    
    private BufferedImage tileset;
    private BufferedImage subimage;
   
    private Tile[] tiles;
    
    public TileMap(int tileSize){
        this.tileSize=tileSize;
    }
    
    public void loadTiles(String s){
        try{
            tileset=ImageIO.read(getClass().getResourceAsStream(s));

            tiles = new Tile[4];
            //BufferedImage subimage;
            subimage= tileset.getSubimage(0, 0, 15, 15);
            tiles[1] = new Tile(subimage,1);
            subimage= tileset.getSubimage(15, 0, 15, 15);
            tiles[3] = new Tile(subimage,1);
            subimage= tileset.getSubimage(0, 15, 15, 15);
            tiles[2] = new Tile(subimage,1);
            subimage= tileset.getSubimage(15, 15, 15, 15);
            tiles[0] = new Tile(subimage,0);
            //subimage = tiles[1].getImage();
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(String s){
        try{
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            map = new int[14][21];
            rand = new Random();
            
            String delims ="\\s+";
            for(int row = 0;row <14 ; row++){
                String line = br.readLine();
                String[]tokens = line.split(delims);
                for(int col=0;col<21;col++){
                    int i = rand.nextInt(4);
                    map[row][col]= Integer.parseInt(tokens[col]);
                    if(map[row][col]==2){
                        if(i<1){
                            map[row][col]=0;
                        }
                    }
                }
            
            }
            map[12][19]=0;
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public int getTileSize(){ return tileSize;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
   
    
    public int getModel(int row, int col){
        return map[row][col];
    }
    
    public int getType(int row, int col){
        int rc = map[row][col];
        return tiles[rc].getType();
    }
    public void setType(int row,int col){
        if(map[row][col]==2)
        map[row][col]=0;
    }
    
    
    public void draw(Graphics2D g){

        for(int row=0;row<14;row++){
            for(int col=0;col<21;col++){
                try{
                    g.drawImage(tiles[map[row][col]].getImage(),col*tileSize, 30+row*tileSize,null);
                }
                catch(Exception e){
                   // e.printStackTrace();
                }
            }
        }
    }
}
