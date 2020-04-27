package GameStates

import Main._
import Interface._
import Towers._
import Enemies._
import processing.core._
import scala.collection.mutable.Buffer
import scala.math._
import java.awt.event.KeyEvent._
import scala.util._

object PlayState extends GameState {
  
  var font = main.createFont("Arial", 16, true)     // small basic font
  var bigFont = main.createFont("Arial", 24, true)  // Larger font
  
  var img = loader.get("back").get               //Image for the background
 
  // Keeping track of different objects ingame
  var enemies      = Buffer[Enemy]()
  var towers       = Buffer[Towers]()
  var projectiles  = Buffer[Projectile]()
  var texts        = Buffer[Info]()
  var texts2   = Buffer[Info]()
  
  var time = 0
  
  var btns = Buffer[GameButton](new GameButton(0, 606, 64, 64, 1, "NormalTowerButton"),
                                  new GameButton(64,606, 64, 64, 2, "RangeTowerButton"),
                                  new GameButton(128, 606, 64, 64, 3, "FastTowerButton"),
                                  new GameButton(512, 606, 64, 64, 10, "playButton")                                 
                                  )
                                  
  var tooltips = Map[GameButton, HoverInfo](btns(0) -> new HoverInfo(Pos(0,574), "Normal Tower \n $50", 2),
                                             btns(1) -> new HoverInfo(Pos(64, 574), "Range Tower \n $150", 2),
                                             btns(2) -> new HoverInfo(Pos(128, 574), "Fast Tower \n $150", 2 ),
                                             btns(3) -> new HoverInfo(Pos(512, 574), "Press for next round", 1),
                                             
                                             )

//PlayState Init
  def init () = {
    EnemySpawner.init()
    enemies.clear()
    towers.clear()
    projectiles.clear()
    Player.init
    time = 0
    EnemySpawner.roundCount = 0
    GameMap.generateMap()
    
  }
  
  def terminate() = {
    
  }
  
 // see mousepresses
  def mousePress(key: Int) = {
    if (key == 37) {
      if(main.mouseY < 606) {
        Mouse.clicked
      }
      else {
        if(main.mouseX < 64) {
          Mouse.selected = 1
        }
        else if (main.mouseX > 64 && main.mouseX < 128) {
          Mouse.selected = 2
        }
        else if (main.mouseX > 128 && main.mouseX < 192) {
          Mouse.selected = 3
        }
        else if (main.mouseX > 192 && main.mouseX < 256) {
          Mouse.selected = 4
        }
        else if (main.mouseX > 256 && main.mouseX < 320) {
          Mouse.selected = 5
        }
        else if (main.mouseX > 512 && main.mouseX < 576) {
          if(!EnemySpawner.round) {
            EnemySpawner.timeToNext = 0.0
          }
        }
      }
    }
    else {      
      Mouse.selected = 0
    }
  }
  
  def keyPress = { }
  
  
  // draw the gamemap, enemies and everything
  def draw(dt: Double) = {
    
    main.image(img, 0, 0, 800, 670)
    GameMap.drawGround
    GameMap.drawWalls
    enemies.foreach(_.draw(1))
    towers.foreach(_.draw(1))
    // Tower Range Draw
    if(Mouse.isOnTower || Mouse.selected != 0) {
      towers.foreach(_.drawRange)
    }
    projectiles.foreach(_.draw(1))  
    main.textAlign(3, 3)
    main.textFont(font,40)
    main.fill(255, 140, 0)
    main.text("$ " + Player.money, 670, 635)
    main.textAlign(1,3)
    main.textFont(font, 25)
    main.text("Lives: " + Player.lives, 10, 10)
    Mouse.draw
    btns.foreach(_.draw())
    texts.foreach(_.draw)
    texts2.foreach(_.draw)
    
    
    btns.foreach{b => if(b.timer) {tooltips(b).draw}}
      
    main.pushStyle()
    main.textAlign(3, 3)
    main.fill(255, 150, 0)
    main.textFont(bigFont)
    main.text(EnemySpawner.roundText(), main.width/2, 10)
    main.text("Points: " + Player.points, main.width-75, 10)
    main.popStyle()
    
    if(EnemySpawner.isComplete) {
      main.pushMatrix()
      main.pushStyle()
      main.textAlign(3, 3)
      main.fill(255, 150, 0)
      main.textFont(bigFont)
      main.text(EnemySpawner.roundCompleteText, main.width/2, main.height/2)
      main.popStyle()
      main.popMatrix()
    }
    
  }
  
  //update the state
  def update(dt: Double) = {
    
    
    Mouse.isOnTower
    EnemySpawner.updateRound()
    btns.foreach(_.update())
    
    if(!Player.isAlive) {
      PlayState.terminate()
      main.mode = EndState
    }
    
    // Projectile Update
    projectiles.foreach(_.move)
    for(p <- projectiles.par) { 
      if(p.isExpired){  
        projectiles = projectiles.filter(x => x != p)
      }
      for (e <- enemies){  
        if(p.hasHit(e)){
          e.hit(p.dmg)
          projectiles = projectiles.filter(x => x != p)
        }
      }
    }
    
    // Enemy Update
    for(e <- enemies.par) {
      e.update
      e.checkTarget
      e.move(e.movePos)
      if(!e.isAlive){
        enemies = enemies.filter(x => x != e)
        Player.killed += 1
        Player.money += e.bounty
        texts2 += new Info(Pos(670, 575), System.nanoTime(), "$ + " + e.bounty, 255,150,0,40)
      }
    }

    // Tower Update
    for(t <- towers.par) {
      t.checkTarget
      if(!t.target.isDefined){
        t.getTarget
      }
      else{
        t.shoot
      }
    }
    
    
    
    // Text Update
    for(i <- texts.par) {
      if(i.isExpired) {              
        texts.filter(_ != i)
      }
    }
    for(i <- texts2.par) {
      if(i.isExpired) {
        texts2.filter(_ != i)
      }
    }
  }
  
 
  
  

} 
