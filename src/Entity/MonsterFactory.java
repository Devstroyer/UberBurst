/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Krzysztof
 */
public class MonsterFactory {
	
   //use getShape method to get object of type shape 
   public Monster getShape(String shapeType){
      if(shapeType == null){
         return null;
      }		
      if(shapeType.equalsIgnoreCase("BLUECRAWLER")){
         return new BlueCrawler();
      } else if(shapeType.equalsIgnoreCase("REDCRAWLER")){
         return new RedCrawler();
      }
      return null;
   }
}