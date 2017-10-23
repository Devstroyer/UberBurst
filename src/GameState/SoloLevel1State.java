/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.BlueCrawler;
import Entity.Bonus;
import Entity.Dynamite;
import Entity.Enemy;
import Entity.Monster;
import Entity.MonsterFactory;
import Entity.Player;
import Entity.RedCrawler;
import Entity.Vault;
import TileMap.LevelBackground;
import TileMap.Tile;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Krzysztof
 */
public class SoloLevel1State extends GameState{
    
    
    private LevelBackground bg;
    private TileMap tileMap;
    
    private BufferedImage gameOver;
    
    private long time;
    
    private boolean finished;
    
    Color titleColor;
    Font titleFont;
    
    private Player player;
    private Dynamite dynamite;
    private Dynamite dynamite2;
    private Enemy enemy;
    private Enemy enemy2;
    private Bonus bonus;
    private Vault vault;
    private boolean newG;
    
    private Monster monster;
    private Monster monster2;
    
    public SoloLevel1State(GameStateManager gsm){
        
        
        titleColor = new Color (5,225,0);
        titleFont = new Font("Arial", Font.PLAIN, 12);
        this.gsm = gsm;
        try{
            bg = new LevelBackground();
            gameOver=ImageIO.read(getClass().getResourceAsStream("/Resources/Backgrounds/GameOver.png"));    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
       
        init();
        
    }
    
    public void init(){
        tileMap = new TileMap(15);
        tileMap.loadTiles("/Resources/Tilesets/BlocksSml2.png");
        tileMap.loadMap("/Resources/Maps/map1.txt");
        
        MonsterFactory monsterFactory = new MonsterFactory();
        monster  = monsterFactory.getShape("REDCRAWLER");
        monster2 = monsterFactory.getShape("BLUECRAWLER");
        
        monster.setTileMap(tileMap);
        monster.setPosition(150, 165);
        
        monster2.setTileMap(tileMap);
        monster2.setPosition(210, 75);
       
        
        player = new Player(tileMap);
        dynamite = new Dynamite(tileMap);
        dynamite2 = new Dynamite(tileMap);
        enemy = new Enemy(tileMap);
        enemy.setPosition(150, 165);
        enemy2 = new Enemy(tileMap);
        enemy2.setPosition(210, 75);
        player.setPosition(15, 45);
        
        time = System.nanoTime();
        bonus =new Bonus();
        vault = new Vault();
        finished=false;
        
        
    }
    
    public void update(){
        
           enemy.setNoCollision();
           enemy2.setNoCollision();
           monster.setNoCollision();
           monster2.setNoCollision();
           
           bg.update(); 
           player.checkDynamiteCol(dynamite);
           
           monster.checkDynamiteCol(dynamite);
           monster2.checkDynamiteCol(dynamite);

           player.checkDynamiteCol(dynamite2);
           
           monster.checkDynamiteCol(dynamite2);
           monster2.checkDynamiteCol(dynamite2);
           
           
           enemy.checkDynamiteCol(dynamite);
           enemy2.checkDynamiteCol(dynamite);
           
           enemy.checkDynamiteCol(dynamite2);
           enemy2.checkDynamiteCol(dynamite2);
           
           player.update();
           enemy.update();   
           enemy2.update();
           monster2.update();
           monster.update();
           
           player.checkEnemyCol(enemy);
           player.checkEnemyCol(enemy2);
           player.checkEnemyCol(monster);
           player.checkEnemyCol(monster2);
           
         
           
           
           
           dynamite.update();
           dynamite.checkSecond(dynamite2);
           dynamite2.update();
           dynamite2.checkSecond(dynamite);
           
           
          dynamite.checkDeath(player);
          dynamite2.checkDeath(player);
           
          dynamite.checkDeath(monster);
          dynamite.checkDeath(monster2);
           
          dynamite2.checkDeath(monster);
          dynamite2.checkDeath(monster2);
            
          dynamite.checkDeath(enemy2);
          dynamite2.checkDeath(enemy2);
          
          dynamite.checkDeath(enemy);
          dynamite2.checkDeath(enemy);
           
           if(dynamite.rBonus){
               dynamite.rBonus=false;
               int i=checkBonus();
               bonus.add(dynamite.getx(),dynamite.gety(),i+1);
               //i+1
           }
           if(dynamite.lBonus){
               dynamite.lBonus=false;
               int i=checkBonus();
               bonus.add(dynamite.getx()-30,dynamite.gety(),i+1);
           }
           if(dynamite.uBonus){
               dynamite.uBonus=false;
               int i=checkBonus();
               bonus.add(dynamite.getx()-15,dynamite.gety()-15,i+1);
           }
           if(dynamite.dBonus){
               dynamite.dBonus=false;
               int i=checkBonus();
               bonus.add(dynamite.getx()-15,dynamite.gety()+15,i+1);
           }
           
           if(dynamite2.rBonus){
               dynamite2.rBonus=false;
               int i=checkBonus();
               bonus.add(dynamite2.getx(),dynamite2.gety(),i+1);
           }
           if(dynamite2.lBonus){
               dynamite2.lBonus=false;
               int i=checkBonus();
               bonus.add(dynamite2.getx()-30,dynamite2.gety(),i+1);
           }
           if(dynamite2.uBonus){
               dynamite2.uBonus=false;
               int i=checkBonus();
               bonus.add(dynamite2.getx()-15,dynamite2.gety()-15,1+i);
           }
           if(dynamite2.dBonus){
               dynamite2.dBonus=false;
               int i=checkBonus();
               bonus.add(dynamite2.getx()-15,dynamite2.gety()+15,1+i);
           }
           
           bonus.checkPlayerCollision(player);
           
           if(enemy.getDead() && enemy2.getDead() && monster.getDead() && monster2.getDead()){
               vault.open();
               vault.update();
               if(player.getx()>283 && player.gety()>207){
                   finished=true;
               }
           }
           
           if(finished){
               finished=false;
               player.setPosition(15, 45);
               gsm.setState(3);
               
           }
        
    }
           

    
    public void draw(Graphics2D g){
        
        bg.draw(g);
        vault.draw(g);
        g.setColor(titleColor);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString(Player.getName(), 0, 10);
        g.drawString("Points:", 0, 20);
        g.drawString(""+player.getPoints(), 50, 20);
        g.drawString("Time: "+(System.nanoTime()-time)/1000000000,120,10);
        
        tileMap.draw(g);
        bonus.draw(g);
        enemy.draw(g);
        enemy2.draw(g);
        
        monster.draw(g);
        monster2.draw(g); 
        
        dynamite.draw(g);
        dynamite2.draw(g);
        player.draw(g);
        if(player.getDead()){
            g.drawImage(gameOver,0,0,null);
        }
    }
    
    public int checkBonus(){
        Random rand = new Random();
        int i = rand.nextInt(7);
        if(player.getDx()==1 && i==4){
            i=5;
        }
        if(player.getdynamiteBonus() && i==5){
            i=6;
        }
        if(player.getPower()==2 && i==6){
            i=rand.nextInt(4);
        }
        return i;
    }
    public void keyPressed(int k){
        if(k==KeyEvent.VK_LEFT)player.setLeft(true);
        if(k==KeyEvent.VK_RIGHT)player.setRight(true);
        if(k==KeyEvent.VK_DOWN)player.setDown(true);
        if(k==KeyEvent.VK_UP)player.setUp(true);
        if(k==KeyEvent.VK_SPACE){
            if(!player.getDead()){
                
                if(!dynamite.getPlanted()){
                    dynamite.plant(player.getx(), player.gety(), player.getPower());
                }
                else{
                    if(player.getdynamiteBonus()){
                        dynamite2.plant(player.getx(), player.gety(), player.getPower());
                    }
                    
                }
                
            }
        }
        if(k==KeyEvent.VK_Q){}
           
        if(k ==KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        if(k ==KeyEvent.VK_BACK_SPACE){
            gsm.setState(0);
        }
    }
    public void keyReleased(int k){
        if(k==KeyEvent.VK_LEFT)player.setLeft(false);
        if(k==KeyEvent.VK_RIGHT)player.setRight(false);
        if(k==KeyEvent.VK_DOWN)player.setDown(false);
        if(k==KeyEvent.VK_UP)player.setUp(false);
        if(k==KeyEvent.VK_SPACE){
             if(player.getDead())gsm.setState(0);
        }
    }
}
