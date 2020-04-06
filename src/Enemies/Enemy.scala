package enemies

import Main._
import processing.core._
import Interface._


abstract class Enemy {
 
  var maxHP: Int
  var HP: Int
  var speed: Int
  var image: PImage
  var target = ??? // implement target class
  var position: Pos
  
 
 
  def isAlive = HP > 0
 
  def draw(size: Int)
  
  def ifHit(dmg: Int) = {
    this.HP -= dmg
    
    
  }
}