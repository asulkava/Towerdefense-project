package Enemies

import scala.util.Random
import Interface._
import Main._
import Enemies._
 
class SkeletonEnemy extends Enemy {
  maxHP = 60
  HP = 60
  speed = 2.0
  bounty = 30
  var pic = loader.get("skeleton").get
 
  var rand2 = new Random(System.currentTimeMillis())
  var result = GameMap.spawnerPos(rand2.nextInt(GameMap.spawnerPos.size))
  
  position = result*32
  
  def draw(scale: Int) = main.image(pic, this.position.x, this.position.y)
	 
  
}