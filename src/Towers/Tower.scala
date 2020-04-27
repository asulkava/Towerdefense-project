 package Towers

import processing.core._
import Enemies._
import GameStates.PlayState
import Main._
import Interface._

// abstarct class for buildings
abstract class Buildable(var position: Pos) {
  var cost: Int
  var range: Int
}

abstract class Towers(position: Pos) extends Buildable(position: Pos) {
  
  var loadedImage : Option[PImage] = None
  var target: Option[Enemy] = None
  var moveVector = Pos(0,0)
  var image_id = "tower"
  var cd = 0
  
  //distance to target
  def targetDistance: Double = {
    if(target.isDefined) {
      (this.position.distanceToAnother(target.get.position))
    }
    else {999}
  }
  
  def draw(scale: Int) = {
    main.image(image, this.position.x, this.position.y)
  }
  
  // draws the range
 def drawRange = {
    main.fill(0, 0, 0, 0)
    main.ellipse(this.position.x+main.offset.x, this.position.y+main.offset.y, this.range.toFloat, this.range.toFloat)
  }
  
  def shoot
  // target check and get
  def checkTarget = {
    if(target.isDefined) {
      if(targetDistance > range || !target.get.isAlive) {
        target = None
      }
    }
    else {
      None
    }
  }
  
   def getTarget = {
    for(e <- PlayState.enemies) {
      if (main.distance(this.position, e.position) < this.range) {
        if (!target.isDefined) {
          target = Some(e)
        }
      }
    }
  }
  //image for the tower
  def image() = {
	  if(!loadedImage.isDefined) {
	    loadedImage = Some(loader.get(image_id))
	  }
	  loadedImage.get
	}

}