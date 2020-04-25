package Towers

import Main._
import Interface._
import processing.core._
import Enemies._


class Projectile(start: Pos, target: Pos, DMG: Int, Time: Long){
  
  var position: Pos = start
  var img: Option[PImage] = None
  var speed: Int = 4
  var dmg = DMG
  var movePos = (this.target - this.position).normalized()*this.speed
  
  def isExpired = timePassed > 5
  
  def timePassed = (System.nanoTime() - Time) 
  
  def hasHit(e: Enemy) = this.position.distanceToAnother(e.position) < 15
  
  def pic(): PImage = {
    if(!img.isDefined) img = Some(loader.get("ammo"))
    img.get
  }
  
  def move = this.position += movePos
  
  def draw(scale: Int) = main.image(pic, this.position.x, this.position.y)
  
  
  
  
  
}