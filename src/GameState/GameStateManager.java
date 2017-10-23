/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.util.ArrayList;

/**
 *
 * @author Krzysztof
 */
public class GameStateManager {
    private ArrayList<GameState> gameStates;
    private int currentState;
    
    public static final int MENUSTATE = 0;
    public static final int STARTOPTIONSTATE = 1;
    public static final int NAMESTATE = 5;
    public static final int SOLOLEVEL1 = 2;
    public static final int STAGEFINISHED = 3;
    public static final int ABOUT = 4;
    public GameStateManager(){
        gameStates = new ArrayList<GameState>();
        
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
         gameStates.add(new StartOptionState(this));
         gameStates.add(new SoloLevel1State(this));
         gameStates.add(new StageFinishedState(this));
         gameStates.add(new AboutState(this));
         gameStates.add(new NameState(this));
        // gameStates.add(new OneVsOneState(this));
    }
    
    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }
    
    public void update(){
        gameStates.get(currentState).update();
    }
    
    public void draw(java.awt.Graphics2D g){
        gameStates.get(currentState).draw(g);
    }
     
     public void keyPressed(int k){
         gameStates.get(currentState).keyPressed(k);
     }
     
     public void keyReleased(int k){
         gameStates.get(currentState).keyReleased(k);
     }
}
