package Main

import processing.core._
import scala.math._
import java.awt.event.KeyEvent._
import scala.util._
import GameStates._


object main extends PApplet {
  
  var f = createFont("Arial", 16, true)
  var fontColor = (255, 150, 0)
  var mode: GameState = EndState
	var last_update = System.nanoTime()
	var last_draw = System.nanoTime()
	var offset = Pos(16,16) // Some leeway for the calculations       
	var fastMode = false
 	
	def mouseLocation() = {
	  Pos(mouseX, mouseY)
	}
  

  def distance(one: Pos, another: Pos): Double = {
    sqrt(pow((another.x - one.x),2) + pow((another.y-one.y),2))
  }
	
  override def setup() : Unit = { 
  	size(800, 670)
  	frameRate(60)
  	background(100)
  	GameMap.loadMaps
    mode = MenuState
  	mode.init
  } 

  override def draw() : Unit = {
    var current_time = System.nanoTime()
      
    var updateTime = (current_time-last_update)/10e6
    mode.update(updateTime)
    last_update = System.nanoTime()
    
    var drawTime = (current_time-last_draw)/10e6
    mode.draw(drawTime)
    last_draw = System.nanoTime()
    

  }
	
  /*
   * Send the mouse button pressed to the mode
   */
  override def mousePressed = mode.mousePress(mouseButton)
        
  override def keyPressed() = {}
     
   
    
  

	
  /**
   * The main method that makes the application run and show.
   */
  def main(args: Array[String]) {
    val frame = new javax.swing.JFrame("TowerDefense")

    frame.getContentPane().add(this)
    init
    frame.setSize(800,670)
    frame.pack
    frame.setVisible(true)
    frame.setLocationRelativeTo(null)
    frame.setMinimumSize(new java.awt.Dimension(645, 540)) 
    frame.setResizable(false)
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE)
    
    var dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    var x = ((dim.getWidth() - frame.getWidth()) / 2).toInt
    var y = ((dim.getHeight() - frame.getHeight()) / 2).toInt
    frame.setLocation(x, y);
  }
}