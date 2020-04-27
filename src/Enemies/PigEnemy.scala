package Enemies

import scala.util.Random
import Interface._
import Main._
import Enemies._
 
class PigEnemy extends Enemy {
  maxHP = 100
  HP = 100
  speed = 1.0
  var pic = loader.get("pig").get
 
  var rand2 = new Random(System.currentTimeMillis())
  var result = GameMap.spawnerPos(rand2.nextInt(GameMap.spawnerPos.size))
  
  position = result*32
  
  def draw(scale: Int) = main.image(pic, this.position.x, this.position.y)
  
}