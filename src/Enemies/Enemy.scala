package Enemies

import Interface._
import Main._
import GameStates._
import scala.math._
import scala.util._
import processing.core._



abstract class Enemy {
  var maxHP: Int = 100
  var HP: Int = maxHP
  var speed: Double = 1.0
  var position: Pos = Pos(0,0)
  var bounty: Int = 10
  var loadedImage : Option[PImage] = None
  var currentTarget = 0
  var target = Path.points(currentTarget)
  var rand = new Random
  var frame = 0
  
  def angle : Double = 0.0
  
  def update = {
    if (this.frame > 0) {
      this.frame -= 1
    }
  }
  
  def isAlive:Boolean = HP > 0
  
  def draw(scale: Int)
  
	def image() = {
	  if(!loadedImage.isDefined) {
	    loadedImage = Some(loader.get("pig"))
	  }
	  loadedImage.get
	}
  
  // check if the enemy is on target
  def checkTarget = {
    if ( this.currentTarget < Path.points.length && this.position.distanceToAnother(this.target) < 10) {
      if(currentTarget == Path.points.length-1) 
      {  
        this.HP = 0
        Player.lives -= 1
      }
      else 
      {  
        currentTarget = currentTarget + 1
        target = Path.points(currentTarget)
      }
    }
  }
   
  
  //move the enemy
  def movePos = (this.target - this.position).normalized()*this.speed
 
  def move(pos: Pos) = {
    this.position += pos
  }
 
  
  // hit the enemy
  def hit(DMG: Int) = {
    this.speed -= 0.1
    this.HP -= DMG
    if (this.frame <= 0) this.frame = 10
  }
}