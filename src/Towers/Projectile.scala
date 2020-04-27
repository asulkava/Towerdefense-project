package Towers

import Main._
import Interface._
import processing.core._
import Enemies._


class Projectile(start: Pos, target: Pos, DMG: Int, Time: Long){
  
  var position: Pos = start
  var img: Option[PImage] = None
  var speed: Int = 6
  var dmg = 50
  var movePos = (this.target - this.position).normalized()*this.speed
  
  def isExpired = timePassed > 10
  
  def timePassed = (System.nanoTime() - Time)/1e9
  
  def hasHit(e: Enemy) = this.position.distanceToAnother(e.position) < 25
  
  def pic(): PImage = {
    if(!img.isDefined) img = Some(loader.get("ammo"))
    img.get
  }
  
  // move the projectile
  def move = this.position += movePos
  
  def draw(scale: Int) = main.image(pic, this.position.x, this.position.y)
  
  
  
  
  
}