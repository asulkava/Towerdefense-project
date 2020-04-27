package Interface

import scala.collection.mutable.Map
import processing.core._
import Main._

object loader {
  private val names = Map[String, String] (
      "ground" -> "ground.png",
      "wall" -> "wall.png",
      "pig"  -> "Pig.png",
      "skeleton"  ->"Skeleton.png",
      "zombie"  -> "Zombie.png",
      "ammo" -> "ammo.png",
      "back" -> "back.png",
      "RangeTower" -> "RangeTower.png",
      "FastTower" -> "FastTower.png",
      "FastTowerButton" -> "FastButton.png",
      "NormalTowerButton" -> "NormalButton.png",
      "RangeTowerButton" -> "RangeButton.png",
      "NormalTower" -> "NormalTower.png",
      "playButton" -> "play.png",
      "farmback" -> "farmback.png"
  )
  
  private val imagePath = "resources/imageFiles/"
   
  def loadImage(path: String) : PImage = {
    main.loadImage(s"${imagePath}${path}")
  }
	
	private val images = Map[String, PImage]()
	
	// Load image
	for(id <- names) {
	  images(id._1) = loadImage(id._2)
	}
	
	// Return image if found
	def get(id: String) : PImage = {
	  var i = images.get(id)
	  
	  if(!i.isDefined) {
	    throw new Exception(s"${id} is not a valid image identifier!")
	  }
	  
	  i.get
	}
}