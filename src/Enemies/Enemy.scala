package enemies

import Main._


abstract class Enemy {
 
  var maxHP: Int
  var HP: Int
  var speed: Int
  var imageName: String
  var target = ??? // implement target class
 
 
  def isAlive = HP > 0
 
 
}