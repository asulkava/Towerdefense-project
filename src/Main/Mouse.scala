package Main

import GameStates._
import Towers._
import Enemies._
import Interface._


object Mouse {
  
  var buildAllowed = true
  
  var selected: Int = 0
  
  var towerNum = Map[Int, Buildable](1 -> new NormalTower(Pos(0,0)),
                                     2 -> new RangeTower(Pos(0,0)),
                                     3 -> new FastTower(Pos(0,0))
                                    )
                                  
// Check if you can build
  def buildPossible: Boolean = {
    if (selected == 0) false
    
    else if(towerNum(selected).isInstanceOf[Towers]) {
      PlayState.towers.forall(x => x.position != GameMap.getTile(main.mouseLocation()).location) && GameMap.getTile(main.mouseLocation()).solid
    }
    
    else !GameMap.getTile(main.mouseLocation()).solid
      
    
  }
  
  // check mouse clicks
  def clicked() = {
    if(buildPossible) {
      selected match {
         
        case 0 => // do nothing if no tower is selected
          
        case 1 => if (Player.money - towerNum(selected).cost >= 0 ) {
                  PlayState.towers += new NormalTower(GameMap.getTile(main.mouseLocation()).location)
                  build(selected)
                  }
                  else noMoney
        
        case 2 => if (Player.money - towerNum(selected).cost >= 0 ) {
                  PlayState.towers += new RangeTower(GameMap.getTile(main.mouseLocation()).location)
                  build(selected)                  
                  }
                  else noMoney
                  
        case 3 => if (Player.money - towerNum(selected).cost >= 0) {
                  PlayState.towers += new FastTower(GameMap.getTile(main.mouseLocation()).location)
                  build(selected)
                  }
                  else noMoney  
      }
    }
    else if (selected != 0) PlayState.texts += new Info(main.mouseLocation(), System.nanoTime(), "Invalid position", 150,150,0,14)
     
  }
  
  // build the tower
  def build(number :Int) = {
    Player.money -= towerNum(number).cost
    Player.spent += towerNum(number).cost
    PlayState.texts2 += new Info(Pos(670, 575), System.nanoTime(), "$ - " + towerNum(number).cost, 255,150,0,40)
  }
  
  // not enough money text
  def noMoney = PlayState.texts += new Info(main.mouseLocation(), System.nanoTime(), "Not Enough money for this tower.", 255,0,0,14)
  
  // hovering on tower
  def isOnTower: Boolean = {
    PlayState.towers.exists( x => (x.position + main.offset).distanceToAnother(main.mouseLocation()) < 10  )
  }
  
   
  
  def draw = {
    if (selected != 0 && main.mouseLocation().y < 606) {  // Draw selected tower
      main.ellipseMode(2)
      if (buildPossible) {                                // Draw range
        main.pushMatrix()
        main.fill(0, 255, 0, 100)
        main.rect(GameMap.getTile(main.mouseLocation()).x*32, GameMap.getTile(main.mouseLocation()).y*32 ,32 ,32)
        main.ellipse(GameMap.getTile(main.mouseLocation).x*32+main.offset.x, GameMap.getTile(main.mouseLocation).y*32+main.offset.x,towerNum(selected).range.toFloat, towerNum(selected).range.toFloat)
        main.popMatrix()
      }
    }
  }
}